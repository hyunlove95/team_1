package team1.project.bookshop.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.MemberForm;import team1.project.bookshop.domain.Sns;

import team1.project.bookshop.domain.LoginForm;import team1.project.bookshop.domain.Member;
import team1.project.bookshop.exception.MemberException;

import team1.project.bookshop.model.member.MemberService;import team1.project.bookshop.sns.GoogleLogin;
import team1.project.bookshop.sns.GoogleOAuthToken;
import team1.project.bookshop.sns.KakaoLogin;
import team1.project.bookshop.sns.KakaoOAuthToken;
import team1.project.bookshop.sns.NaverLogin;
import team1.project.bookshop.sns.NaverOAuthToken;


@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired 
	private GoogleLogin googleLogin;
	
	@Autowired
	private KakaoLogin kakaoLogin;
	
	@Autowired
	private NaverLogin naverLogin;

	
	/**회원가입 폼 ok*/
	@GetMapping("/join")
	public ModelAndView getJoinForm(HttpServletRequest request) {
		return new ModelAndView("bookshop/member/joinform");
	}
	
	/**로그인 폼 ok*/
	@GetMapping("/login")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		return new ModelAndView("bookshop/member/loginform");
	}
	
	/**로그인 처리(동기) ok*/
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request, LoginForm loginForm, RedirectAttributes redirectAttributes) {
		MemberForm member_login = memberService.loginCheck(loginForm);
		
		if(member_login==null) {
			redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 올바르지 않습니다. 다시 확인해주세요.");
			ModelAndView mav = new ModelAndView("redirect:/member/login");
			return mav;
		}
		log.info("로그인한 회원는 {} 입니다. ", member_login);
		
		//세션에 담기
		HttpSession session=request.getSession();
		session.setAttribute("member", member_login);
		
		ModelAndView mav = new ModelAndView("redirect:/");
		log.info("로그인시 redirect를 줬다!");
		return mav;
	}
		
	
	
	/**마이페이지로 이동 ok*/
	@GetMapping("/mypage")
	public ModelAndView getMyPage(HttpServletRequest request) {
		return new ModelAndView("bookshop/member/mypage");
	}
	
	/**로그아웃 처리 → 메인페이지로 이동 ok*/
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	
	/**비밀번호 확인 페이지로 이동 ok*/
	@GetMapping("/pass_check")
	public ModelAndView getPassCheck(HttpServletRequest request) {
		HttpSession session=request.getSession();
		MemberForm sessionMember = (MemberForm)session.getAttribute("member");
		log.info("sns 번호는 {}", sessionMember.getSns_idx());
		if(sessionMember.getSns_idx()==4) {
			return new ModelAndView("bookshop/member/password_check");
		}else if(
				sessionMember.getSns_idx()==3 ||
				sessionMember.getSns_idx()==2 ||
				sessionMember.getSns_idx()==1
				){
			return new ModelAndView("bookshop/member/member_information");
		}else {
			return new ModelAndView("redirect:/member/mypage");
		}
	}
	
	
	/**회원정보 페이지로 이동*/
	@GetMapping("/info")
	public ModelAndView getMemberInfo(HttpServletRequest request) {
		
		HttpSession session =request.getSession();
		LoginForm passchecked = (LoginForm)session.getAttribute("passchecked");
		MemberForm member = (MemberForm)session.getAttribute("member");
		
		log.info("회원정보 페이지로 들어왔다.");
		
		/**null이면 오류 뜬다.*/
//		log.info("member info ::: member : {}", member.getMember_idx());
//		log.info("member info ::: member : {}", member.getId());
//		log.info("@GetMapping(info) before_info : {}", memberCheck.getBefore_info());
		
		if(member!=null && passchecked.getBefore_info()==true) {
			passchecked.setBefore_info(false);
			session.setAttribute("passchecked", passchecked);
			return new ModelAndView("bookshop/member/member_information");
		} else if(member!=null && passchecked.getBefore_info()!=true) {
			passchecked.setBefore_info(false);
			session.setAttribute("passchecked", passchecked);
			return new ModelAndView("redirect:/member/pass_check");
		}else {
			return new ModelAndView("redirect:/");
		}
		
		
	}
	
	/**회원삭제 페이지로 이동*/
	@GetMapping("/withdraw")
	public ModelAndView getMemberWithdraw(HttpServletRequest request) {
		return new ModelAndView("bookshop/member/member_withdraw");
	} 
	
	/**회원탈퇴(세션값과 직접 적은 비밀번호 일치하면...) → 메인페이지로 이동 */
	
	@PostMapping("/withdraw")
	public ModelAndView deleteMember(HttpServletRequest request, LoginForm loginForm, RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		
		//세션에 있는 멤버 가져오기
		HttpSession session=request.getSession();
		MemberForm sessionMember = (MemberForm)session.getAttribute("member");
		
		/**홈페이지 회원이라면*/
		if(sessionMember.getSns_idx()==4) {
			log.info(loginForm.getId());
			log.info(loginForm.getPass());
			//비밀번호 확인을 통해 멤버 가져오기 (selectByIdAndPass에 비밀번호 암호화 포함)
			Integer member_idx = memberService.checkIdx(loginForm);
			
			if(member_idx==null) {
				redirectAttributes.addFlashAttribute("message", "비밀번호가 올바르지 않습니다. 다시 확인해주세요.");
				mav.setViewName("redirect:/member/withdraw");
				return mav;
			}else if(member_idx==sessionMember.getMember_idx()) {//세션멤버와 입력한멤버가 동일하다면
				memberService.delete(sessionMember.getMember_idx());
				session.invalidate();
				redirectAttributes.addFlashAttribute("message", "아이디가 삭제되었습니다.");
				log.info("삭제완료");
				mav.setViewName("redirect:/member/withdraw/complete");
				return mav;
			}
			mav.addObject("message", "아이디 삭제에 실패했습니다.");
			mav.setViewName("redirect:/member/withdraw");
			return mav;			
			
		}else if( 
				/**SNS 회원이라면*/
				sessionMember.getSns_idx()==1 ||
				sessionMember.getSns_idx()==2 ||
				sessionMember.getSns_idx()==3
				) {
			log.info("SNS회원의 아이디는 {}", loginForm.getId());
			memberService.delete(sessionMember.getMember_idx());
			session.invalidate();
			redirectAttributes.addFlashAttribute("message", "아이디가 성공적으로 삭제되었습니다.");
			log.info("삭제완료");
			mav.setViewName("redirect:/member/withdraw/complete");
			return mav;	
		}
		
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@GetMapping("/withdraw/complete")
	public ModelAndView withdrawComplete(HttpServletRequest request) {
		return new ModelAndView("bookshop/member/withdraw_complete");
	}
	
	@GetMapping("/sns/setInfo")
	public ModelAndView setInfo(HttpServletRequest request) {
		return new ModelAndView("bookshop/member/infoform");
	}
	
	//구글 로그인 콜백
	@GetMapping("/sns/google/callback")
	public ModelAndView googleCallback(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		String code = request.getParameter("code");
		log.info("구글에서 발급된 코드는 {}", code);
		
		/**********************************************
		 * 1) 토큰 취득을 위한 POST 헤더와 바디 구성하기
		 **********************************************/
		String url=googleLogin.getToken_request_url();
		
		//body의 파라미터 구성하기 <파라미터명, 파라미터값>
		MultiValueMap<String, String> params=new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", googleLogin.getClient_id());
		params.add("client_secret", googleLogin.getClient_secret());
		params.add("redirect_uri", googleLogin.getRedirect_uri());
		params.add("grant_type", googleLogin.getGrant_type());
		
		//post 방식의 헤더 (application/x-www-form-urlencoded)
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		//머리와 몸 합치기
		HttpEntity httpEntity = new HttpEntity(params, headers);
		
		//요청시도를 위한 객체생성, 비동기방식의 요청을 위한 객체
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		
		/**********************************************
		 * 2) 토큰 요청 후 responseEntity로부터 토큰 꺼내기 (String에 불과하므로)
		 **********************************************/
		
		String body = responseEntity.getBody();
		log.info("구글에서 넘겨받은 응답정보(body) {}", body);
		
		//json으로 되어있는 문자열을 파싱하여, 자바의 객체로 옮겨담자
		ObjectMapper objectMapper=new ObjectMapper();
		GoogleOAuthToken oAuthToken=null;
		try {
			oAuthToken=objectMapper.readValue(body, GoogleOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		/**********************************************
		 * 3) 토큰을 이용하여 회원정보에 접근
		 **********************************************/
		
		String userinfo_url=googleLogin.getUserinfo_url();
		
		//GET방식요청 구성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity=new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET 요청
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity, String.class);
		
		String userBody=userEntity.getBody();
		log.info("userBody = {}", userBody);
		HashMap<String, Object> userMap=new HashMap<String, Object>();
		//사용자 정보 추출하기
		ObjectMapper objectMapper2 = new ObjectMapper();
		try {
			userMap=objectMapper2.readValue(userBody, HashMap.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String id=(String)userMap.get("id");
		String email=(String)userMap.get("email");
		boolean verified_email=(Boolean)userMap.get("verified_email");
		String name=(String)userMap.get("name");
		
		log.info("uid = {}", id);
		log.info("email = {}", email);
		log.info("name = {}", name);

		MemberForm memberForm = new MemberForm();
		
		Member member = new Member();
		member.setId(id);
		
		/**하위테이블(Info)이 존재하지 않을 수 있으니, member만 가져온다..*/
		Member snsMember=memberService.selectSnsMemberById(member.getId());
		
		//회원가입이 안되어 있는 경우 (null)
		if(snsMember==null) {
			//sns정보 심어놓아야 서비스가 일한다.
			Sns sns = new Sns();
			sns.setSns_idx(1);
			member.setSns(sns);
			member.setName(name);
			member.setEmail(email);
			member.setSns(sns);
			memberService.snsRegist(member);
			log.info("selectkey member_idx = {}", member.getMember_idx());
			
			/**member 가입 후 info테이블 작성 시키러 보내기*/
			String member_idx=Integer.toString(member.getMember_idx());
			ModelAndView mav = new ModelAndView("redirect:/member/sns/setInfo");
			redirectAttributes.addFlashAttribute("member_idx", member_idx);
			return mav;
		} else if ( /**member는 가입했지만, info는 작성하지 않았을 때*/
				snsMember!=null &&
				memberService.getSnsInfo(snsMember)==null
				) { /**info테이블 작성 시키러 보내기*/
			int member_idx = snsMember.getMember_idx();
			ModelAndView mav = new ModelAndView("redirect:/member/sns/setInfo");
			redirectAttributes.addFlashAttribute("member_idx", member_idx);
			return mav;
		}
		/**member와 info테이블을 둘다 채웠을 때,
		 * selectById는 memberMap을 가져온다.*/
		memberForm = memberService.selectById(snsMember);
		//자동 로그인처리 (세션에 담아주자)
		session.setAttribute("member", memberForm);
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
		
	
	//카카오 로그인 콜백
	@GetMapping("/sns/kakao/callback")
	public ModelAndView kakaoCallback(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		String code = request.getParameter("code");
		log.info("카카오에서 발급된 코드는 {}", code);	
		
		/**********************************************
		 * 1) 토큰 취득을 위한 POST 헤더와 바디 구성하기
		 **********************************************/
		
		String url=kakaoLogin.getToken_request_url();
		
		//body의 파라미터 구성하기 <파라미터명, 파라미터값>
		MultiValueMap<String, String> params=new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", kakaoLogin.getClient_id());
		
		params.add("redirect_uri", kakaoLogin.getRedirect_uri());
		params.add("grant_type", kakaoLogin.getGrant_type());
		
		//post 방식의 헤더 (application/x-www-form-urlencoded)
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		//머리와 몸 합치기
		HttpEntity httpEntity = new HttpEntity(params, headers);
		
		//요청시도를 위한 객체생성, 비동기방식의 요청을 위한 객체
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		
		
		
		/**********************************************
		 * 2) 토큰 요청 후 responseEntity로부터 토큰 꺼내기 (String에 불과하므로)
		 **********************************************/
		
		String body = responseEntity.getBody();
		log.info("카카오에서 넘겨받은 응답정보(body) {}", body);
		
		
		//json으로 되어있는 문자열을 파싱하여, 자바의 객체로 옮겨담자
		ObjectMapper objectMapper=new ObjectMapper();
		KakaoOAuthToken oAuthToken=null;
		try {
			oAuthToken=objectMapper.readValue(body, KakaoOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//oAuthToken 안의 토큰을 이용하여 회원정보에 접근
		 
		/**********************************************
		 * 3) 토큰을 이용하여 회원정보에 접근
		 **********************************************/
		
		String userinfo_url=kakaoLogin.getUserinfo_url();
		
		//GET방식요청 구성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity=new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET 요청
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity, String.class);
		
		String userBody=userEntity.getBody();
		log.info("userBody = {}", userBody);
		HashMap<String, Object> userMap=new HashMap<String, Object>();
		//사용자 정보 추출하기
		ObjectMapper objectMapper2 = new ObjectMapper();
		try {
			userMap=objectMapper2.readValue(userBody, HashMap.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
//		String id = (String)userMap.get("id");
		String connected_at = (String)userMap.get("connected_at");
		
		//내부의 json은 맵으로 처리
		Map properties=(Map)userMap.get("properties");
		Map kakao_account=(Map)userMap.get("kakao_account");
		String name=(String)properties.get("nickname");
		String email=(String)kakao_account.get("email");
		
		log.info("id is {}", userMap.get("id"));
		log.info("name is {}", name);
		log.info("email is {}", email);
		String id=Long.toString((Long)userMap.get("id"));
		
		MemberForm memberForm = new MemberForm();
		
		Member member = new Member();
		member.setId(id);
		
		/**하위테이블(Info)이 존재하지 않을 수 있으니, member만 가져온다..*/
		Member snsMember=memberService.selectSnsMemberById(member.getId());
		
		//회원가입이 안되어 있는 경우 (null)
		if(snsMember==null) {
			//sns정보 심어놓아야 서비스가 일한다.
			Sns sns = new Sns();
			sns.setSns_idx(3);
			member.setSns(sns);
			member.setName(name);
			member.setEmail(email);
			member.setSns(sns);
			memberService.snsRegist(member);
			log.info("selectkey member_idx = {}", member.getMember_idx());
			
			/**member 가입 후 info테이블 작성 시키러 보내기*/
			String member_idx=Integer.toString(member.getMember_idx());
			ModelAndView mav = new ModelAndView("redirect:/member/sns/setInfo");
			redirectAttributes.addFlashAttribute("member_idx", member_idx);
			return mav;
		} else if ( /**member는 가입했지만, info는 작성하지 않았을 때*/
				snsMember!=null &&
				memberService.getSnsInfo(snsMember)==null
				) { /**info테이블 작성 시키러 보내기*/
			int member_idx = snsMember.getMember_idx();
			ModelAndView mav = new ModelAndView("redirect:/member/sns/setInfo");
			redirectAttributes.addFlashAttribute("member_idx", member_idx);
			return mav;
		}
		/**member와 info테이블을 둘다 채웠을 때,
		 * selectById는 memberMap을 가져온다.*/
		memberForm = memberService.selectById(snsMember);
		//자동 로그인처리 (세션에 담아주자)
		session.setAttribute("member", memberForm);
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
		
	}
	
	//네이버 로그인 콜백
	@GetMapping("/sns/naver/callback")
	public ModelAndView naverCallback(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		String code = request.getParameter("code");
		log.info("네이버에서 발급된 코드는 {}", code);
		
		//구글이 넘겨준 code와 내 계정이 보유한 client_id 및 client_secret을 조합하여
		//구글 서버측에 token 발급을 요청해야 한다..(POST) head+body를 구성하여 요청을 시도
		//이때 우리 스프링 서버는 상대적으로 클라이언트가 된다.
		//token은 회원정보에 접근할 수 있는 증명서 같은 개념임..
		
		/**********************************************
		 * 1) 토큰 취득을 위한 POST 헤더와 바디 구성하기
		 **********************************************/
		
		String url=naverLogin.getToken_request_url();
		
		//body의 파라미터 구성하기 <파라미터명, 파라미터값>
		MultiValueMap<String, String> params=new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", naverLogin.getClient_id());
		params.add("client_secret", naverLogin.getClient_secret());
		params.add("redirect_uri", naverLogin.getRedirect_uri());
		params.add("grant_type", naverLogin.getGrant_type());
		params.add("state", naverLogin.getState());
		
		//post 방식의 헤더 (application/x-www-form-urlencoded)
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		//머리와 몸 합치기
		HttpEntity httpEntity = new HttpEntity(params, headers);
		
		//요청시도를 위한 객체생성, 비동기방식의 요청을 위한 객체
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		
		
		
		/**********************************************
		 * 2) 토큰 요청 후 responseEntity로부터 토큰 꺼내기 (String에 불과하므로)
		 **********************************************/
		
		String body = responseEntity.getBody();
		log.info("네이버에서 넘겨받은 응답정보(body) {}", body);
		
		
		//json으로 되어있는 문자열을 파싱하여, 자바의 객체로 옮겨담자
		
		ObjectMapper objectMapper=new ObjectMapper();
		NaverOAuthToken oAuthToken=null;
		try {
			oAuthToken=objectMapper.readValue(body, NaverOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//oAuthToken 안의 토큰을 이용하여 회원정보에 접근
		
		/**********************************************
		 * 3) 토큰을 이용하여 회원정보에 접근
		 **********************************************/
		
		String userinfo_url=naverLogin.getUserinfo_url();
		
		//GET방식요청 구성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity=new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET 요청
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity, String.class);
		
		String userBody=userEntity.getBody();
		log.info(" 회원정보 : userBody = {}", userBody);
		
		HashMap<String, Object> userMap=new HashMap<String, Object>();
		//사용자 정보 추출하기
		ObjectMapper objectMapper2 = new ObjectMapper();
		try {
			userMap=objectMapper2.readValue(userBody, HashMap.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//내부의 json은 맵으로 처리
		Map response=(Map)userMap.get("response");
		String id=(String)response.get("id");
		String name=(String)response.get("nickname");
		String gender=(String)response.get("gender");
		String email=(String)response.get("email");
		String mobile=(String)response.get("mobile");
		
		
		log.info("id is {}", id);
		log.info("name is {}", name);
		log.info("email is {}", email);
		
		
		MemberForm memberForm = new MemberForm();
		
		Member member = new Member();
		member.setId(id);
		
		/**하위테이블(Info)이 존재하지 않을 수 있으니, member만 가져온다..*/
		Member snsMember=memberService.selectSnsMemberById(member.getId());
		
		//회원가입이 안되어 있는 경우 (null)
		if(snsMember==null) {
			//sns정보 심어놓아야 서비스가 일한다.
			Sns sns = new Sns();
			sns.setSns_idx(2);
			member.setSns(sns);
			member.setName(name);
			member.setEmail(email);
			member.setSns(sns);
			memberService.snsRegist(member);
			log.info("selectkey member_idx = {}", member.getMember_idx());
			
			/**member 가입 후 info테이블 작성 시키러 보내기*/
			String member_idx=Integer.toString(member.getMember_idx());
			ModelAndView mav = new ModelAndView("redirect:/member/sns/setInfo");
			redirectAttributes.addFlashAttribute("member_idx", member_idx);
			return mav;
		} else if ( /**member는 가입했지만, info는 작성하지 않았을 때*/
				snsMember!=null &&
				memberService.getSnsInfo(snsMember)==null
				) { /**info테이블 작성 시키러 보내기*/
			int member_idx = snsMember.getMember_idx();
			ModelAndView mav = new ModelAndView("redirect:/member/sns/setInfo");
			redirectAttributes.addFlashAttribute("member_idx", member_idx);
			return mav;
		}
		/**member와 info테이블을 둘다 채웠을 때,
		 * selectById는 memberMap을 가져온다.*/
		memberForm = memberService.selectById(snsMember);
		//자동 로그인처리 (세션에 담아주자)
		session.setAttribute("member", memberForm);
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;	
		}



}