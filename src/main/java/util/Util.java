package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Util {
	
	public static PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

}
