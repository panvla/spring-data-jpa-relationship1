package com.vladimirpandurov.springdatarelationship.repository;

import com.vladimirpandurov.springdatarelationship.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
