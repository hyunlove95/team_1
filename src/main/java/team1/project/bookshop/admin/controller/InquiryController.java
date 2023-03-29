package team1.project.bookshop.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Inquiry;
import team1.project.bookshop.model.inquiry_category.Inquiry_categoryService;
import team1.project.bookshop.model.inquiry.InquiryService;

//1:1 문의 게시판 컨트롤러
@Controller
public class InquiryController {
	
	@Autowired
	private Inquiry_categoryService inquiry_categoryService;
	
	@Autowired
	private InquiryService inquiryService;
	
	

	@GetMapping("/inquiry/list")
	public ModelAndView getList(HttpServletRequest request) {
		//3단계 
		List inquiryList = inquiryService.selectAll();
		
		//4단계: jsp로 가져가야 하므로 결과저장 
		ModelAndView mav = new ModelAndView("admin/inquiry/list2");
		mav.addObject("inquiryList", inquiryList);
		
		return mav;
	}
	
	//상세보기 요청 
	@GetMapping("/inquiry/detail")
	public ModelAndView getDetail(HttpServletRequest request, int inquiry_idx) {
		//3단계 
		List inquiry_categoryList = inquiry_categoryService.selectAll();
		Inquiry inquiry=inquiryService.select(inquiry_idx);
		
		ModelAndView mav = new ModelAndView("admin/inquiry/detail");
		mav.addObject("inquiry_categoryList", inquiry_categoryList);
		mav.addObject("inquiry", inquiry);
		
		return mav;
	}
}
