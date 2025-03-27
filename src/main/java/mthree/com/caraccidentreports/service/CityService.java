package mthree.com.caraccidentreports.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mthree.com.caraccidentreports.dao.mappers.CityMapper;
import mthree.com.caraccidentreports.model.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class CityService {
    private static final String BASE_URL = "https://api.tomtom.com/search/2/geocode/%s.json?key=%s";

    @Value("${spring.tomtom.key}")
    private String apiKey;

    private final RestClient restClient;
    private final ObjectMapper objMapper;
    private final CityMapper mapper;

    public CityService() {
        this.restClient = RestClient.create();
        this.objMapper = new ObjectMapper();
        this.mapper = new CityMapper();
    }

    public String getBoundingBox(String city) {

        String url = String.format(BASE_URL, city, apiKey);


        try {
            // restclient gets response in JSON form
            String response = restClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            return parseBoundingBox(response);

        } catch (RestClientException e) {
            throw new RuntimeException("Error calling TomTom API", e);
        }
    }

    /**
     * Extracts coordinates from input
     * @param response the response
     * @return the city object with coordinates stored
     */
    private String parseBoundingBox(String response) {
        try {
            JsonNode root = objMapper.readTree(response);
            JsonNode boundingBox = root.at("/results/0/boundingBox");

            // if somethings going wrong this could be off but i really double checked so
            double minLat = boundingBox.at("/btmRightPoint/lat").asDouble();
            double minLon = boundingBox.at("/topLeftPoint/lon").asDouble();
            double maxLat = boundingBox.at("/topLeftPoint/lat").asDouble();
            double maxLon = boundingBox.at("/btmRightPoint/lon").asDouble();

            //return new City(minLat, minLon, maxLat, maxLon);
            return String.format("%f,%f,%f,%f", minLon, minLat, maxLon, maxLat);

        } catch (Exception e) {
            throw new RuntimeException("Error parsing bounding box data", e);
        }

    }


}
