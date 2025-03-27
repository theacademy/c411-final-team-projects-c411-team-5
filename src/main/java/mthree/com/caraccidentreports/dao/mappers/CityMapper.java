package mthree.com.caraccidentreports.dao.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import mthree.com.caraccidentreports.model.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {

//    public City mapToBoundingBox(JsonNode firstResult) {
//
//        if (firstResult == null || !firstResult.has("boundingbox")) {
//            return null;
//        }
//
//        double minLat = firstResult.get("boundingbox").get(0).asDouble();
//        double maxLat = firstResult.get("boundingbox").get(1).asDouble();
//        double minLon = firstResult.get("boundingbox").get(2).asDouble();
//        double maxLon = firstResult.get("boundingbox").get(3).asDouble();
//
//        City bbox = new City();
//        bbox.setMinLat(minLat);
//        bbox.setMaxLat(maxLat);
//        bbox.setMinLon(minLon);
//        bbox.setMaxLon(maxLon);
//
//        return bbox;
//    }

    public City mapToBoundingBox(ResultSet rs) throws SQLException {
        City city = new City();
        city.setMinLat(rs.getDouble("min_lat"));
        city.setMinLon(rs.getDouble("min_lon"));
        city.setMaxLat(rs.getDouble("max_lat"));
        city.setMaxLon(rs.getDouble("max_lon"));
        return city;
    }
}
