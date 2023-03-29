package team1.project.bookshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Notice;
import team1.project.bookshop.model.notice.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//공지사항 요청 처리
	@GetMapping("/notice/list")
	public ModelAndView getList(HttpServletRequest request) {
		//3단계
		List noticeList=noticeService.selectAll();
		
		//4단계
		ModelAndView mav = new ModelAndView("bookshop/notice/list2");
		mav.addObject("noticeList", noticeList);
		
		return mav;
	}
	
	//공지사항 상세보기 요청처리
	@GetMapping("/notice/detail")
	public ModelAndView getDetail(HttpServletRequest request, int notice_idx) {
		//3단계
		Notice notice=noticeService.select(notice_idx);
		
		//4단계
		ModelAndView mav = new ModelAndView("bookshop/notice/detail");
		mav.addObject("notice", notice);

		return mav;
	}
}
