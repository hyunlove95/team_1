package team1.project.bookshop.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Notice;
import team1.project.bookshop.model.notice.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//공지사항관리 등록 요청
	@GetMapping("/notice/registform")
	public ModelAndView getForm(HttpServletRequest request) {
		//3단계 
		List noticeList=noticeService.selectAll();
		
		//4단계 
		ModelAndView mav =new ModelAndView("admin/notice/regist");
		mav.addObject("noticeList", noticeList);
		
		return mav;
	}
	
	//공지사항관리 목록 요청
	@GetMapping("/notice/list")
	public ModelAndView getList(HttpServletRequest request) {
		//3단계 
		List noticeList = noticeService.selectAll();
		
		//4단계: jsp로 가져가야 하므로 결과저장 
		ModelAndView mav = new ModelAndView("admin/notice/list");
		mav.addObject("noticeList", noticeList);
		
		return mav;
	}
	
	//상세보기 요청 
	@GetMapping("/notice/detail")
	public ModelAndView getDetail(HttpServletRequest request, int notice_idx) {
		//3단계 
		Notice notice=noticeService.select(notice_idx);
		
		ModelAndView mav = new ModelAndView("admin/notice/detail");
		mav.addObject("notice", notice);
		
		return mav;
	}
	
	//수정요청 처리 
	@RequestMapping(value="/notice/edit", method=RequestMethod.POST)
	public ModelAndView edit(Notice notice) {
		//3단계: 
		noticeService.update(notice);
		
		//4단계:생략, 즉 상세보기를 재접속  redirect 
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/admin/notice/detail?notice_idx="+notice.getNotice_idx());  
		return mav;
	}
	
	//삭제요청 처리
	@RequestMapping(value="/notice/delete", method=RequestMethod.POST)
	public ModelAndView del(int notice_idx) {
		//3단계:일 시키기
		noticeService.delete(notice_idx);
		
		//4단계:생략
		return new ModelAndView("redirect:/admin/notice/list");
	}
}
