package team1.project.bookshop.model.inquiry;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Inquiry;
import team1.project.bookshop.exception.InquiryException;

@Repository
public class MybatisInquiryDAO implements InquiryDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Inquiry.selectAll");
	}

	@Override
	public Inquiry select(int inquiry_idx) {
		return sqlSessionTemplate.selectOne("Inquiry.select", inquiry_idx);
	}

	@Override
	public void insert(Inquiry inquiry) throws InquiryException{
		int result=sqlSessionTemplate.insert("Inquiry.insert", inquiry);
		if(result <1) {
			throw new InquiryException("1:1 문의 등록 실패");
		}
	}

	@Override
	public void update(Inquiry inquiry) throws InquiryException{
		int result=sqlSessionTemplate.update("Inquiry.update", inquiry);
		if(result <1) {
			throw new InquiryException("1:1 문의 수정 실패");
		}
	}

	@Override
	public void delete(int inquiry_idx) throws InquiryException{
		int result=sqlSessionTemplate.delete("Inquiry.delete", inquiry_idx);
		if(result <1) {
			throw new InquiryException("1:1 문의 삭제 실패");
		}
	}

}
