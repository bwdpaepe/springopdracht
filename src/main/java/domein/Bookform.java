package domein;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validator.ValidISBN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookform {
	
	@NotBlank(message="{book_name_notblank}")
	private String name;
	
	@NotBlank(message="{book_image_notblank}")
	private String image;
	
	@Range(min=1000000000000L, max=9999999999999L, message="{book_isbn_range}")
	@ValidISBN
	private long isbn;
	
	@Positive
	@DecimalMin(value = "0.01", message="{book_price_min}")
	@DecimalMax(value = "99.99", message="{book_price_max}")
	private double price;
	
	@NotNull
	private Author author1;
	
	private Author author2;
	
	private Author author3;
	
	@NotBlank(message="{location_name_notblank}")
	@Pattern(regexp="^[a-zA-Z]*", message="{location_name_only_alpha}")
	private String locationName1;
	@Min(value = 50, message="{location_code1_min}")
	@Max(value= 300, message="{location_code1_max}")
	private int locationCode11;
	@Min(value = 50, message="{location_code2_min}")
	@Max(value= 300, message="{location_code2_max}")
	private int locationCode12;
	
	@NotBlank(message="{location_name_notblank}")
	@Pattern(regexp="^[a-zA-Z]*", message="{location_name_only_alpha}")
	private String locationName2;
	@Min(value = 50, message="{location_code1_min}")
	@Max(value= 300, message="{location_code1_max}")
	private int locationCode21;
	@Min(value = 50, message="{location_code2_min}")
	@Max(value= 300, message="{location_code2_max}")
	private int locationCode22;
}
