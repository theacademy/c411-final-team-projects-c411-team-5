package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.Accident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Service
public class AccidentService {

    @Autowired
    private RestClient restClient;

    // when running, paste the api key into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final String API_KEY = "";
    // when running, paste API_KEY into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private final String BASE_URL = "https://api.tomtom.com/traffic/services/5/incidentDetails";
    private final String FIELDS = "{incidents{type,geometry{type,coordinates}}}";
    public AccidentService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl(BASE_URL).build();
    }

    public Accident getIncidents(String bbox) {

        try {
            String endpoint = BASE_URL + "?key=" + API_KEY + "&categoryFilter=1&bbox=" + bbox + "&fields=" + FIELDS;

            return restClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .body(Accident.class);
        } catch (RestClientResponseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
