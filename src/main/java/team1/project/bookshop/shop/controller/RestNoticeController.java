package team1.project.bookshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team1.project.bookshop.exception.NoticeException;
import team1.project.bookshop.model.notice.NoticeService;
import team1.project.bookshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestNoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public List getList(HttpServletRequest request) {
		return noticeService.selectAll();
	}
	
	@ExceptionHandler(NoticeException.class)
	public ResponseEntity<Message> handle(NoticeException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
