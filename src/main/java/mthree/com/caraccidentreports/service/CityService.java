package mthree.com.caraccidentreports.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mthree.com.caraccidentreports.dao.mappers.CityMapper;
import mthree.com.caraccidentreports.model.City;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class CityService {
    private static final String BASE_URL = "https://nominatim.openstreetmap.org/search?format=json&q=";

    private final RestClient restClient;
    private final ObjectMapper objMapper;
    private final CityMapper mapper;

    public CityService() {
        this.restClient = RestClient.create();
        this.objMapper = new ObjectMapper();
        this.mapper = new CityMapper();
    }

    public Map<String, City> getBoundingBoxes(String[] cities) {
        Map<String, City> results = new HashMap<>();

        for (String city : cities) {
            String url = buildUrl(city);

            try {
                // restclient gets response in JSON form
                String response = restClient
                        .get()
                        .uri(url)
                        .retrieve()
                        .body(String.class);

                // turn into a json node
                JsonNode root = objMapper.readTree(response);

                if (root.isArray() && root.size() > 0) {
                    JsonNode firstResult = root.get(0);

                    City bbox = mapper.mapToBoundingBox(firstResult);

                    // store bbox in results map
                    if (bbox != null) {
                        results.put(city, bbox);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    private String buildUrl(String city) {
        // replace whitespace
        String encodedCity = city.replace(" ", "%20");

        return BASE_URL + encodedCity;
    }


}
