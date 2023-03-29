package team1.project.bookshop.model.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Notice;
import team1.project.bookshop.exception.NoticeException;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List selectAll() {	
		return noticeDAO.selectAll();
	}

	@Override
	public Notice select(int notice_idx) {
		noticeDAO.hitUp(notice_idx);
		return noticeDAO.select(notice_idx);
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		noticeDAO.insert(notice);
	}

	@Override
	public void update(Notice notice) throws NoticeException{
		noticeDAO.update(notice);
	}

	@Override
	public void delete(int notice_idx) throws NoticeException{
		noticeDAO.delete(notice_idx);
	}
	
	@Override
	public List selectByWord(String title) {
		return noticeDAO.selectByWord(title);
	}

}
