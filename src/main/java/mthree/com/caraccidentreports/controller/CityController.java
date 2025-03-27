package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.model.City;
import mthree.com.caraccidentreports.service.CityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geocode")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public String getBoundingBoxes(@RequestParam String city) {
        return cityService.getBoundingBox(city);
    }


}
