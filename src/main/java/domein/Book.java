package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//import org.hibernate.validator.constraints.Range;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.DecimalMax;
//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import validator.ValidISBN;

@Entity
@NamedQueries({
	@NamedQuery(name = "Book.authorsOfBook",
	query = "SELECT b.authorList FROM Book b WHERE :Id = b.id"),
	@NamedQuery(name = "Book.locationsOfBook",
	query = "SELECT b.locationList FROM Book b WHERE :Id = b.id"),
	@NamedQuery(name = "Book.votesOfBook",
	query = "SELECT COUNT(*) FROM Book b JOIN b.userList u WHERE :Id = b.id"),
	@NamedQuery(name = "Book.popularBooks",
	query = "SELECT new domein.BookPopular(b.id as id, b.name as name, COUNT(*) as numVotes) FROM Book b JOIN b.userList u GROUP BY b.id ORDER BY numVotes DESC, name")
})
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"book_id", "name", "image", "ISBN", "price", "authorList", "locationList", "userList"})
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("book_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//@NotBlank(message="{book_name_notblank}")
	private String name;
	
	//@NotBlank(message="{book_image_notblank}")
	private String image;
	
	@JsonProperty("ISBN")
	//@Column(unique=true)	ISBN is niet verplicht, dus constraint kan niet
	//@Range(min=1000000000000L, max=9999999999999L, message="{book_isbn_range}")
	//@ValidISBN
	private long isbn;
	
	//@Positive
	//@DecimalMin(value = "0.01", message="{book_price_min}")
	//@DecimalMax(value = "99.99", message="{book_price_max}")
	private double price;
	
	private int numVotes;
	
	//@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@ManyToMany
	private List<Author> authorList = new ArrayList<>();
	
	@OneToMany
	//@OneToMany(cascade=CascadeType.ALL)
	private List<Location> locationList = new ArrayList<>();
	
	@ManyToMany
	private List<User> userList = new ArrayList<>();
	
	public Book (String name, String image) {
		this.name = name;
		this.image = image;
		this.price = new SecureRandom().nextDouble(0.00, 100.00);
		//this.numVotes = new SecureRandom().nextInt(1,100);
	}
	
	public Book (String name, String image, long isbn) {
		this.name = name;
		this.image = image;
		this.isbn = isbn;
		this.price = new SecureRandom().nextDouble(20.00, 40.00);
		//this.numVotes = new SecureRandom().nextInt(1,100);
	}
	
	public Book (String name, String image, long isbn, double price) {
		this.name = name;
		this.image = image;
		this.isbn = isbn;
		this.price = price;
		//this.numVotes = 0;
	}
	
	public void addAuthor(Author author) {
		this.authorList.add(author);
	}
	
	public void addLocation(Location location) {
		this.locationList.add(location);
	}
	
	public void addUser(User user) {
		this.userList.add(user);
	}
	
	public void removeUser(User user) {
		this.userList.remove(user);
	}
	


}
