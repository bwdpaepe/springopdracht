package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message="{book_name_notblank}")
	private String name;
	
	@NotBlank(message="{book_image_notblank}")
	private String image;
	
	@Range(min=1000000000000L, max=9999999999999L)
	private long isbn;
	
	@Positive
	private double price;
	
	@ManyToMany
	private List<Author> authorList = new ArrayList<>();
	
	@OneToMany
	private List<Location> locationList = new ArrayList<>();
	
	public Book (String name, String image) {
		this.name = name;
		this.image = image;
		this.price = new SecureRandom().nextDouble(20.00, 40.00);
		//this.authorSet = authorSet;
	}
	
	public void addAuthor(Author author) {
		this.authorList.add(author);
	}
	
	public void addLocation(Location location) {
		this.locationList.add(location);
	}
	


}
