package team1.project.bookshop.admin.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Admin;
import team1.project.bookshop.model.member.MemberService;


@Slf4j
@Controller
@RequestMapping(value = "/member")
public class AdminMemberController {

	@Autowired
	private MemberService memberService;
	
	/**회원목록 페이지로 이동*/
	@GetMapping("/list")
	public ModelAndView getMemberList(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		
		ModelAndView mav= new ModelAndView("admin/member/list");
		return mav;
	}
}
