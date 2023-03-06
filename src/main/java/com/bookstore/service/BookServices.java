package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.utils.DateUtils;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class BookServices {
	private HttpServletRequest request;
	private HttpServletResponse response; 
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	
	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.bookDAO = new BookDAO(entityManager);
		this.categoryDAO = new CategoryDAO(entityManager);
		this.request = request;
		this.response = response;
	}
	
	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}
	
	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		
		request.setAttribute("listBooks", listBooks);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "book_list.jsp";

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void deleteBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		
		bookDAO.delete(bookId);
		
		String message = "Xóa thành công!.";
		listBooks(message);
	}
	
	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
        request.setAttribute("listCategory", listCategory);
		
		String newPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createBook() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.findByTitle(title);
		
		if (existBook != null) {
			String message = "Không thể tạo sách có tiêu đề " + title + " bởi vì tiêu đề này đã tồn tại!";
			listBooks(message);
			return;
		}
		
		Book newBook = new Book();
		readBookFields(newBook);
		
		Book createdBook = bookDAO.create(newBook);
		
		if (createdBook.getBookId() > 0) {
			String message = "Thêm thành công!";
			request.setAttribute("message",message);
			listBooks(message);
		}
	}
	
	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);
		
		String editPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}
	
	public void readBookFields(Book book) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		
		Date publishDate = null;
		
		try {
			publishDate = DateUtils.getDateFormatted(request.getParameter("publishDate").replace('-', '/'));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Lỗi format date (format is dd/MM/yyyy)");
		}
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(publishDate);
		
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);
		
		book.setPrice(price);
		
		Part part = request.getPart("bookImage");
		
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];
			
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			book.setImage(imageBytes);
		}
	}
	
	public void updateBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.get(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);
		
		if (bookByTitle != null && !existBook.equals(bookByTitle)) {
			String message = "Không thể cập nhật vì đã tồn tại tiêu đề: " + title;
			listBooks(message);
			return;
		}
		
		readBookFields(existBook);
		
		bookDAO.update(existBook);
		
		String message = "Cập nhật thành công!.";
		listBooks(message);	
	}
	
	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		Category category = categoryDAO.get(categoryId);
		
		request.setAttribute("listBooks", listBooks);
		request.setAttribute("category", category);
		
		String listPage = "client/books_list_by_category.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

}
