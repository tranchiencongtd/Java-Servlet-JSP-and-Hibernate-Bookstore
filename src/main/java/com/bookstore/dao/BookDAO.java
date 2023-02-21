package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Book;

import jakarta.persistence.EntityManager;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Book create(Book book) {
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		return super.update(book);
	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNameQuery("Book.findAll");
	}
	
	@Override
	public long count() {
		return super.countWithNameQuery("Book.countAll");
	}
	
	public Book findByTitle(String title) {
		List<Book> result = super.findWithNameQuery("Book.findByTitle", "title", title);
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
}
