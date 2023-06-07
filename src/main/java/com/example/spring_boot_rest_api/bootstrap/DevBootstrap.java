package com.example.spring_boot_rest_api.bootstrap;

import com.example.spring_boot_rest_api.component.ClientOrderComponent;
import com.example.spring_boot_rest_api.model.*;
import com.example.spring_boot_rest_api.repository.BookRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DevBootstrap {

	private final ClientOrderComponent clientOrder;
	private final BookRepository bookRepository;

	public DevBootstrap(ClientOrderComponent clientOrder, BookRepository bookRepository) {
		this.clientOrder = clientOrder;
		this.bookRepository = bookRepository;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void initData() {
		clientOrder.securedMethod();

		Author robertMartin = new Author("Robert", "MARTIN", LocalDate.of(1952, Month.DECEMBER, 5));
		Publisher pearson = new Publisher("Pearson");
		Book cleanCode = new Book("Clean Code", "9780132350884");
		cleanCode.getAuthors().add(robertMartin);
		cleanCode.setPublisher(pearson);

		Author craigWalls = new Author("Craig", "WALLS", LocalDate.of(1965, Month.FEBRUARY, 12));
		Publisher manningPublications = new Publisher("Manning Publications");
		Book springInAction = new Book("Spring in Action", "1935182358");
		springInAction.getAuthors().add(craigWalls);
		springInAction.setPublisher(manningPublications);


		bookRepository.save(cleanCode);
		bookRepository.save(springInAction);
	}
}
