package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.model.Accident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AccidentService {

    @Autowired
    private RestClient restClient;

    // when running, paste the api key into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!
    private final String API_KEY = "";
    // when running, paste API_KEY into this variable but don't push to github!!!!!!!!!!!!!!!!!!!!!!!!!!

    private final String BASE_URL = "https://api.tomtom.com/traffic/services/5/incidentDetails";

    public AccidentService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl(BASE_URL).build();
    }

    // input bounding box ?
    public Accident getIncidents(String bbox) {
        // replace brackets
        String endpoint = "?key=" + API_KEY + "&categoryFilter=1&bbox=" + bbox;
        System.out.println(endpoint);

        return restClient.get()
                .uri(endpoint)
                .retrieve()
                .body(Accident.class);
    }
}
