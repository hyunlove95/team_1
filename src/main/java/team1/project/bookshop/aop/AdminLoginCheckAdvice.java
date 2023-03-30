package team1.project.bookshop.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Admin;
import team1.project.bookshop.exception.AdminException;

@Slf4j
public class AdminLoginCheckAdvice {
	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws AdminException, Throwable {
		
		Object result = null;
		
		Class targetClass=joinPoint.getTarget().getClass();
		Object[] args=joinPoint.getArgs();
		log.info("호출 타겟 클래스 : {}", targetClass.getName());
		/**aspectj signature*/
		Signature signature=joinPoint.getSignature();
		log.info("어드민로그인체크 호출 메서드는 {}", signature.getName());
		
		HttpServletRequest request=null;
		HttpSession session=null;
		for(int i=0;i<args.length;i++) {
			if(args[i] instanceof HttpServletRequest) {
				request=(HttpServletRequest)args[i];
			}
		}
		
		String uri = request.getRequestURI();
		log.info("uri = {}", uri);
		
		if(		/**
				 * 관리자 로그인 체크하지 않는 경우
				 * post admin/login에서는 세션을 담는다.
				 * 여기 어드바이스에서는 세션을 없어도 된다.
				 */
				uri.equals("/admin/login") ||
				uri.equals("/admin/logout")
				) {
			result=joinPoint.proceed();
		}else {
			//관리자 로그인 체크해야 하는 경우
			session=request.getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			if(admin==null) {
				log.info("admin 세션 없음");
				throw new AdminException("로그인이 필요한 서비스입니다");
			}else {
				log.info("admin = {}", admin);
				result = joinPoint.proceed();
			}
		}
		
		return result;
	}
}
