package team1.project.bookshop.model.member;

import java.util.List;

import team1.project.bookshop.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public int selectCountMember();
	public Member select(int member_idx);
	public int idCheck(String id);
	public void insert(Member member);
	public void update(Member member);
	public void delete(int member_idx);
	public Member selectById(String id);
	public Member selectSnsMemberById(String id);
	public List search(String keyword);
}