package com.example.spring_boot_rest_api;


import com.example.spring_boot_rest_api.model.Author;
import com.example.spring_boot_rest_api.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

	private final AuthorRepository authorRepository;

	@Autowired
	public AuthorRepositoryTest(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}


	@Test
	@Transactional
	void findAllByBirthDayBetween() {

		List<Author> authorsBornBtw1950And1970 = authorRepository.
				findAllByBirthDayBetween(
						LocalDate.of(1950, 1, 1),
						LocalDate.of(1970, 1, 1)
				);

		List<Author> authorsBornBtw1950And1960 = authorRepository.
				findAllByBirthDayBetween(
						LocalDate.of(1950, 1, 1),
						LocalDate.of(1960, 1, 1)
				);

		assertEquals(2, authorsBornBtw1950And1970.size());
		assertEquals(1, authorsBornBtw1950And1960.size());


		assertEquals(1, authorsBornBtw1950And1960.get(0).getBooks().size());

	}

	@Test
	void updateAuthorTest(){
		// verify that there is initially 2 rows in the table
		assertEquals(2, authorRepository.count());

		LocalDate dateOfBirth = LocalDate.of(2000, Month.JANUARY, 1);
		Author authorTest = new Author("firstName", "lastName", dateOfBirth);
		authorRepository.save(authorTest);

		// verify that there is 3 rows in the table after persisting the author
		assertEquals(3, authorRepository.count());

		authorTest.setFirstName("updatedFirstName");
		// verify that the updated author is not persisted before calling the save() method
		assertNull(authorRepository.findAuthorByFirstNameAndLastNameAndBirthDay(
				"updatedFirstName",
				"lastName",
				dateOfBirth));

		authorRepository.save(authorTest);
		// verify that there is no added row in the table and that the update is persisted
		assertEquals(3, authorRepository.count());
		assertNotNull(authorRepository.findAuthorByFirstNameAndLastNameAndBirthDay(
				"updatedFirstName",
				"lastName",
				dateOfBirth));
	}
}

