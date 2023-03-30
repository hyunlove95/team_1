package team1.project.bookshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Book;
import team1.project.bookshop.model.book.BookService;
import team1.project.bookshop.util.PageManager2;

@Controller
public class BookController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookService bookService;

	@GetMapping("/bookdetail")
	public ModelAndView getDetail(int book_idx, HttpServletRequest request) {
		
		Book book =bookService.select(book_idx);
		
		ModelAndView mav = new ModelAndView("bookshop/book/bookdetail");
		mav.addObject("book", book);
		
		return mav;
	}
	


	
	
	
}
