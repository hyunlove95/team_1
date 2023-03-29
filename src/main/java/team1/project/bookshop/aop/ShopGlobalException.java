package team1.project.bookshop.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.exception.EmailException;
import team1.project.bookshop.exception.HashException;
import team1.project.bookshop.exception.MemberException;

//쇼핑몰관 관련된 글로벌(전역적) 예외객체 정의하되,
//jsp로 에러 결과를 보여줘야 하므로, 반환값이 ModelAndView 가 되어야함
@ControllerAdvice(annotations = Controller.class)
public class ShopGlobalException {
	
	@ExceptionHandler(HashException.class)
	public ModelAndView handle(HashException e) {
		ModelAndView mav=new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
	@ExceptionHandler(EmailException.class)
	public ModelAndView handle(EmailException e) {
		ModelAndView mav=new ModelAndView("shop/error/result");
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
