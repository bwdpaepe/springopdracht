package exceptions;

public class BookNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(Long isbn) {
	    super(String.format("Could not find book %s", isbn));
	  }

}
