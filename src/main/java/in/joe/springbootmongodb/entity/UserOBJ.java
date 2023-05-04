package in.joe.springbootmongodb.entity;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="users")
public class UserOBJ {

	@Id
	private String id;
	@NotNull(message=" User email cannot be null/n")
	@NotEmpty(message="user email cannot be empty")
	private String email;
	@NotNull(message=" User password cannot be null/n")
	@NotEmpty(message="user password cannot be empty")
	private String password;
	
	private Set<Authority>authorities;
	
	public UserOBJ(String id,
			 String email,
			 String password,
			String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		
	}
	
	public UserOBJ() {}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	
	
	
	
}
