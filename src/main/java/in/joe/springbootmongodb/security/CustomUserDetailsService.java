package in.joe.springbootmongodb.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
//		Optional <UserOBJ> user=userRepository.findByEmail(email);
//		user.orElseThrow(()->new UsernameNotFoundException("User not found"));
//		//return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
//		return user.map(CustomUserDetails::new).get();
		UserOBJ user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
	}
	
//	private List<SimpleGrantedAuthority>getAuthorities(Set<Authority>authorities){
//		List<SimpleGrantedAuthority>list=new ArrayList<>();
//		for(Authority auth:authorities) {
//			list.add(new SimpleGrantedAuthority(auth.getAuthority()));
//		}
//		return list;
//	}
}
