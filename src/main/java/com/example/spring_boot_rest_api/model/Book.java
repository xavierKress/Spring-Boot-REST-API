package com.example.spring_boot_rest_api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String isbn;

	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name = "author_book",
			joinColumns = @JoinColumn(name="book_id"),
			inverseJoinColumns = @JoinColumn(name="author_id"))
	@JsonIgnore
	private Set<Author> authors = new HashSet<>();

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="publisher_id", referencedColumnName="id")
	private Publisher publisher;

	public Book() {
	}

	public Book(String title, String isbn) {
		this.title = title;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(authors, book.authors) && Objects.equals(publisher, book.publisher);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, isbn, authors, publisher);
	}
}

