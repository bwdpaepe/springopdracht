package domein;

import org.hibernate.validator.constraints.Range;

import jakarta.annotation.Nullable;
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
import validator.RangeISBN;
import validator.ValidISBN;
import validator.ValidLocationCode;
import validator.ValidLocationName;
import validator.ValidPrice;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
	
	@NotBlank(message="{book_name_notblank}")
	private String name;
	
	@NotBlank(message="{book_image_notblank}")
	private String image;
	
	//@Range(min=1000000000000L, max=9999999999999L, message="{book_isbn_range}")  werkt niet, veld mag null zijn
	//@Nullable
	@RangeISBN
	@ValidISBN
	private long isbn;
	
	//@DecimalMin(value = "0.01", message="{book_price_min}")     werkt niet, veld mag null zijn
	//@DecimalMax(value = "99.99", message="{book_price_max}")    werkt niet, veld mag null zijn
	//@Nullable
	@ValidPrice
	private double price;
	
	@NotNull
	private Author author1;
	
	@Nullable
	private Author author2;
	
	@Nullable
	private Author author3;
	
	@NotBlank(message="{location_name_notblank}")
	@Pattern(regexp="^[a-zA-Z]*", message="{location_name_only_alpha}")
	private String locationName1;
	@NotNull(message="{location_code1_notblank}")
	@Min(value = 50, message="{location_code1_min}")
	@Max(value= 300, message="{location_code1_max}")
	private int locationCode11;
	@NotNull(message="{location_code2_notblank}")
	@Min(value = 50, message="{location_code2_min}")
	@Max(value= 300, message="{location_code2_max}")
	private int locationCode12;
	
	//@Pattern(regexp="^[a-zA-Z]*", message="{location_name_only_alpha}")
	//@Nullable
	@ValidLocationName
	private String locationName2;
	//@Min(value = 50, message="{location_code1_min}")
	//@Max(value= 300, message="{location_code1_max}")
	//@Nullable
	@ValidLocationCode
	private int locationCode21;
	//@Min(value = 50, message="{location_code2_min}")
	//@Max(value= 300, message="{location_code2_max}")
	//@Nullable
	@ValidLocationCode
	private int locationCode22;
	
	//@Pattern(regexp="^[a-zA-Z]*", message="{location_name_only_alpha}")
	//@Nullable
	@ValidLocationName
	private String locationName3;
	//@Min(value = 50, message="{location_code1_min}")
	//@Max(value= 300, message="{location_code1_max}")
	//@Nullable
	@ValidLocationCode
	private int locationCode31;
	//@Min(value = 50, message="{location_code2_min}")
	//@Max(value= 300, message="{location_code2_max}")
	//@Nullable
	@ValidLocationCode
	private int locationCode32;
}
