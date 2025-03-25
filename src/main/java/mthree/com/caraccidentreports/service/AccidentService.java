package mthree.com.caraccidentreports.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.geojson.FeatureCollection;

@Service
public class AccidentService {

    @Autowired
    private RestClient restClient;

    // when running, paste the api key into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final String API_KEY = "";
    // when running, paste API_KEY into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private final String BASE_URL = "https://api.tomtom.com/traffic/services/5/incidentDetails";
    // i think we may have to use everything
    private final String FIELDS = "{incidents{type,geometry{type,coordinates},properties{id,iconCategory,magnitudeOfDelay,events{description,code}}}}";

    public AccidentService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    public FeatureCollection getIncidents(String bbox) {

        try {
            String endpoint = BASE_URL + "?key=" + API_KEY + "&categoryFilter=1&bbox=" + bbox + "&fields=" + FIELDS;

            String jsonResponse = restClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .body(String.class);

            ObjectMapper mapper = new ObjectMapper();
            FeatureCollection featureCollection = mapper.readValue(jsonResponse, FeatureCollection.class);
            return featureCollection;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to either fetch or parse incident data", e);

        }
    }
}
