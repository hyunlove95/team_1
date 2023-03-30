package team1.project.bookshop.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Admin;
import team1.project.bookshop.exception.AdminException;
import team1.project.bookshop.model.admin.AdminService;


@Slf4j
@Controller
public class AdminController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminService adminService;
	
	/**로그인 폼*/
	@GetMapping("/login")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		return new ModelAndView("admin/login/loginform");
	}
	
	/**로그인 처리(동기)*/
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request, Admin admin) {
		Admin admin_login=adminService.login(admin);
		
		//admin을 세션에 담기
		HttpSession session=request.getSession();
		session.setAttribute("admin", admin_login);
		
		ModelAndView mav= new ModelAndView("redirect:/admin/main");
		return mav;
	}
	
	/**로그인 처리 후 메인으로 이동*/
	@GetMapping("/main")
	public ModelAndView getMain(HttpServletRequest request) {
		log.info("관리자 main 호출");
		//로그인 인증 여부를 따져봐야 한다...
		HttpSession session =request.getSession();
		Admin admin =(Admin)session.getAttribute("admin");
		log.info("admin = {}", admin);
		ModelAndView mav = new ModelAndView();
		if(admin==null) {
			throw new AdminException("로그인이 필요한 서비스입니다.");
		}else {
			mav.setViewName("admin/index");
			mav.addObject("admin", admin);
		}
		return mav;
	}
	
	/**로그아웃 → 로그인페이지로 이동*/
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/admin/login");
	}

}