package in.joe.springbootmongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection="users")
public class UserOBJ {

	private String id;
	@NotNull(message=" User email cannot be null/n")
	@NotEmpty(message="user email cannot be empty")
	private String email;
	@NotNull(message=" User password cannot be null/n")
	@NotEmpty(message="user password cannot be empty")
	private String password;
	private Boolean role;
	
	

	public UserOBJ(String id,
			@NotNull(message = " User email cannot be null/n") @NotEmpty(message = "user email cannot be empty") String email,
			@NotNull(message = " User password cannot be null/n") @NotEmpty(message = "user password cannot be empty") String password,
			Boolean role) {
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

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	
	
	
}
