package team1.project.bookshop.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.exception.HashException;
import team1.project.bookshop.exception.MemberException;
import team1.project.bookshop.util.Message;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class RestShopGlobalException {

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Message> handle(MemberException e){
		Message message = new Message();
		message.setMsg("쇼핑몰 글로벌 예외 : "+e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	@ExceptionHandler(HashException.class)
	public ResponseEntity<Message> handle(HashException e){
		Message message = new Message();
		message.setMsg("쇼핑몰 글로벌 예외 : "+e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
}
