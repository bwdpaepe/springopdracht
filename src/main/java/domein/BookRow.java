package domein;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookRow {
	
	private final Long id;
	private final String name;
	private final String image;
	private final String author1;
	private final String author2;
	private final String author3;
	
	

}
