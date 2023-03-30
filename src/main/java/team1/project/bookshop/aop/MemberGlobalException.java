package team1.project.bookshop.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.exception.AdminException;
import team1.project.bookshop.exception.MemberException;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class MemberGlobalException {
	
	@ExceptionHandler(MemberException.class)
	public ModelAndView handle(MemberException e){
		log.info("멤버 글로벌 예외");
		//에러페이지가 아닌 로그인폼 페이지로 보내버린다.
		ModelAndView mav = new ModelAndView("redirect:/member/login");
		mav.addObject("e", e);
		return mav;
	}
}
