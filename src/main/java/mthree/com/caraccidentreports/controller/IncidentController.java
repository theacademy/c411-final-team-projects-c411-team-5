package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.Incident;
import mthree.com.caraccidentreports.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Incident>> getIncidents(
            @RequestParam float minLon,
            @RequestParam float minLat,
            @RequestParam float maxLon,
            @RequestParam float maxLat) {

        String bbox = String.format("%f,%f,%f,%f", minLon, minLat, maxLon, maxLat);

        List<Incident> response = incidentService.refreshIncidents(bbox);

        return ResponseEntity.ok(response);

    }
}
