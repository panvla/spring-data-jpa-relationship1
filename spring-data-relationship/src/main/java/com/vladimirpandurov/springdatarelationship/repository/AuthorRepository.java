package com.vladimirpandurov.springdatarelationship.repository;

import com.vladimirpandurov.springdatarelationship.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
