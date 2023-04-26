package in.joe.springbootmongodb.exception;

public class BookCollectionException extends Exception{

	private static final long serialVersionUID=1L;
	
	//Global exception to catch the error
	public BookCollectionException(String message) {
		super(message);
	}
	
	//to prevent nonAvailable data
	public static String NotFoundException(String id) {
		return"book with "+id+" not found";
	}
	//to prevent duplication
	public static String bookAlreadyExists() {
		return "book with given name already exists";
	}
	
	public static String NoBooksAvailable() {
		return "No books available";
	}
}
