package in.joe.springbootmongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.joe.springbootmongodb.security.CustomUserDetailsService;


@SuppressWarnings("deprecation")
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/register","/login").permitAll()
		.antMatchers("/api/**","/user/**").authenticated()
//		.antMatchers("/api/book/save").hasRole("ADMIN")
//		.antMatchers("/api/book/getAll").hasAnyRole("ADMIN","USER")
		
		//lezim hot role aala save w delete w update
		//.anyRequest().authenticated()
		//.antMatchers("/").authenticated()	
		.and()
//		.authorizeRequests()
//		.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN","MANAGER")
//		.antMatchers(HttpMethod.GET).hasAnyRole("ADMIN","USER")
//		.and()
		.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth )throws Exception{
//		InMemoryUserDetailsManager userDetailsService=new InMemoryUserDetailsManager();
//		UserDetails user1=User.withUsername("joe").password("123").authorities("admin").build();
//		UserDetails user2=User.withUsername("toty").password("123").authorities("user").build();
//		userDetailsService.createUser(user1);
//		userDetailsService.createUser(user2);
		
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
	