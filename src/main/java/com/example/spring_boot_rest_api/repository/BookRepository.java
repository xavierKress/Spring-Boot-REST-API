package com.example.spring_boot_rest_api.repository;


import com.example.spring_boot_rest_api.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
