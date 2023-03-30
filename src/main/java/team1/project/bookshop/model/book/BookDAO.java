package team1.project.bookshop.model.book;

import java.util.List;

import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Book;

public interface BookDAO {
	public List selectAll();
	public Book select(int book_idx);
	public void insert(Book book);
}
