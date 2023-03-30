package team1.project.bookshop.aop;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.MemberForm;
import team1.project.bookshop.exception.HashException;
import team1.project.bookshop.exception.MemberException;

@Slf4j
public class MemberLoginCheckAdvice {
	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws MemberException, HashException, Throwable {
		
		Object result = null;
		
		String target=joinPoint.getTarget().getClass().getName();
		log.info("호출하려는 타겟클래스 : {}", target);
		
		/**aspectj signature*/
		Signature signature=joinPoint.getSignature();
		log.info("로그인체크 호출 메서드는 {}", signature.getName());
		
		HttpServletRequest request=null;
		HttpSession session=null;
		Object[] args=joinPoint.getArgs(); //매개변수 배열
		for(int i=0;i<args.length;i++) {
			if(args[i] instanceof HttpServletRequest) {
				request=(HttpServletRequest)args[i];
			}
		}
		
		String uri = request.getRequestURI();
		log.info("loginCheck uri = {}", uri);
		
		if(	
			// 컨트롤러에서 로그인을 체크하지 않는 경우
			uri.equals("/") ||
			uri.equals("/member/login") ||
			uri.equals("/member/join") ||
			uri.equals("/rest/member/idCheck") ||
			uri.equals("/rest/member/join") ||
			uri.equals("/rest/member/authform/google") ||
			uri.equals("/rest/member/authform/kakao") ||
			uri.equals("/rest/member/authform/naver") ||
			uri.equals("/member/sns/google/callback") || 
			uri.equals("/member/sns/kakao/callback") ||
			uri.equals("/member/sns/naver/callback") ||
			uri.equals("/member/sns/setInfo") ||
			uri.equals("/rest/member/sns/setInfo") ||
			uri.equals("/member/withdraw/complete") ||
			uri.equals("/bookdetail") ||
			uri.equals("/rest/book/list") ||
			uri.equals("/rest/search/word") ||
			uri.equals("/rest/book/search/subcategory") ||
			uri.equals("/rest/booksubcategory") ||
			uri.equals("/rest/booksubcategory/selectlist") ||
			uri.equals("/book/search") ||
			uri.equals("/notice/list") ||
			uri.equals("/notice/detail")
			
			
			) {
				result=joinPoint.proceed();
		}else {
			//로그인을 체크해야 하는경우 
			session=request.getSession();
			MemberForm member=(MemberForm)session.getAttribute("member");
			/**세션에 멤버가 필요하지만 없을 때, 로그인 페이지로 보내버린다.*/
			if(member==null) {
				log.info("세션에 memberForm 없음");
				throw new MemberException("로그인이 필요한 서비스입니다.");
			}else {
				log.info("AOP : member ADDRESS : {}", member.getAddress());
				result = joinPoint.proceed();
			}
		}
		return result;
	}
	
}
