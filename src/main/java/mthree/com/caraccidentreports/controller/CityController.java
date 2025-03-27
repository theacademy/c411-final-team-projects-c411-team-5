package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.City;
import mthree.com.caraccidentreports.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/geocode")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public City getBoundingBoxes(@PathVariable String city) {
        return cityService.getBoundingBoxes(city);
    }


}
