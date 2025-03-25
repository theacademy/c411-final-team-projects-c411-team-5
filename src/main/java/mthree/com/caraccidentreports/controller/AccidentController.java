package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.Accident;
import mthree.com.caraccidentreports.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/traffic") // not sure what is here
public class AccidentController {

    @Autowired
    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/incidents")
    public Accident getIncidents(
            @RequestParam float minLon,
            @RequestParam float minLat,
            @RequestParam float maxLon,
            @RequestParam float maxLat) {

        String bbox = String.format("%f,%f,%f,%f", minLon, minLat, maxLon, maxLat);
        return accidentService.getIncidents(bbox);
    }
}
