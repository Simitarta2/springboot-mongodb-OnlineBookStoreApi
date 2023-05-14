package in.joe.springbootmongodb.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import in.joe.springbootmongodb.entity.UserOBJ;
import in.joe.springbootmongodb.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserOBJ user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));

		return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
	}
}