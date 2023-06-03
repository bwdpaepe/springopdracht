package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NamedQueries({
	@NamedQuery(name = "User.booksOfUser",
	query = "SELECT u.bookList FROM User u WHERE :Id = u.id")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = {"id", "password"})
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;

    private String password;
    
    //private String role;
    
    private int enabled;
    
    private int maxVotes;
    
    @ManyToMany (mappedBy="userList")
    private List<Book> bookList = new ArrayList<>();

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		//this.role = "ROLE_USER";
		this.enabled = 1;
		this.maxVotes = new SecureRandom().nextInt(5, 10);
	}
	
	public void addBook(Book book) {
		this.bookList.add(book);
	}
	
	public void removeBook(Book book) {
		this.bookList.remove(book);
	}
}
