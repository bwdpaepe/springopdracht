package domein;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//@NotBlank(message="{location_name_notblank}")
	//@Pattern(regexp="^[a-zA-Z]*", message="{location_name_only_alpha}")
	private String name;
	//@Min(value = 50, message="{location_code1_min}")
	//@Max(value= 300, message="{location_code1_max}")
	private int code1;
	//@Min(value = 50, message="{location_code2_min}")
	//@Max(value= 300, message="{location_code2_max}")
	private int code2;
	
	public Location(String name, int code1, int code2) {
		this.name = name;
		this.code1 = code1;
		this.code2 = code2;
	}

}
