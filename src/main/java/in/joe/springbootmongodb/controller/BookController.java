package in.joe.springbootmongodb.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.joe.springbootmongodb.entity.BookOBJ;
import in.joe.springbootmongodb.exception.BookCollectionException;
import in.joe.springbootmongodb.service.BookService;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/book")
public class BookController {

	
	//Because we added security in the dependency everything will be protected
	
	@Autowired
	private BookService bookService;
	
	
	
	@PostMapping(value="/save")
	public ResponseEntity<?>createbook(@RequestBody BookOBJ book){
		try {
			//book.setCreatedAt(new Date (System.currentTimeMillis()));
			//bookRepo.save(book);
		
			bookService.creatBook(book);
			return new ResponseEntity<BookOBJ>(book,HttpStatus.OK);
		
		}catch(BookCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (value="/getAll")
	public ResponseEntity<?>getAllbooks(@RequestParam Integer pageNumber,@RequestParam Integer pageSize){
		List<BookOBJ> books=bookService.getAllBooks(pageNumber,pageSize);
		return new ResponseEntity<>(books,books.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping (value="/getAll/sorted/DESC")
	public ResponseEntity<?>getAllbooksSortedDESC(){
		List<BookOBJ> books=bookService.getAllBooksSrotedDESC();
		return new ResponseEntity<>(books,books.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	@GetMapping (value="/getAll/sorted/ASC")
	public ResponseEntity<?>getAllbooksSortedASC(){
		List<BookOBJ> books=bookService.getAllBooksSrotedASC();
		return new ResponseEntity<>(books,books.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/get/{id}")
	public ResponseEntity<?>getById(@PathVariable("id")String id){
		try {
			
			return new ResponseEntity<>(bookService.getBookByID(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
//	@GetMapping("/books/books")
//	public ResponseEntity<?> getBookByTitle(@RequestParam("title") String title){
//		try {
//			return new ResponseEntity<>(bookService.getBookByTitle(title),HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@GetMapping("/books/price")
//	public ResponseEntity<List<BookOBJ>> searchBooksByPrice(
//	    @RequestParam(name = "minPrice", required = true) double minPrice,
//	    @RequestParam(name = "maxPrice", required = true) double maxPrice
//	) {
//	    List<BookOBJ> books = bookService.searchBooksByPriceRange(minPrice, maxPrice);
//	    return new ResponseEntity<List<BookOBJ>>(books,books.size()>0? HttpStatus.OK:HttpStatus.NOT_FOUND);
//	}
	
	@PutMapping(value="/update/{id}")
	
	public ResponseEntity<?>updateById(@PathVariable("id")String id,@RequestBody BookOBJ book){
		try {
			bookService.updateBookByID(id, book);
			return new ResponseEntity<>("Update book with ID:"+id,HttpStatus.OK);
		
		}catch(BookCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("delete/{id}")
	
	public ResponseEntity<?>deleteById(@PathVariable("id")String id){
		try {
			bookService.deleteBookByID(id);
			return new ResponseEntity<>("Succefully deleted with id"+id,HttpStatus.OK);
		}catch(BookCollectionException e){
		    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
}
