package com.vladimirpandurov.springdatarelationship.repository;

import com.vladimirpandurov.springdatarelationship.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
