package mthree.com.caraccidentreports.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Incident {
    private String iid;
    private String from;
    private String to;
    private String incidentType;

    public Incident() {
        this.iid = UUID.randomUUID().toString();
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

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIid() {
        return iid;
    }

    public void setAid(String iid) {
        this.iid = iid;
    }
}

