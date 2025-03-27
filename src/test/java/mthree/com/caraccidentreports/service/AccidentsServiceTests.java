package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.App;
import mthree.com.caraccidentreports.dao.mappers.IncidentMapper;
import mthree.com.caraccidentreports.model.Incident;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest(classes = App.class)
public class AccidentsServiceTests {

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("grabbing accidents from tomtom")
    public void grabbingAccidentsFromTomtom() {
        List<Incident> incidents = incidentService.refreshIncidents("Seattle WA");
        //List<Incident> incidents2 = incidentService.getIncidents();
        //Assertions.assertEquals(incidents, incidents2);
        Assertions.assertFalse(incidents.isEmpty());
    }
}
