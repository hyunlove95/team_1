package team1.project.bookshop.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Inquiry_categoryController {
	
	//카테고리관리 메인 요청
	@GetMapping("/inquiry_category/main")
	public ModelAndView getMain(HttpServletRequest request) {
		return new ModelAndView("admin/inquiry_category/category_main");
	}
	
}
