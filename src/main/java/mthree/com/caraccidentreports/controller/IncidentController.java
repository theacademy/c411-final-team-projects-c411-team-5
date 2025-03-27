package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.Incident;
import mthree.com.caraccidentreports.service.IncidentService;
import org.apache.logging.log4j.util.InternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/traffic")
public class IncidentController {
    
    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incidents")
    public ResponseEntity<List<Incident>> getIncidents(@RequestParam String city) {
        List<Incident> response = incidentService.refreshIncidentsForCity(city);
        return ResponseEntity.ok(response);

    }
}
