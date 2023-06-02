package perform;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import domein.Book;
import reactor.core.publisher.Mono;

public class PerformRestExample {

	private final String SERVER_URI = "http://localhost:8080/rest";
	private WebClient webClient = WebClient.create();

	public PerformRestExample() throws Exception {
		System.out.println("\n------- GET BOOKS BY AUTHOR NAME Shakespeare, William -------");
		getBooksFromAuthor("Shakespeare, William");
		
		System.out.println("\n------- GET BOOK BY ISBN 9787698540261 ------- ");
		getBookByISBN("9787698540261");
	}

	private void getBooksFromAuthor(String name_author) {
		webClient.get().uri(SERVER_URI + "/books?author_name=" + name_author).retrieve()
        .bodyToFlux(Book.class)
        .flatMap(book -> {
            printEmpData(book);
            return Mono.empty();
        })
        .blockLast();
	}

	private void getBookByISBN(String isbn)
	{
		webClient.get()
	    .uri(SERVER_URI + "/book?isbn=" + isbn)
	    .retrieve()
	    .bodyToMono(Book.class)
	    .doOnSuccess(emp -> printEmpData(emp))
	    .block();
	}
	
		
	private void printEmpData(Book book) {
		System.out.printf("ID=%s, Name=%s, ISBN=%s%n", 
				book.getId(), book.getName(), book.getIsbn());
	}
	


}
