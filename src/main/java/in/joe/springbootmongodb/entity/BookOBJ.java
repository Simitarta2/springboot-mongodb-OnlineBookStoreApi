package in.joe.springbootmongodb.entity;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="books")
public class BookOBJ {

	@Id
	private String id;
	
	@NotNull(message="Book title cannot be null")
	@NotEmpty(message="Book tutke cannot be empty")
	private String bookTitle;
	@NotNull(message="Publication date cannot be null")
	private String publicationDate;
	@NotNull(message="Available Quatity cannot be null")
	private int availableQuatity;
	@NotNull(message="Price cannot be null")
	private double price;
	private String authorName;
	private String description;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public int getAvailableQuatity() {
		return availableQuatity;
	}
	public void setAvailableQuatity(int availableQuatity) {
		this.availableQuatity = availableQuatity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public BookOBJ(String id,  String bookTitle,
			 String publicationDate,
			 int availableQuatity,
			 double price, String authorName, String description) {
		this.id = id;
		this.bookTitle = bookTitle;
		this.publicationDate = publicationDate;
		this.availableQuatity = availableQuatity;
		this.price = price;
		this.authorName = authorName;
		this.description = description;
	}
	public BookOBJ() {
		
	}

	
	
	
	

}