package com.vladimirpandurov.springdatarelationship.repository;

import com.vladimirpandurov.springdatarelationship.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
