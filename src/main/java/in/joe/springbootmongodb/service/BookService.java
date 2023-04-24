package in.joe.springbootmongodb.service;

import java.util.List;

import in.joe.springbootmongodb.exception.BookCollectionException;
import in.joe.springbootmongodb.model.BookOBJ;
import jakarta.validation.ConstraintViolationException;

public interface BookService {
	public void createbook(BookOBJ book)throws BookCollectionException,ConstraintViolationException;

	public List<BookOBJ> getAllbooks();

	public BookOBJ getbookByID(String id)throws BookCollectionException;
	//void because the deleteByid<-- is a void
	public void deletebookByID(String id)throws BookCollectionException;
	
	public void updateBookByID(String id,BookOBJ book) throws BookCollectionException;
}
