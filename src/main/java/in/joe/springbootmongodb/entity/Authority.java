package in.joe.springbootmongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.annotations.Immutable;

@Document(value="authorities")
public class Authority {

	@Id
	private String id;
	private String authority;
	
}
