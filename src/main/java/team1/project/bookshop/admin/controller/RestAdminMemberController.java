package team1.project.bookshop.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.model.member.MemberService;

/** :/admin/rest/member */
@Slf4j
@RestController
@RequestMapping(value = "/rest/member")
public class RestAdminMemberController {

	@Autowired
	private MemberService memberService;
	
	/** admin/main에서 가입자 수 보여주기 */
	@GetMapping("/count")
	public ResponseEntity<Integer> countMember(HttpServletRequest request){
		int countMember =memberService.selectCountMember();
		ResponseEntity<Integer> entity = new ResponseEntity<Integer>(countMember, HttpStatus.OK);
		return entity;
	}
	
	/**admin의 멤버 페이지에서 회원 보여주기*/
	@GetMapping("/")
	public List getList(HttpServletRequest request) {
		return memberService.selectAll();
	}
	
	/**admin의 멤버 페이지에서 회원 검색하기*/
	@PostMapping("/search")
	public List getList(HttpServletRequest request, String keyword) {
		log.info("keyword = {}", keyword);
		return memberService.search(keyword);
	}
	
}
