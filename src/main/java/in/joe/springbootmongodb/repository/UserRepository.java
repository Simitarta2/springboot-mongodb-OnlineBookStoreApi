package in.joe.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import in.joe.springbootmongodb.entity.UserOBJ;

public interface UserRepository extends MongoRepository<UserOBJ,String> {

	@Query("{'email':?0}")
	Optional<UserOBJ>findByEmail(String email);

	
}
