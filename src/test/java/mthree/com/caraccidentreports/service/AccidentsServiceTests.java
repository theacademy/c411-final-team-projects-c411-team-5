package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.App;
import mthree.com.caraccidentreports.model.Incident;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = App.class)
public class AccidentsServiceTests {

    @Autowired
    private IncidentService incidentService;

    @Test
    @DisplayName("grabbing accidents from tomtom")
    public void grabbingAccidentsFromTomtom() {
        List<Incident> incidents = incidentService.getIncidents("-74.560706,40.499211,-73.247828,41.101973");
    }
}
