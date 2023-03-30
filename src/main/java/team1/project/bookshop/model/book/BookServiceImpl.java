package team1.project.bookshop.model.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Book;
import team1.project.bookshop.exception.BookException;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDAO bookDAO;
	
	public List selectAll() {
		return bookDAO.selectAll();
	}

	public Book select(int book_idx) {
		return bookDAO.select(book_idx);
	}

	public void insert(Book book) throws BookException{
		bookDAO.insert(book);
	}
}
