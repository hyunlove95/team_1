package team1.project.bookshop.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.model.book.BookService;
import team1.project.bookshop.util.PageManager2;

@Controller
public class ClientController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookService bookService;
	
	
	@GetMapping("/book/search")
	public ModelAndView search() {
		
		List bookList = bookService.selectAll();
		PageManager2 pageManager = new PageManager2();
		pageManager.init(bookList);
		
		
		ModelAndView mav = new ModelAndView("client/booksearch2");
		mav.addObject("bookList", bookList);
		mav.addObject("pageManager", pageManager);
		
		return mav;
	}
	
	@GetMapping("/")
	public ModelAndView getMain() {
		return new ModelAndView("bookshop/index");
	}
}
