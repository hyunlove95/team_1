package team1.project.bookshop.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.exception.AdminException;
import team1.project.bookshop.util.Message;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class RestAdminGlobalException {
	
	@ExceptionHandler(value = {AdminException.class})
	public ResponseEntity<Message> handle(RuntimeException e){
		log.info("관리자 Rest 글로벌 예외에서 감지한 회원등록 실패");
		
		Message message = new Message();
		message.setMsg("Admin Rest 글로벌 예외 : " + e.getMessage());
		
		ResponseEntity entity=new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
