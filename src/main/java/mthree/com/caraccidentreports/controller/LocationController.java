package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.Location;
import mthree.com.caraccidentreports.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin
public class LocationController {

    @Autowired
    LocationServiceImpl locationService;

    @GetMapping("")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{lid}")
    public Location getLocationByLid(@PathVariable String lid) {
        return locationService.getLocationByLid(lid);
    }
}
