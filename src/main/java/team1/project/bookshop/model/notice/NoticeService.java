package team1.project.bookshop.model.notice;

import java.util.List;

import team1.project.bookshop.domain.Notice;

public interface NoticeService {
	public List selectAll();
	public Notice select(int notice_idx);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int notice_idx);
}
