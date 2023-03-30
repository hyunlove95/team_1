package team1.project.bookshop.model.order;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Confirm;
import team1.project.bookshop.exception.ConfirmException;

@Repository
public class MybatisConfirmDAO implements ConfirmDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("Confirm.selectAll");
	}

	public Confirm select(Confirm confirm) {
		return sqlSessionTemplate.selectOne("Confirm.select", confirm);
	}

	public void insert(Confirm confirm) throws ConfirmException{
		int result = sqlSessionTemplate.insert("Confirm.insert", confirm);
		if(result<1) {
			throw new ConfirmException("관리자 주문처리 등록 실패");
		}
	}

	public void update(boolean order_checked) throws ConfirmException{
		int result = sqlSessionTemplate.update("Confirm.update", order_checked);
		if(result<1) {
			throw new ConfirmException("관리자 주문처리 수정 실패");
		}
	}

	public void delete(int confirm_idx) throws ConfirmException{
		int result = sqlSessionTemplate.delete("Confirm.delete", confirm_idx);
		if(result<1) {
			throw new ConfirmException("관리자 주문처리 삭제 실패");
		}
	}
}
