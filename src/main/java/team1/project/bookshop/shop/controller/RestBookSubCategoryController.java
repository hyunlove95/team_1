package team1.project.bookshop.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import team1.project.bookshop.model.subcategory.BookSubCategoryService;
import team1.project.bookshop.util.Message;


@RestController
@RequestMapping("/rest")
public class RestBookSubCategoryController {
	
	@Autowired
	private BookSubCategoryService bookSubCategoryService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping("/booksubcategory")
	public List getList() {
		
		return bookSubCategoryService.selectAll();
	}

	@GetMapping("/booksubcategory/selectlist")
	public List selectList(int bookTopCategory_idx) {
		
		return bookSubCategoryService.selectByTopCategory(bookTopCategory_idx);
	}
	
	
}
