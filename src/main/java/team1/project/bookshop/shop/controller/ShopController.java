package team1.project.bookshop.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.model.book.BookService;

@Controller
public class ShopController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/shop")
	public ModelAndView getShop() {
		List bookList = bookService.selectAll();
		
		ModelAndView mav = new ModelAndView("shop/shop");
		mav.addObject("bookList", bookList);
		
		return mav;
	}
	
	@GetMapping("/shop_detail")
	public ModelAndView getShop_detail() {
		return new ModelAndView("shop/shop_detail");
	}
}
