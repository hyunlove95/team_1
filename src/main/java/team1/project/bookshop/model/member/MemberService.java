package team1.project.bookshop.model.member;

import java.util.List;

import team1.project.bookshop.domain.Info;
import team1.project.bookshop.domain.LoginForm;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.domain.MemberForm;

public interface MemberService {
	public List selectAll();
	public int selectCountMember();
	public MemberForm loginCheck(LoginForm loginForm);
	public int idCheck(String id);
	public void regist(MemberForm memberForm);
	public void update(MemberForm memberForm);
	public void delete(int member_idx);
	public Integer checkIdx(LoginForm loginForm);
	public void snsRegist(Member member);
	/**member -> memberform*/
	public MemberForm selectById(Member member);
	public Member selectSnsMemberById(String id);
	public void registInfo(Info info);
	public Info getSnsInfo(Member member);
	public void updateInfo(Info info);
	public void updateSnsInfo(Info info, MemberForm memberForm);
	public List search(String keyword);
}








