package com.vladimirpandurov.springdatarelationship.repository;

import com.vladimirpandurov.springdatarelationship.model.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<Zipcode, Long> {
}
