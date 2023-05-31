package domein;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authorities implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
    private User user;
	
	private String email;
    
    private String authority;
    
    public Authorities(String email, User user) {
    	this.user = user;
    	this.email = email;
    	this.authority = "ROLE_USER";
    }
    
    public Authorities(User user, String email, String authority) {
    	this.user = user;
    	this.email = email;
    	this.authority = authority;
    }

}
