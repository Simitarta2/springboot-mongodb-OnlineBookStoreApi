package in.joe.springbootmongodb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import in.joe.springbootmongodb.entity.UserOBJ;
import in.joe.springbootmongodb.exception.UserCollectionException;
import in.joe.springbootmongodb.service.UserService;

@RestController
@CrossOrigin(origins="*")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authentication;
	
	@PostMapping(value="/register")
	public ResponseEntity<?>creatUser(@RequestBody UserOBJ user){
		try {
			UserOBJ newUser=new UserOBJ();
			newUser.setEmail(user.getEmail());
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.creatUser(newUser);
			return new ResponseEntity<UserOBJ>(newUser,HttpStatus.OK);				
		
		}catch(UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(value="/login")
	public ResponseEntity<HttpStatus>login(@RequestBody UserOBJ userModel){
		Authentication authObject;
		try {
			authObject=authentication.authenticate(new
					UsernamePasswordAuthenticationToken(userModel.getEmail(),userModel.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authObject);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch(BadCredentialsException e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PostMapping("/logout")
	public ResponseEntity<HttpStatus> logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	    }
	    return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	@GetMapping(value="/user/getAll")
	public ResponseEntity<?>getUsers(@RequestParam Integer pageNumber,Integer pageSize){
		List<UserOBJ>users=userService.getAllUsers(pageNumber,pageSize);
		return new ResponseEntity<>(users,users.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/user/get/{id}")
	public ResponseEntity<?>getUserByID(@PathVariable("id")String id){
		try{
			return new ResponseEntity<>(userService.getUserByID(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(value="/user/update/{id}")
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
	
	@DeleteMapping(value="/user/delete/{id}")
	public ResponseEntity<?> deleteUserByID(@PathVariable("id")String id){
		try{
			userService.deleteUserByID(id);
			return new ResponseEntity<>("Succefully deleted with id"+id,HttpStatus.OK);
		}catch(UserCollectionException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
}

