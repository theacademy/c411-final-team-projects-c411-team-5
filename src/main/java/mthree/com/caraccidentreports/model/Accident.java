package mthree.com.caraccidentreports.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Accident {

    private String type; // e.g., accident, construction
    private Geometry geometry; // location of the incident

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * Nested class for Geometry (i.e. coordinates)
     */
    public static class Geometry {
        private String type; // type of geometry
        private float[] coordinates; // coordinates (long, lat)

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public float[] getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(float[] coordinates) {
            this.coordinates = coordinates;
        }
    }

}

