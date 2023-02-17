package com.vladimirpandurov.springdatarelationship.controller;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.CityRequestDto;
import com.vladimirpandurov.springdatarelationship.model.City;
import com.vladimirpandurov.springdatarelationship.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody final CityRequestDto  cityRequestDto){
        City city = cityService.addCity(cityRequestDto);
        return new ResponseEntity<>(city,  HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") final Long id){
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<City>> getCities(){
        List<City> cities = cityService.getCities();
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCityById(@PathVariable("id") Long id){
        City city = cityService.deleteCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<City> editCity(@RequestBody final CityRequestDto cityRequestDto, @PathVariable final Long id){
        City city = cityService.editCity(id, cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
