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

import in.joe.springbootmongodb.entity.UserOBJ;
import in.joe.springbootmongodb.exception.UserCollectionException;
import in.joe.springbootmongodb.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(value="/save")
	public ResponseEntity<?>creatUser(@RequestBody UserOBJ user){
		try {
			userService.creatUser(user);
			return new ResponseEntity<UserOBJ>(user,HttpStatus.OK);				
		
		}catch(UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/getAll")
	public ResponseEntity<?>getUsers(@RequestParam Integer pageNumber,Integer pageSize){
		List<UserOBJ>users=userService.getAllUsers(pageNumber,pageSize);
		return new ResponseEntity<>(users,users.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/get/{id}")
	public ResponseEntity<?>getUserByID(@PathVariable("id")String id){
		try{
			return new ResponseEntity<>(userService.getUserByID(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(value="/update/{id}")
	public ResponseEntity<?> updateUserByID(@PathVariable("id")String id,@RequestBody UserOBJ user){
		try {
			userService.updateUserByID(id, user);
			return new ResponseEntity<UserOBJ>(user,HttpStatus.OK);
		
		}catch(UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> deleteUserByID(@PathVariable("id")String id){
		try{
			userService.deleteUserByID(id);
			return new ResponseEntity<>("Succefully deleted with id"+id,HttpStatus.OK);
		}catch(UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
}

