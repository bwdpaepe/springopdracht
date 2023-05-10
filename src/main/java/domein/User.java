package domein;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.Util;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "email")
@ToString(of = "email")
public class User implements Serializable {
	
	@Transient
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String hashedPassword;
    
    private Role role;
    
    public User(String email, String password, Role role) {
		this.email = email;
		setHashedPassword(password);
		this.role = role;
	}
	
	private void setHashedPassword(String password) {
		if(passwordEncoder != null) {
			this.hashedPassword = passwordEncoder.encode(password);
		}
		else {
			this.hashedPassword = Util.encoder().encode(password);
		}
	}
    
    

}
