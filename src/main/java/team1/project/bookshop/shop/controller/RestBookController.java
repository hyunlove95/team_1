package team1.project.bookshop.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team1.project.bookshop.domain.Book;
import team1.project.bookshop.model.book.BookService;
import team1.project.bookshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestBookController {
	
	@Autowired
	private BookService bookService;
	
	
	
	@PostMapping("/editbook")
	public ResponseEntity<Message> editBook(Book book, HttpServletRequest request){
		
		
		
		
		Message msg = new Message();
		msg.setMsg("수정성공입니다!");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(msg, HttpStatus.OK);
		return entity;
	}
	
	
}
