package team1.project.bookshop.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.exception.AdminException;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class AdminGlobalException {
	
	@ExceptionHandler(AdminException.class)
	public ModelAndView handle(AdminException e){
		log.info("관리자 글로벌 예외");
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e", e);
		return mav;
	}
}
