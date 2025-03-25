package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.service.AccidentService;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/traffic")
public class AccidentController {

    @Autowired
    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/incidents")
    public ResponseEntity<FeatureCollection> getIncidents(
            @RequestParam float minLon,
            @RequestParam float minLat,
            @RequestParam float maxLon,
            @RequestParam float maxLat) {

        String bbox = String.format("%f,%f,%f,%f", minLon, minLat, maxLon, maxLat);

        FeatureCollection response = accidentService.getIncidents(bbox);

        return ResponseEntity.ok(response);

    }
}
