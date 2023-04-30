package in.joe.springbootmongodb.entity;
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
	private String role;//'ADMIN' or 'USER'
	
	

	public UserOBJ(String id,
			 String email,
			 String password,
			String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
}
