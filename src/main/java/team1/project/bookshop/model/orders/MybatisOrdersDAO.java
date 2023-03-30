package team1.project.bookshop.model.orders;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Orders;
import team1.project.bookshop.exception.OrdersException;

@Repository
public class MybatisOrdersDAO implements OrdersDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {
		return sqlSessionTemplate.selectList("Orders.selectAll");
	}
	
	public List selectAllByMember(Orders orders) {
		return sqlSessionTemplate.selectList("Orders.selectAllByMember", orders);
	}

	public Orders select(Orders orders) {
		return sqlSessionTemplate.selectOne("Orders.select", orders);
	}

	public void insert(Orders orders) throws OrdersException{
		int result = sqlSessionTemplate.insert("Orders.insert", orders);
		if(result<1) {
			throw new OrdersException("Orders(장바구니) 등록 실패");
		}
	}

	public void delete(Orders orders) throws OrdersException{
		int result = sqlSessionTemplate.delete("Orders.delete", orders);
		if(result<1) {
			throw new OrdersException("Orders(장바구니) 삭제 실패");
		}
	}
}
