package mthree.com.caraccidentreports.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.geojson.Geometry;
import org.geojson.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Accident {
    private String from;
    private String to;

    public Accident() {}

    public Accident(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
}

