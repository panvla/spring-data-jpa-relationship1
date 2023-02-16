package com.vladimirpandurov.springdatarelationship.service;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.CityRequestDto;
import com.vladimirpandurov.springdatarelationship.model.City;

import java.util.List;

public interface CityService {
    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCities();
    public City getCity(Long cityId);
    public City deleteCity(Long cityId);
    public City editCity(Long cityId,CityRequestDto cityRequestDto);
}
