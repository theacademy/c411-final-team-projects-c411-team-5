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

    @Autowired
    private RestClient restClient;

    public TomTomService(RestClient restClient) {
        this.restClient = restClient;
    }

    // input bounding box ?
    public String getIncidents(String url) {
        return "";
    }
}
