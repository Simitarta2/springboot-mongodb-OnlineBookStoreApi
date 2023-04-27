package in.joe.springbootmongodb.exception;

public class UserCollectionException extends Exception{
private static final long serialVersionUID=1L;
	
	//Global exception to catch the error
	public UserCollectionException(String message) {
		super(message);
	}
	
	//to prevent nonAvailable data
	public static String NotFoundException(String id) {
		return"user with "+id+" not found";
	}
	//to prevent duplication
	public static String userAlreadyExists() {
		return "user with given name already exists";
	}
	
	public static String NoUsers() {
		return "No user ";
	}
}

