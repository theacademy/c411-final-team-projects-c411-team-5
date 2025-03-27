package mthree.com.caraccidentreports.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mthree.com.caraccidentreports.dao.IncidentDao;
import mthree.com.caraccidentreports.dao.mappers.IncidentMapper;
import mthree.com.caraccidentreports.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private String bbox;
    private List<Incident> incidents;
    private IncidentDao incidentDao;
    private JdbcTemplate jdbcTemplate;

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
                incident.setFrom(from);
                incident.setTo(to);
                incident.setIncidentType(desc);
                incidents.add(incident);
            }
        return incidents;
    }

    private List<Incident> getIncidents(String bbox) {
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

    @Scheduled(fixedDelay = 60000 * 5)
    public void triggerRefreshWithTimer() {
        List<Incident> incidents = getIncidents(bbox);
        for (Incident incident : incidents) {
            incidentDao.createIncident(incident);
        }
        this.incidents = incidents;

        String sql = "Select lid from location";
        List<String> lids = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("lid"));

        for(String lid : lids) {
            StringBuilder builder = new StringBuilder();
            List<Incident> filteredIncidents = incidents.stream().filter(incident ->
                Objects.equals(incident.getIid(), lid)).toList();
            if (filteredIncidents.isEmpty()) {continue;}

            builder.append("You have the following incidents that may hinder traffic in your area:").append("\n\n");
            filteredIncidents.forEach(incident -> {
                builder.append("Incident Type: ").append(incident.getIncidentType()).append("\n");
                builder.append("From Street: ").append(incident.getFrom()).append("\n");
                builder.append("To Street: ").append(incident.getTo()).append("\n");
            });

            //send email here
        }


    }

    public List<Incident> refreshIncidents(String bbox) {
        this.bbox = bbox;
        triggerRefreshWithTimer();
        return incidents;
    }

    public List<Incident> getIncidents() {
        return incidentDao.getIncidents();
    }
}
