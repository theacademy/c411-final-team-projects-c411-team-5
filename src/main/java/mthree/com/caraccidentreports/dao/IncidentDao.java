package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.Incident;

import java.util.List;

public interface IncidentDao {
    List<Incident> getIncidents();
    List<Incident> getIncidentsByType(String type);
    Incident getIncidentById(String id);
    Incident createIncident(Incident incident);
    void updateIncident(Incident incident);
    void deleteIncident(Incident incident);
}
