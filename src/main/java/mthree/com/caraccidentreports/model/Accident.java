package mthree.com.caraccidentreports.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.geojson.Geometry;
import org.geojson.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Accident {
    private String type;
    private Geometry<?> geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry<?> getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry<?> geometry) {
        this.geometry = geometry;
    }

}

