package team1.project.bookshop.admin.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team1.project.bookshop.domain.Notice;
import team1.project.bookshop.exception.NoticeException;
import team1.project.bookshop.model.notice.NoticeService;
import team1.project.bookshop.util.Message;

//상품과 관련된 요청을 처리하는 하위 컨트롤러 

@RestController
@RequestMapping("/rest")
public class RestNoticeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//서비스의 존재가 없다면, 컨트롤러계층과 모델 계층의 구분이 모호해진다..
	//추후 모델을 완전히 재사용을 위해 분리시키려할때 분리가 불가능하다..
	@Autowired
	private NoticeService noticeService;
	
	
	//공지 등록 요청 처리
	@RequestMapping(value="/notice/regist", method=RequestMethod.POST)
	public ResponseEntity<Message> regist(Notice notice, HttpServletRequest request) {
		logger.info("notice is "+notice);
				
		//3단계 
		noticeService.insert(notice);
		
		Message message = new Message();
		message.setMsg("공지등록 성공");
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
		
	@ExceptionHandler(NoticeException.class)
	public ResponseEntity<Message> handle(NoticeException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}

	
}