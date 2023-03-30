package team1.project.bookshop.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Order_detail;
import team1.project.bookshop.exception.Order_detailException;

@Repository
public class MybatisOrder_detailDAO implements Order_detailDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll(Order_detail order_detail) {
		return sqlSessionTemplate.selectList("Order_detail.selectAll", order_detail);
	}

	public Order_detail select(Order_detail order_detail) {
		return sqlSessionTemplate.selectOne("Order_detail.select", order_detail);
	}

	public void insert(Order_detail order_detail) throws Order_detailException{
		int result = sqlSessionTemplate.insert("Order_detail.insert", order_detail);
		if(result<1) {
			throw new Order_detailException("Order_detail(책 한권) 등록 실패");
		}
	}

	public void delete(Order_detail order_detail) throws Order_detailException{
		int result = sqlSessionTemplate.insert("Order_detail.insert", order_detail);
		if(result<1) {
			throw new Order_detailException("Order_detail(책 한권) 등록 실패");
		}
	}
}
