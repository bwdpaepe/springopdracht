package com.springBoot_opdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domein.Author;
import domein.Authorities;
import domein.Book;
import domein.Location;
import domein.User;
import repository.AuthorRepository;
import repository.AuthoritiesRepository;
import repository.BookRepository;
import repository.LocationRepository;
import repository.UserRepository;

@Component
public class InitDataConfig implements CommandLineRunner {
	
	@Autowired 
	UserRepository uRepository;
	
	@Autowired
	AuthoritiesRepository aRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	LocationRepository lRepository;
	
	@Autowired 
	BookRepository bookRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		// fill database with 3 users and 1 admin
		uRepository.save(new User("sandra@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("sandra@example.com", uRepository.findByEmail("sandra@example.com")));
		uRepository.save(new User("tania@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("tania@example.com", uRepository.findByEmail("tania@example.com")));
		uRepository.save(new User("jurgen@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("jurgen@example.com", uRepository.findByEmail("jurgen@example.com")));
		uRepository.save(new User("ann@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities(uRepository.findByEmail("ann@example.com"), "ann@example.com", "ROLE_ADMIN"));
		
		// fill database with authors
		authorRepository.save(new Author("Shakespeare, William"));
		authorRepository.save(new Author("Forster, E. M. (Edward Morgan)"));
		authorRepository.save(new Author("Eliot, George"));
		authorRepository.save(new Author("Melville, Herman"));
		authorRepository.save(new Author("Von Arnim, Elizabeth"));
		authorRepository.save(new Author("Gaskell, Elizabeth Cleghorn"));
		authorRepository.save(new Author("Smollett, T. (Tobias)"));
		authorRepository.save(new Author("Fielding, Henry"));
		authorRepository.save(new Author("Dumas, Alexandre"));
		authorRepository.save(new Author("Wagner, Richard"));
		authorRepository.save(new Author("Alcott, Louisa May"));
		authorRepository.save(new Author("Montgomery, L. M. (Lucy Maud)"));
		authorRepository.save(new Author("Shelley, Mary Wollstonecraft"));
		authorRepository.save(new Author("Austen, Jane"));
		authorRepository.save(new Author("Rizal, José"));
		authorRepository.save(new Author("Carroll, Lewis"));
		authorRepository.save(new Author("Fitzgerald, F. Scott (Francis Scott)"));
		authorRepository.save(new Author("Wilde, Oscar"));
		authorRepository.save(new Author("Ibsen, Henrik"));
		authorRepository.save(new Author("Dickens, Charles"));	
		
		// fill database with locations
		String alphabet[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		int counter = 0;
		for(String alpha: alphabet) {
			lRepository.save(new Location("location" + alpha, 51 + counter, 102 + counter));
			counter++;
		}
		
		
		// fill database with books
		Book book;
		
		book = new Book("Romeo and Juliet", "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Shakespeare, William"));
		book.addLocation(lRepository.findByName("locationA"));
		book.addLocation(lRepository.findByName("locationU"));
		book.addLocation(lRepository.findByName("locationV"));
		bookRepository.save(book);
		book = new Book("A Room with a View", "https://www.gutenberg.org/cache/epub/2641/pg2641.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Forster, E. M. (Edward Morgan)"));
		book.addLocation(lRepository.findByName("locationB"));
		book.addLocation(lRepository.findByName("locationW"));
		bookRepository.save(book);
		book = new Book("Middlemarch", "https://www.gutenberg.org/cache/epub/145/pg145.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Eliot, George"));
		book.addLocation(lRepository.findByName("locationC"));
		book.addLocation(lRepository.findByName("locationX"));
		bookRepository.save(book);
		book = new Book("Moby Dick; Or, The Whale", "https://www.gutenberg.org/cache/epub/2701/pg2701.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Melville, Herman"));
		book.addLocation(lRepository.findByName("locationD"));
		book.addLocation(lRepository.findByName("locationY"));
		bookRepository.save(book);
		book = new Book("The Enchanted April", "https://www.gutenberg.org/cache/epub/16389/pg16389.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Von Arnim, Elizabeth"));
		book.addLocation(lRepository.findByName("locationE"));
		book.addLocation(lRepository.findByName("locationZ"));
		bookRepository.save(book);
		book = new Book("Cranford", "https://www.gutenberg.org/cache/epub/394/pg394.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Gaskell, Elizabeth Cleghorn"));
		book.addLocation(lRepository.findByName("locationF"));
		bookRepository.save(book);
		book = new Book("The Adventures of Ferdinand Count Fathom — Complete", "https://www.gutenberg.org/cache/epub/6761/pg6761.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Smollett, T. (Tobias)"));
		book.addLocation(lRepository.findByName("locationG"));
		bookRepository.save(book);
		book = new Book("History of Tom Jones, a Foundling", "https://www.gutenberg.org/cache/epub/6593/pg6593.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Fielding, Henry"));
		book.addLocation(lRepository.findByName("locationH"));
		bookRepository.save(book);
		book = new Book("Twenty Years After", "https://www.gutenberg.org/cache/epub/1259/pg1259.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Dumas, Alexandre"));
		book.addLocation(lRepository.findByName("locationI"));
		bookRepository.save(book);
		book = new Book("My Life — Volume 1", "https://www.gutenberg.org/cache/epub/5197/pg5197.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Wagner, Richard"));
		book.addLocation(lRepository.findByName("locationJ"));
		bookRepository.save(book);
		book = new Book("Little Women; Or, Meg, Jo, Beth, and Amy", "https://www.gutenberg.org/cache/epub/37106/pg37106.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Alcott, Louisa May"));
		book.addLocation(lRepository.findByName("locationK"));
		bookRepository.save(book);
		book = new Book("The Blue Castle: a novel", "https://www.gutenberg.org/cache/epub/67979/pg67979.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Montgomery, L. M. (Lucy Maud)"));
		book.addLocation(lRepository.findByName("locationL"));
		bookRepository.save(book);
		book = new Book("Frankenstein; Or, The Modern Prometheus", "https://www.gutenberg.org/cache/epub/84/pg84.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Shelley, Mary Wollstonecraft"));
		book.addLocation(lRepository.findByName("locationM"));
		bookRepository.save(book);
		book = new Book("Pride and Prejudice", "https://www.gutenberg.org/cache/epub/1342/pg1342.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Austen, Jane"));
		book.addLocation(lRepository.findByName("locationN"));
		bookRepository.save(book);
		book = new Book("Noli Me Tangere", "https://www.gutenberg.org/cache/epub/20228/pg20228.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Rizal, José"));
		book.addLocation(lRepository.findByName("locationO"));
		bookRepository.save(book);
		book = new Book("Alice's Adventures in Wonderland", "https://www.gutenberg.org/cache/epub/11/pg11.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Carroll, Lewis"));
		book.addLocation(lRepository.findByName("locationP"));
		bookRepository.save(book);
		book = new Book("The Great Gatsby", "https://www.gutenberg.org/cache/epub/64317/pg64317.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Fitzgerald, F. Scott (Francis Scott)"));
		book.addLocation(lRepository.findByName("locationQ"));
		bookRepository.save(book);
		book = new Book("The Importance of Being Earnest: A Trivial Comedy for Serious People", "https://www.gutenberg.org/cache/epub/844/pg844.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Wilde, Oscar"));
		book.addLocation(lRepository.findByName("locationR"));
		bookRepository.save(book);
		book = new Book("A Doll's House : a play", "https://www.gutenberg.org/cache/epub/2542/pg2542.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Ibsen, Henrik"));
		book.addLocation(lRepository.findByName("locationS"));
		bookRepository.save(book);
		book = new Book("A Tale of Two Cities", "https://www.gutenberg.org/cache/epub/98/pg98.cover.medium.jpg");
		book.addAuthor(authorRepository.findByName("Dickens, Charles"));
		book.addLocation(lRepository.findByName("locationT"));
		bookRepository.save(book);
		
		
	}

}
