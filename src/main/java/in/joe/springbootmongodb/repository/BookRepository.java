package in.joe.springbootmongodb.repository;



import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.joe.springbootmongodb.model.BookOBJ;

@Repository
public interface BookRepository extends MongoRepository<BookOBJ,String> {

	@Query("{'bookTitle':?0}")
	Optional<BookOBJ>findByBookTitle(String bookTitle);
	
}
