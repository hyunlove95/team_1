package team1.project.bookshop.shop.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.MemberForm;
import team1.project.bookshop.domain.Info;
import team1.project.bookshop.domain.LoginForm;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.domain.NewPassCheckForm;
import team1.project.bookshop.model.member.MemberService;
import team1.project.bookshop.sns.GoogleLogin;
import team1.project.bookshop.sns.KakaoLogin;
import team1.project.bookshop.sns.NaverLogin;
import team1.project.bookshop.util.ErrorBinder;
import team1.project.bookshop.util.MemberConverter;
import team1.project.bookshop.util.Message;
import team1.project.bookshop.util.NewPassChecker;

@Slf4j
@RestController
@RequestMapping("/rest/member")
public class RestMemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NewPassChecker newPassChecker;
	
	@Autowired
	private GoogleLogin googleLogin;
	
	@Autowired
	private KakaoLogin kakaoLogin;
	
	@Autowired
	private NaverLogin naverLogin;
	
	@Autowired
	private ErrorBinder errorBinder;
	
	/**id 중복검사하기 OK*/
	@PostMapping("/idCheck")
	public ResponseEntity<Message> idCheck(HttpServletRequest request, String id){
		log.info("Controller의 id : "+ id);
		int result=memberService.idCheck(id);
		
		Message message=new Message();
		if(result==0) {
			message.setMsg("중복되지 않는 ID입니다.");
		}else {
			message.setMsg("중복되는 ID입니다.");
		}	
		ResponseEntity entity = new ResponseEntity<Message>	(message, HttpStatus.OK);	
		return entity;
	}
	
	/**회원가입하기 OK*/
	@PostMapping("/join")
	public ResponseEntity<?> regist(HttpServletRequest request, @Validated MemberForm memberForm, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			JSONObject jsonObject= errorBinder.bindError(bindingResult);
			ResponseEntity entity = new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
			return entity;
		}
		log.info("비밀번호는 {}입니다..",memberForm.getPass());
		log.info("아이디는 {}입니다..",memberForm.getId());
		log.info("주소는 {}입니다..",memberForm.getAddress());
		log.info("상세주소는 {}입니다..",memberForm.getAddress_detail());
		log.info("이메일 {}입니다..",memberForm.getEmail());
		log.info("전화번호는 {}입니다..",memberForm.getPhone_number());
		
		memberService.regist(memberForm);
		Message message = new Message();
		message.setMsg("회원가입 성공");
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}

	/**회원정보 페이지에서 회원정보 수정*/
	
	/**update 시에 그 다음부터 info만 떠버림.. -> sns 번호가 0이 된다.*/
	@PostMapping("/update")
	public ResponseEntity<?> update(HttpServletRequest request, @Validated MemberForm memberForm, BindingResult bindingResult) {
//		log.info("비밀번호는 {}입니다..",memberForm.getPass());
//		log.info("아이디는 {}입니다..",member.getId());
//		log.info("주소는 {}입니다..",member.getAddress());
//		log.info("상세주소는 {}입니다..",member.getAddress_detail());
//		log.info("이메일 {}입니다..",member.getEmail());
//		log.info("전화번호는 {}입니다..",member.getPhone_number());
		
		if(bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			JSONObject jsonObject= errorBinder.bindError(bindingResult);
			ResponseEntity entity = new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
			return entity;
		}
		memberService.update(memberForm);
		
		/**회원정보를 수정했으니, 세션에서도 member를 다시 업데이트 해줘야 한다..*/
	
		HttpSession session=request.getSession();
		session.setAttribute("member", memberForm);
		
		Message message = new Message();
		message.setMsg("회원정보 수정 성공");
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}

	
	/**memberform 대신 member_idx 회원탈퇴처럼 체크하기*/
	@PostMapping("/pass_check")	
	public ResponseEntity<?> passCheck(HttpServletRequest request, LoginForm loginForm){
		
		Message message=new Message();
		ResponseEntity entity = new ResponseEntity<Message>	(message, HttpStatus.OK);
		
		HttpSession session =request.getSession();
		MemberForm sessionMember = (MemberForm)session.getAttribute("member");
		
		/**여기서 null이 뜨면 NullPointerException*/
//		MemberForm memberForm = memberService.loginCheck(loginForm);
		Integer member_idx = memberService.checkIdx(loginForm);
		
		if(//member_idx를 찾지 못했거나, 세션에 있는 idx와 다르다면...
				member_idx==null || 
				member_idx!= sessionMember.getMember_idx()) {
			message.setMsg("비밀번호가 틀렸습니다.");
			message.setCode(1);
			return entity;
		} else if(member_idx==sessionMember.getMember_idx()) {
			message.setMsg("비밀번호가 맞았습니다..");
			message.setCode(0);
			log.info("@PostMapping(pass_check) before_info : {}", loginForm.getBefore_info());
			loginForm.setBefore_info(true);
			session.setAttribute("passchecked", loginForm);
			return entity;
		}
		log.info("sessionMember는 {}", sessionMember);
		message.setCode(2);
		message.setMsg("비밀번호 확인에 실패했습니다.");
		return entity;
	}
	
	
	@PostMapping("/newpass_check")
	public ResponseEntity<Message> newpassCheck(HttpServletRequest request, NewPassCheckForm newPassCheckForm){
		Message message=newPassChecker.newpasscheck(newPassCheckForm);
		ResponseEntity entity = new ResponseEntity<Message>	(message, HttpStatus.OK);	
		return entity;
	}

	//로그인폼에서 사용할 SNS 인증화면의 링크 주소 요청을 처리
		@GetMapping("/authform/google")
		public ResponseEntity<Message> getGoogleUrl(HttpServletRequest request, Member member){
			//사용자가 보게될 인증화면에 대한 주소 구하기
			log.info("인증화면 링크 얻고싶다");
			String url = googleLogin.getGrantUrl(); //인증화면으로 가기 위한 링크 주소 얻기
			log.info("url : {}", url);
			
			Message message = new Message();
			message.setMsg(url);
			
			ResponseEntity entity=new ResponseEntity<Message>(message, HttpStatus.OK);
			log.info("리턴까지 된다.");
			return entity;
		}
		
		//로그인폼에서 사용할 SNS 인증화면의 링크 주소 요청을 처리
		@GetMapping("/authform/kakao")
		public ResponseEntity<Message> getKakaoUrl(HttpServletRequest request, Member member){
			//사용자가 보게될 인증화면에 대한 주소 구하기 
			String url = kakaoLogin.getGrantUrl(); //인증화면으로 가기 위한 링크 주소 얻기
			
			Message message = new Message();
			message.setMsg(url);
			
			ResponseEntity entity=new ResponseEntity<Message>(message, HttpStatus.OK);
			return entity;
		}

		
		//로그인폼에서 사용할 SNS 인증화면의 링크 주소 요청을 처리
		@GetMapping("/authform/naver")
		public ResponseEntity<Message> getNaverUrl(HttpServletRequest request, Member member){
			//사용자가 보게될 인증화면에 대한 주소 구하기 
			String url = naverLogin.getGrantUrl(); //인증화면으로 가기 위한 링크 주소 얻기
			
			Message message = new Message();
			message.setMsg(url);
			
			ResponseEntity entity=new ResponseEntity<Message>(message, HttpStatus.OK);
			return entity;
		}
		
		@PostMapping("/sns/setInfo")
		public ResponseEntity<Message> setInfo (HttpServletRequest request,@Validated Info info, BindingResult bindingResult){
			
			if(bindingResult.hasErrors()) {
				log.info("errors={}", bindingResult);
				JSONObject jsonObject= errorBinder.bindError(bindingResult);
				ResponseEntity entity = new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
				return entity;
			}
			memberService.registInfo(info);
			
			Message message = new Message();
			message.setMsg("info 작성 성공");
			message.setCode(0);
			
			ResponseEntity entity=new ResponseEntity<Message>(message, HttpStatus.OK);
			
			return entity;
		}
		
		@PostMapping("/info_update")
		public ResponseEntity<?> updateInfo (HttpServletRequest request,@Validated Info info, BindingResult bindingResult){
			log.info("info update :::");
			
			if(bindingResult.hasErrors()) {
				log.info("errors={}", bindingResult);
				JSONObject jsonObject= errorBinder.bindError(bindingResult);
				ResponseEntity entity = new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
				return entity;
			}
			
			HttpSession session=request.getSession();
			MemberForm memberForm = (MemberForm)session.getAttribute("member");

			/**info를 수정하고 세션에 존재하는 memberForm을 업데이트 해야한다.*/
			memberService.updateSnsInfo(info, memberForm);
			log.info("memberForm 변화 됐나? {}", memberForm.getAddress());
			log.info("memberForm 변화 됐나? {}", memberForm.getAddress_detail());
			
			session.setAttribute("member", memberForm);
			
			Message message = new Message();
			message.setMsg("info 갱신 성공");
			message.setCode(0);
			
			ResponseEntity entity=new ResponseEntity<Message>(message, HttpStatus.OK);
			
			return entity;
		}
	
	
}
