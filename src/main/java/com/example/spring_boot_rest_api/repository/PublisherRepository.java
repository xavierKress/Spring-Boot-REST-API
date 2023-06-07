package com.example.spring_boot_rest_api.repository;

import com.example.spring_boot_rest_api.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
