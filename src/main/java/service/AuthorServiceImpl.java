package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Author;
import repository.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {
	
	@Autowired 
	AuthorRepository authorRepository;

	@Override
	public List<Author> findByBookId(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> findAll() {
		return (List<Author>) authorRepository.findAll();
	}

}
