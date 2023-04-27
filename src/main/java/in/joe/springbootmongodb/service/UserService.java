package in.joe.springbootmongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.joe.springbootmongodb.exception.UserCollectionException;
import in.joe.springbootmongodb.model.UserOBJ;
import in.joe.springbootmongodb.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class UserService  {

	@Autowired
	private UserRepository userRepo;
	
	public void creatUser(UserOBJ user) throws UserCollectionException,ConstraintViolationException{
	
		Optional<UserOBJ>userOptional=userRepo.findByEmail(user.getEmail());
		if(userOptional.isPresent()) {
			throw new UserCollectionException(UserCollectionException.userAlreadyExists());
		}else {
			userRepo.save(user);
		}
	}
	
	public List<UserOBJ> getAllUsers(){
		List<UserOBJ>users=userRepo.findAll();
		if(users.size()>0)return users;
		else {
			UserCollectionException.NoUsers();
			return new ArrayList<UserOBJ>();
		}
	}
	
	public UserOBJ getUserByID(String id) throws UserCollectionException {
		Optional<UserOBJ> user=userRepo.findById(id);
		if(user.isPresent()) 
			return user.get();
		else {
			throw new UserCollectionException(UserCollectionException.NotFoundException(id));
		}
	}
	public void deleteUserByID(String id) throws UserCollectionException {
		Optional<UserOBJ> user=userRepo.findById(id);
		if(user.isPresent())userRepo.deleteById(id);
		else
			throw new UserCollectionException(UserCollectionException.NotFoundException(id));
	}
	public void updateUserByID(String id,UserOBJ user) throws UserCollectionException {
		Optional<UserOBJ> userByID=userRepo.findById(id);
		if(userByID.isPresent()) {
			UserOBJ userToUP=userByID.get();
			userToUP.setPassword(user.getPassword());
			userToUP.setRole(user.getRole());
			userRepo.save(userToUP);
		}
		else
			throw new UserCollectionException(UserCollectionException.NotFoundException(id));
	}
		
	}

