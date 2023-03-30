package team1.project.bookshop.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.exception.HashException;
import team1.project.bookshop.exception.MemberException;

@ControllerAdvice(annotations = Controller.class)
public class ShopGlobalException {
	
	@ExceptionHandler(HashException.class)
	public ModelAndView handle(HashException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
	
	@ExceptionHandler(MemberException.class)
	public ModelAndView handle(MemberException e) {
		ModelAndView mav=new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
}
