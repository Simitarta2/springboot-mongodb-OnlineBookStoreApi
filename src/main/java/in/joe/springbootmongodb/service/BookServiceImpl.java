package in.joe.springbootmongodb.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.joe.springbootmongodb.exception.BookCollectionException;
import in.joe.springbootmongodb.model.BookOBJ;
import in.joe.springbootmongodb.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class BookServiceImpl  implements BookService {
	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public void createbook(BookOBJ book) throws BookCollectionException,ConstraintViolationException {
		// book Auto-generated method stub
		Optional<BookOBJ>bookOptional=bookRepo.findByBookTitle(book.getBookTitle());
		if(bookOptional.isPresent()) {
			throw new BookCollectionException(BookCollectionException.bookAlreadyExists());
		}else {
			bookRepo.save(book);
		}
	}

	
	public List<BookOBJ> getAllBooks(int pageNumber,int pageSize) {
		
		Pageable pages= PageRequest.of(pageNumber,pageSize);
		List<BookOBJ> books =bookRepo.findAll(pages).getContent();
		if(books.size()>0)return books;
		
		//I tried putting List but got an error so I tried ArrayList and it worked
		else {
			BookCollectionException.NoBooksAvailable();
			return new ArrayList<BookOBJ>();
		}
	}


	
	public List<BookOBJ> getAllBooksSrotedDESC(String sortType) {
		
		Sort sort=Sort.by(Sort.Direction.DESC,"price");
		List<BookOBJ> books =bookRepo.findAll(sort);
		if(books.size()>0)return books;
		else {
			BookCollectionException.NoBooksAvailable();
			return new ArrayList<BookOBJ>();
		}
	}
	
	public List<BookOBJ> getAllBooksSrotedASC() {
		Sort sort=Sort.by(Sort.Direction.ASC,"price");
		List<BookOBJ> books =bookRepo.findAll(sort);
		if(books.size()>0)return books;
		else {
			BookCollectionException.NoBooksAvailable();
			return new ArrayList<BookOBJ>();
		}
	}
	
	@Override
	public BookOBJ getbookByID(String id) throws BookCollectionException {
		// book Auto-generated method stub
		Optional<BookOBJ>bookOptional=bookRepo.findById(id);
		if(!bookOptional.isPresent()) {
			throw new BookCollectionException(BookCollectionException.NotFoundException(id));
			}
		else return bookOptional.get();
	}
	
	

	@Override
	//type void because  deleteById is a void
	public void deletebookByID(String id) throws BookCollectionException {
		Optional<BookOBJ>bookOptional=bookRepo.findById(id);
		if(!bookOptional.isPresent()) {
			throw new BookCollectionException(BookCollectionException.NotFoundException(id));
			}
		else bookRepo.deleteById(id);
	}

	@Override
	public void updateBookByID(String id, BookOBJ book) throws BookCollectionException {
		// TODO Auto-generated method stub
		Optional<BookOBJ>bookByID =bookRepo.findById(id);
		if(bookByID.isPresent()) {
			BookOBJ bookToUpdate=bookByID.get();
			bookToUpdate.setBookTitle(book.getBookTitle());
			bookToUpdate.setPublicationDate(book.getPublicationDate());
			bookToUpdate.setAvailableQuatity(book.getAvailableQuatity());
			bookToUpdate.setPrice(book.getPrice());
			bookToUpdate.setAuthorName(book.getAuthorName());
			bookToUpdate.setDescription(book.getDescription());
			bookRepo.save(bookToUpdate);
		}else {
			throw new BookCollectionException(BookCollectionException.NotFoundException(id));
		}
		
	}


	
	

}
