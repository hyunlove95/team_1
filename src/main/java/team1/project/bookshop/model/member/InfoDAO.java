package team1.project.bookshop.model.member;

import team1.project.bookshop.domain.Info;
import team1.project.bookshop.domain.Member;

public interface InfoDAO {
	public Info select(int member_idx);
	public void insert(Info info);
	public void snsInsert(Info info);
	public void update(Info info);
	public void snsUpdate(Info info);
	public void delete(int member_idx);
	public Info getSnsInfo(int member_idx);
}