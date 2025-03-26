package mthree.com.caraccidentreports.dao.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import mthree.com.caraccidentreports.model.City;

public class CityMapper {

    public City mapToBoundingBox(JsonNode firstResult) {

        if (firstResult == null || !firstResult.has("boundingbox")) {
            return null;
        }

        double minLat = firstResult.get("boundingbox").get(0).asDouble();
        double maxLat = firstResult.get("boundingbox").get(1).asDouble();
        double minLon = firstResult.get("boundingbox").get(2).asDouble();
        double maxLon = firstResult.get("boundingbox").get(3).asDouble();

        City bbox = new City();
        bbox.setMinLat(minLat);
        bbox.setMaxLat(maxLat);
        bbox.setMinLon(minLon);
        bbox.setMaxLon(maxLon);

        return bbox;
    }
}
