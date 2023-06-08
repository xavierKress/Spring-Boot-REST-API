package com.example.spring_boot_rest_api.api.v1;



import com.example.spring_boot_rest_api.model.Book;
import com.example.spring_boot_rest_api.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book", description = "Book management APIs")
public class BookRestController {
	private final BookRepository bookRepository;

	public BookRestController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Operation(
			summary = "Retrieve the collection of books",
			description = "Get the list of books. The response is a collection of Book object with id, title, ISBN authors and publisher.",
			tags = { "books", "get" })
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Book.class), mediaType = "application/json") })
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<Book>> getBooks(){
		return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
		Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Book addBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book patchBookTitle(@PathVariable("id") Long id, @RequestBody Book patch){
		Book bookToPatch = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		bookToPatch.setTitle(patch.getTitle());
		return bookRepository.save(bookToPatch);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
	}

	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<String> bookNotFound() {

		return new ResponseEntity<>("book not found", HttpStatus.NOT_FOUND);
	}

}
