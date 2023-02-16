package com.vladimirpandurov.springdatarelationship.service;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.ZipcodeRequestDto;
import com.vladimirpandurov.springdatarelationship.model.City;
import com.vladimirpandurov.springdatarelationship.model.Zipcode;
import com.vladimirpandurov.springdatarelationship.repository.ZipCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ZipcodeServiceImpl implements ZipcodeService{

    private final ZipCodeRepository zipCodeRepository;
    private final CityService cityService;

    @Override
    @Transactional
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId()==null){
            return zipCodeRepository.save(zipcode);
        }
        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcode.setCity(city);
        return zipCodeRepository.save(zipcode);
    }

    @Override
    public List<Zipcode> getZipcodes() {
        return StreamSupport
                .stream(zipCodeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Zipcode getZipcode(Long zipcodeId) {
        return zipCodeRepository.findById(zipcodeId).orElseThrow(()-> new IllegalArgumentException("zipcode with id: " + zipcodeId + " could not be found"));
    }

    @Override
    public Zipcode deleteZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        zipCodeRepository.delete(zipcode);
        return zipcode;
    }
    @Transactional
    @Override
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcodeToEdit = getZipcode(zipcodeId);
        zipcodeToEdit.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId() != null){
            return zipcodeToEdit;
        }
        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcodeToEdit.setCity(city);
        return zipcodeToEdit;
    }
    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        City city = cityService.getCity(cityId);
        if(Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("zipcode already has a city");
        }
        zipcode.setCity(city);
        return zipcode;
    }
    @Transactional
    @Override
    public Zipcode removeCityFromZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        if(!Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("zipcode does not hava a city");
        }
        zipcode.setCity(null);
        return zipcode;
    }
}
