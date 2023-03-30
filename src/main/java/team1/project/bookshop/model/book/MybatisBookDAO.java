package team1.project.bookshop.model.book;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Book;
import team1.project.bookshop.exception.BookException;

@Repository
public class MybatisBookDAO implements BookDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("Book.selectAll");
	}

	public Book select(int book_idx) {
		return sqlSessionTemplate.selectOne("Book.select", book_idx);
	}

	public void insert(Book book) throws BookException{
		int result = sqlSessionTemplate.insert("Book.insert", book);
		if(result<1) {
			throw new BookException("책 등록 실패");
		}
	}
}
