package mthree.com.caraccidentreports.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mthree.com.caraccidentreports.dao.IncidentDao;
import mthree.com.caraccidentreports.dao.mappers.CustomerMapper;
import mthree.com.caraccidentreports.dao.mappers.IncidentMapper;
import mthree.com.caraccidentreports.dao.mappers.LocationMapper;
import mthree.com.caraccidentreports.dao.mappers.UserCredentialMapper;
import mthree.com.caraccidentreports.model.Customer;
import mthree.com.caraccidentreports.model.Incident;
import mthree.com.caraccidentreports.model.Location;
import mthree.com.caraccidentreports.model.UserCredential;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IncidentService {

    private RestClient restClient;

    // when running, paste the api key into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Value("${spring.tomtom.key}")
    private String API_KEY;
    // when running, paste API_KEY into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Value("${spring.tomtom.baseurl}")
    private String BASE_URL;
    // i think we may have to use everything
    private String FIELDS = "{incidents{properties{events{description},from,to}}}";
    private String FILTER = "1,7,8,9,14";
    @Value("${spring.tomtom.bbox}")
    private String bboxurl;
    private List<Incident> incidents;
    private IncidentDao incidentDao;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmailService emailService;
    @Autowired
    public IncidentService(RestClient restClient, IncidentDao incidentDao, JdbcTemplate jdbcTemplate) {
        this.restClient = restClient;
        this.incidentDao = incidentDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Incident> jsonToListOfAccidents(JsonNode incidentsNode) {
        List<Incident> incidents = new ArrayList<>();
        for (JsonNode incidentNode : incidentsNode) {
                JsonNode propertiesNode = incidentNode.path("properties");
                String from = propertiesNode.path("from").asText();
                String to = propertiesNode.path("to").asText();
                String desc = propertiesNode.path("events").get(0).path("description").asText();
                desc = switch (desc) {
                    case "Closed", "Roadworks" -> "Road closed for maintenance";
                    case "Accident" -> "Vehicle accident";
                    default -> desc;
                };
                Incident incident = new Incident();
                incident.setLid("2");
                incident.setFrom(from);
                incident.setTo(to);
                incident.setIncidentType(desc);
                incidents.add(incident);
            }
        return incidents;
    }

    private List<Incident> getIncidents(String city) {
        String bbox = restClient.get().uri(bboxurl + city).retrieve().body(String.class);
        try {
            String url = BASE_URL +
                    "?bbox=" + bbox +
                    "&fields=" + FIELDS +
                    "&categoryFilter=1,7,8,9,14" + //
                    "&language=en-US" +
                    "&timeValidityFilter=present" +
                    "&key=" + API_KEY;

            String response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            JSONObject jsonObject = new JSONObject(response);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode incidentsNode = rootNode.path("incidents");

            List<Incident> incidents = jsonToListOfAccidents(incidentsNode);
            return incidents;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to either fetch or parse incident data", e);
        }
    }

    private void refreshIncidentsInLocation(Location location) {
        List<Incident> incidents = getIncidents(location.getCity() + " " + location.getState());
            for (Incident incident : incidents) {
                incident.setLid(location.getLid());
                incidentDao.createIncident(incident);
            }
            this.incidents = incidents;

            String lid = location.getLid();
            StringBuilder builder = new StringBuilder();
            if (this.incidents.isEmpty()) { return;}

        builder.append("🚨 You have the following incidents that may hinder traffic in your area:\n\n");

        this.incidents.forEach(incident -> {
            builder.append("🔹 ").append(incident.getIncidentType()).append("\n")
                    .append("   ➡️ From: ").append(incident.getFrom()).append("\n")
                    .append("   ➡️ To: ").append(incident.getTo()).append("\n\n");
        });

        builder.append("⚠️ Stay safe and plan your route accordingly.\n");

            handleEmails(lid, builder);
    }

    private void handleEmails(String lid, StringBuilder builder) {
        String customerSql = "SELECT username FROM customer WHERE lid = ?;";
            List<String> users = jdbcTemplate.query(customerSql, ((rs, rowNum) -> rs.getString("username")), lid);

            for(String c : users) {
                String emailQuery = "SELECT email FROM user_cred WHERE username = ?;";
                String email = jdbcTemplate.queryForObject(emailQuery, ((rs, rowNum) -> rs.getString("email")), c);
                emailService.sendSimpleEmail(email,"There is a new incident report!",builder.toString());
            }
    }

    @Scheduled(fixedDelay = 60000 * 5)
    public void triggerRefreshWithTimer() {
        String sql = "Select * from location";
        List<Location> locations = jdbcTemplate.query(sql, new LocationMapper());

        for (Location location : locations) {
            refreshIncidentsInLocation(location);
        }
    }

    public List<Incident> refreshIncidentsForCity(String city) {
        String sql = "Select * from location where city = ?;";
        String[] parts = city.split(" ");
        Location location = jdbcTemplate.queryForObject(sql, new LocationMapper(),
                String.join(" ", Arrays.copyOf(parts, parts.length - 1)));
        refreshIncidentsInLocation(location);
        return incidents;
    }

    public List<Incident> refreshIncidents(String city) {
        refreshIncidentsForCity(city);
        return incidents;
    }

    public List<Incident> getIncidents() {
        return incidentDao.getIncidents();
    }
}
