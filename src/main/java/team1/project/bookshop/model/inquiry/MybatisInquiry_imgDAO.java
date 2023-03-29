package team1.project.bookshop.model.inquiry;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Inquiry_img;
import team1.project.bookshop.exception.Inquiry_imgException;

@Repository
public class MybatisInquiry_imgDAO implements Inquiry_imgDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		List list=sqlSessionTemplate.selectList("Inquiry_img.selectAll");
		return list;
	}

	@Override
	public List selectByInquiry(int inquiry_idx) {
		return sqlSessionTemplate.selectList("Inquiry_img.selectByInquiry", inquiry_idx);
	}

	@Override
	public void insert(Inquiry_img inquiry_img) throws Inquiry_imgException{
		int result=sqlSessionTemplate.insert("Inquiry_img.insert", inquiry_img);
		
		if(result<1) {
			throw new Inquiry_imgException("이미지 등록실패");
		}
	}

	@Override
	public void delete(int inquiry_idx) throws Inquiry_imgException{
		int result=sqlSessionTemplate.delete("Inquiry_img.delete", inquiry_idx);
		
		if(result<1) {
			throw new Inquiry_imgException("이미지 삭제실패");
		}
	}

}
