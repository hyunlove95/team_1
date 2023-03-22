package team1.project.bookshop.model.inquiry_category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Inquiry_category;
import team1.project.bookshop.exception.Inquiry_categoryException;
import team1.project.bookshop.model.inquiry_category.Inquiry_categoryDAO;

@Repository
public class MybatisInquiry_categoryDAO implements Inquiry_categoryDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		List list=sqlSessionTemplate.selectList("Inquiry_category.selectAll");
		return list;
	}

	@Override
	public Inquiry_category select(int inquiry_category_idx) {
		return sqlSessionTemplate.selectOne("Inquiry_category.select", inquiry_category_idx);
	}

	@Override
	public void insert(Inquiry_category inquiry_category) throws Inquiry_categoryException{
		int result=sqlSessionTemplate.insert("Inquiry_category.insert", inquiry_category);
		
		if(result<1) {
			throw new Inquiry_categoryException("카테고리 등록실패");
		}
	}

	@Override
	public void update(Inquiry_category inquiry_category) throws Inquiry_categoryException{
		int result=sqlSessionTemplate.update("Inquiry_category.update", inquiry_category);
		if(result <1) {
			throw new Inquiry_categoryException("카테고리 수정실패");
		}
	}

	@Override
	public void delete(int inquiry_category_idx) throws Inquiry_categoryException{
		int result=sqlSessionTemplate.delete("Inquiry_category.delete", inquiry_category_idx);
		if(result <1) {
			throw new Inquiry_categoryException("카테고리 삭제실패");
		}
	}


}
