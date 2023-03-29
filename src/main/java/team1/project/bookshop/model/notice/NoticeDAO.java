package team1.project.bookshop.model.notice;

import java.util.List;

import team1.project.bookshop.domain.Notice;

public interface NoticeDAO {
	public List selectAll();
	public Notice select(int notice_idx);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int notice_idx);
	public void hitUp(int notice_idx);
	public List selectByWord(String title);
}
