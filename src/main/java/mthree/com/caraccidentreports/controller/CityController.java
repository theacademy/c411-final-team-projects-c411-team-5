package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.City;
import mthree.com.caraccidentreports.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public Map<String, City> getBoundingBoxes(@RequestParam String[] cities) {
        return cityService.getBoundingBoxes(cities);
    }


}
