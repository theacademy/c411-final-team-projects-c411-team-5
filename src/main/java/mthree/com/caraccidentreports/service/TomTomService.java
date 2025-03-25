package mthree.com.caraccidentreports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class TomTomService {

//    @Value("${tomtom.api.key}")
//    private String apiKey;

    private RestClient restClient;
    // when running, paste API_KEY into this variable but don't push to github
    private final String API_KEY = "";
    private final String BASE_URL = "api.tomtom.com";

    @Autowired
    public TomTomService(RestClient restClient) {
        this.restClient = restClient;
    }

    // input bounding box ?
    public String getIncidents(String bbox) {
        // replace brackets
        String url = BASE_URL + "/traffic/services/5/incidentDetails?bbox=" + bbox + "key=" + API_KEY +
                "&fields={incidents{type,geometry{type,coordinates},properties{iconCategory}}}&categoryFilter=1&language=en-GB&t=1111&timeValidityFilter=pres";

        return "";
        // return restClient.get().uri(url).retrieve().body(TrafficIncidentResponse.class);
    }
}
