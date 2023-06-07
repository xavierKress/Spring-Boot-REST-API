package com.example.spring_boot_rest_api.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	private LocalDate birthDay;

	@Transient
	private Integer age;

	@ManyToMany(mappedBy ="authors")
	private Set<Book> books = new HashSet<>();

	public Author() {
	}

	public Author(String firstName, String lastName, LocalDate birthDay) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getAge() {
		return Period.between(this.birthDay, LocalDate.now()).getYears();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return id.equals(author.id) && firstName.equals(author.firstName) && lastName.equals(author.lastName) && birthDay.equals(author.birthDay);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, birthDay);
	}

}
