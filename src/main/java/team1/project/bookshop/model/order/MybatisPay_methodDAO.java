package team1.project.bookshop.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Pay_method;
import team1.project.bookshop.exception.Pay_methodException;

@Repository
public class MybatisPay_methodDAO implements Pay_methodDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("Pay_method.selectAll");
	}

	public Pay_method select(int pay_method_idx) {
		return sqlSessionTemplate.selectOne("Pay_method.select", pay_method_idx);
	}

	public void insert(Pay_method pay_method) throws Pay_methodException{
		int result=sqlSessionTemplate.insert("Pay_method.insert", pay_method);
		if(result<1) {
			throw new Pay_methodException("결제방법 등록 실패");
		}
	}

	public void update(Pay_method pay_method) throws Pay_methodException{
		int result=sqlSessionTemplate.update("Pay_method.update", pay_method);
		if(result<1) {
			throw new Pay_methodException("결제방법 수정 실패");
		}
	}

	public void delete(int pay_method_idx) throws Pay_methodException{
		int result=sqlSessionTemplate.delete("Pay_method.delete", pay_method_idx);
		if(result<1) {
			throw new Pay_methodException("결제방법 삭제 실패");
		}
	}
}
