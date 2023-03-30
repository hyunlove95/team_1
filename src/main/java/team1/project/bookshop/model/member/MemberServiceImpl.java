package team1.project.bookshop.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Info;
import team1.project.bookshop.domain.LoginForm;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.domain.MemberForm;
import team1.project.bookshop.exception.HashException;
import team1.project.bookshop.exception.MemberException;
import team1.project.bookshop.model.inquiry.InquiryDAO;
import team1.project.bookshop.model.inquiry.Inquiry_imgDAO;
import team1.project.bookshop.util.MemberConverter;
import team1.project.bookshop.util.PassConverter;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private PassConverter passConverter;
	
	@Autowired
	private MemberConverter memberConverter;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private InfoDAO infoDAO;
	
	@Autowired
	private LoginFormDAO loginFormDAO;
	
	@Autowired
	private InquiryDAO inquiryDAO;
	
	@Autowired
	private Inquiry_imgDAO Inquiry_imgDAO;
	
	
	@Override
	public List selectAll() {
		return memberDAO.selectAll();
	}

	
	@Override
	public int selectCountMember() {
		return memberDAO.selectCountMember();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MemberForm loginCheck(LoginForm loginForm) throws HashException{
		String hash=passConverter.convertHash(loginForm.getPass());
		loginForm.setPass(hash);
		Integer member_idx=loginFormDAO.selectIdxByIdAndPass(loginForm);
		if(member_idx==null) {
			return null;
		}else {
			Member member = memberDAO.select(member_idx);
			
			return memberConverter.getMemberForm(member);
		}
			
	}

	@Override
	public int idCheck(String id) {
		return memberDAO.idCheck(id);
	}
	
	/**가입 시 idcheck 포함..*/
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(MemberForm memberForm) throws MemberException, HashException{

		//비밀번호 암호화
		String hash=passConverter.convertHash(memberForm.getPass());
		memberForm.setPass(hash);
		
		/**member_idx 빼고 다 넣었다.*/
		Member member = memberConverter.getMember(memberForm);
		log.info(member.getLoginForm().getId());
		
		if(memberDAO.idCheck(memberForm.getId())>0) {
			//예외 없지만, 일부러 예외 던짐
			throw new MemberException("아이디가 중복되어 등록할 수 없습니다.");
		}
		
		memberDAO.insert(member);
		/**하지만 loginform과 info에게는 member_idx가 없다*/
		
		log.info("selectkey member_idx"+member.getMember_idx());
		int member_idx=member.getMember_idx();
		
		LoginForm loginForm=member.getLoginForm();
		loginForm.setMember(member);
		Info info=member.getInfo();
		info.setMember(member);
		
		loginFormDAO.insert(loginForm);
		infoDAO.insert(info);
		
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MemberForm memberForm) throws MemberException, HashException{
		//비밀번호 암호화
//		log.info("멤버 비밀번호={}", memberForm.getPass());
		log.info("멤버idx={}", Integer.toString(memberForm.getMember_idx()));
//		log.info("멤버 비밀번호길이={}", Integer.toString(memberForm.getPass().length()));
		
		/**암호화된 비밀번호(64자리)를 그대로 넣어도 비밀번호가 그냥 바뀌어버린다...
		 * 그렇기 때문에 64자리보다 짧을 때만 비밀번호를 바꾼다.*/
		if(memberForm.getPass().length()<64) {
			String hash=passConverter.convertHash(memberForm.getPass());
			memberForm.setPass(hash);
		}
//		log.info("멤버 비밀번호={}", memberForm.getPass());
//		log.info("멤버 비밀번호길이={}", Integer.toString(memberForm.getPass().length()));
		
		Member member = memberConverter.getMember(memberForm);
		member.setMember_idx(memberForm.getMember_idx());
		log.info(member.getLoginForm().getId());
		memberDAO.update(member);
		
		LoginForm loginForm=member.getLoginForm();
		loginForm.setMember(member);
		Info info=member.getInfo();
		info.setMember(member);
		
		loginFormDAO.update(loginForm);
		infoDAO.update(info);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int member_idx) throws MemberException{
		
		//member가 가진 장바구니 삭제
		
		//member가 가진 주문내역 삭제
		
		//member가 가진 문의내역 삭제
//		Inquiry_imgDAO.delete(inquiry_idx);
//		inquiryDAO.delete(member_idx);
		//주문목록은? 그대로...
		infoDAO.delete(member_idx);
		log.info("info 삭제");
		/**loginform이 존재한다면 */
		if(loginFormDAO.select(member_idx)!=null) {
			loginFormDAO.delete(member_idx);
			log.info("loginform 삭제");
		}		
		memberDAO.delete(member_idx); 
		log.info("member 삭제");
		
	}

	@Override
	public Integer checkIdx(LoginForm loginForm) throws HashException{
		String hash=passConverter.convertHash(loginForm.getPass());
		loginForm.setPass(hash);
		return loginFormDAO.selectIdxByIdAndPass(loginForm);
	}


	@Override
	public void snsRegist(Member member) throws MemberException{
		memberDAO.insert(member);
	}


	@Override
	public MemberForm selectById(Member member) {
		String id = member.getId();
		Member selectedMember=memberDAO.selectById(id);
		return memberConverter.getMemberForm(selectedMember); 
	}


	@Override
	public void registInfo(Info info) throws MemberException{
		infoDAO.snsInsert(info);
	}


	@Override
	public Info getSnsInfo(Member member) {
		return infoDAO.getSnsInfo(member.getMember_idx());
	}


	@Override
	public Member selectSnsMemberById(String id) {
		return memberDAO.selectSnsMemberById(id);
	}


	@Override
	public void updateInfo(Info info) throws MemberException{
		log.info("멤버서비스임플 업데이트 인포 실행");
		infoDAO.update(info);
	}
	
	@Override
	public void updateSnsInfo(Info info, MemberForm memberForm) throws MemberException{
		log.info("멤버서비스임플 sns 계정 업데이트 인포 실행");
		infoDAO.snsUpdate(info);
		memberConverter.setInfo(memberForm, info);
	}


	@Override
	public List search(String keyword) {
		return memberDAO.search(keyword);
	}
	
	


}