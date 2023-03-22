package team1.project.bookshop.model.notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Notice;
import team1.project.bookshop.exception.NoticeException;

@Repository
public class MybatisNoticeDAO implements NoticeDAO{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		List list=sqlSessionTemplate.selectList("Notice.selectAll");
		return list;
	}

	@Override
	public Notice select(int notice_idx) {
		return sqlSessionTemplate.selectOne("Notice.select", notice_idx);
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		int result=sqlSessionTemplate.insert("Notice.insert", notice);
		
		if(result<1) {
			throw new NoticeException("공지사항 등록실패");
		}
	}

	@Override
	public void update(Notice notice) throws NoticeException{
		int result=sqlSessionTemplate.update("Notice.update", notice);
		if(result<1) {
			throw new NoticeException("공지사항 수정실패");
		}
	}

	@Override
	public void delete(int notice_idx) throws NoticeException{
		int result=sqlSessionTemplate.delete("Notice.delete", notice_idx);
		if(result<1) {
			throw new NoticeException("공지사항 삭제실패");
		}
	}
	
	
}
