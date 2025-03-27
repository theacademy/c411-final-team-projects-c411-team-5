package mthree.com.caraccidentreports.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Incident {
    private String iid;
    private String lid;
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

    public void setIid(String iid) {
        this.iid = iid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return Objects.equals(getIid(), incident.getIid()) && Objects.equals(getFrom(), incident.getFrom()) && Objects.equals(getTo(), incident.getTo()) && Objects.equals(getIncidentType(), incident.getIncidentType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIid(), getFrom(), getTo(), getIncidentType());
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }
}

