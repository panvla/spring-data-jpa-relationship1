package com.vladimirpandurov.springdatarelationship.service;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.ZipcodeRequestDto;
import com.vladimirpandurov.springdatarelationship.model.Zipcode;

import java.util.List;

public interface ZipcodeService {

    Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);
    List<Zipcode> getZipcodes();
    Zipcode getZipcode(Long zipcodeId);
    Zipcode deleteZipcode(Long zipcodeId);
    Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto);
    Zipcode addCityToZipcode(Long zipcodeId, Long cityId);
    Zipcode removeCityFromZipcode(Long zipcodeId);

}
