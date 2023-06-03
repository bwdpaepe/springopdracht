package domein;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"book_id", "name", "image", "ISBN", "price", "authors", "locations", "votes"})
public class BookRest {
	@JsonProperty("book_id")
	private long id;

	private String name;
	
	private String image;
	
	@JsonProperty("ISBN")
	private long isbn;
	
	private double price;
	
	private List<String> authors;
	
	private List<String> locations;
	
	private List<String> votes;
}
