package team1.project.bookshop.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team1.project.bookshop.util.Message;
import team1.project.bookshop.domain.Inquiry_category;
import team1.project.bookshop.exception.Inquiry_categoryException;
import team1.project.bookshop.model.inquiry_category.Inquiry_categoryService;

@RestController
@RequestMapping("/rest")
public class RestInquiry_categoryController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private Inquiry_categoryService inquiry_categoryService;
	
	//등록요청 처리 
	@PostMapping("/inquiry_category")
	public Message regist(HttpServletRequest request, Inquiry_category inquiry_category) {
		//3단계:
		inquiry_categoryService.insert(inquiry_category);
		Message message = new Message();
		message.setMsg("카테고리 등록 성공");
		return message;
	}

	//목록요청 처리 
	@GetMapping("/inquiry_category")
	public List<Inquiry_category> getList(HttpServletRequest request){
		//3단계 
		return inquiry_categoryService.selectAll();
	}
	
	
	//수정요청 처리 
	//@ResponseBody   :  자바객체-->  JSON 
	//@RequestBody  :   JSON --> 자바객체
	@PutMapping("/inquiry_category")
	public ResponseEntity<Message> edit(HttpServletRequest request, @RequestBody Inquiry_category inquiry_category){
		logger.info("inquiry_category is "+inquiry_category);
		//3단계: 
		inquiry_categoryService.update(inquiry_category);
		
		//결과 메시지 처리
		Message message = new Message();		
		message.setMsg("수정성공");
		ResponseEntity<Message> entity=null;
		entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
	
	//삭제요청 처리 
	//파라미터가 평소( ?변수명=값)와는 틀리게 디렉토리의 일부로  전달된다..
	//따라서 스프링측에서 경로에 포함된 파라미터를 변수로 인식할 필요가 있다..
	//이 문제를 가능하게 해주는 코드  경로에 변수부분을{변수}표현,
	//@PathVariable
	@DeleteMapping("/inquiry_category/{inquiry_category_idx}")
	public ResponseEntity<Message> del(HttpServletRequest request, @PathVariable int inquiry_category_idx){
		logger.info("넘겨받은  inquiry_category_idx is "+inquiry_category_idx);
		//3단계: 일시키기
		inquiry_categoryService.delete(inquiry_category_idx);
		
		//결과 메시지 처리
		Message message = new Message();		
		message.setMsg("삭제성공");
		ResponseEntity<Message> entity=null;
		entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;

	}
	
	
	
	@ExceptionHandler(Inquiry_categoryException.class)
	public ResponseEntity<Message> handle(HttpServletRequest request, Inquiry_categoryException e) {
		//HTTP 응답정보를 보다 세밀하게 구성하고 싶다면.. 
		//Http 응답 메시지를 구성할 수 있는 객체를 지원함..
		Message message = new Message();		
		message.setMsg(e.getMessage());
		
		ResponseEntity<Message> entity=null;
		entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
