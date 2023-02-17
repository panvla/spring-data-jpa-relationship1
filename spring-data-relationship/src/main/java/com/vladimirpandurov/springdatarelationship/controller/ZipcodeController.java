package com.vladimirpandurov.springdatarelationship.controller;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.ZipcodeRequestDto;
import com.vladimirpandurov.springdatarelationship.model.Zipcode;
import com.vladimirpandurov.springdatarelationship.service.ZipcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zipcode")
@RequiredArgsConstructor
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    @PostMapping
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto){
        Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Zipcode> getZipcode(@PathVariable("id") final Long id){
        Zipcode zipcode = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Zipcode>> getZipcodes(){
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Zipcode> deleteZipcode(@PathVariable("id") final Long id){
        Zipcode zipcode = zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Zipcode> editZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto,
                                               @PathVariable("id") final Long id){
        Zipcode zipcode = zipcodeService.editZipcode(id, zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
    @PostMapping("/addCity/{cityId}/toZipcode/{zipcodeId}")
    public ResponseEntity<Zipcode> addCity(@PathVariable("cityId") final Long cityId,
                                           @PathVariable("zipcodeId") final Long zipcodeId){
        Zipcode zipcode = zipcodeService.addCityToZipcode(zipcodeId, cityId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
    @DeleteMapping("/deleteCity/{zipcodeId}")
    public ResponseEntity<Zipcode> deleteCity(@PathVariable("zipcodeId") final Long zipcodeId){
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

}
