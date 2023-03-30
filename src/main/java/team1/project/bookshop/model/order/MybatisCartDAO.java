package team1.project.bookshop.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.exception.CartException;

@Repository
public class MybatisCartDAO implements CartDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll(Cart cart) {
		return sqlSessionTemplate.selectList("Cart.selectAll", cart);
	}

	public Cart select(Cart cart) {
		return sqlSessionTemplate.selectOne("Cart.select", cart);
	}
	
	public int selectDuplicate(Cart cart) {
		int result = sqlSessionTemplate.selectOne("Cart.selectDuplicate", cart);
		return result;
	}

	public void insert(Cart cart) throws CartException{
		int result = sqlSessionTemplate.insert("Cart.insert", cart);
		if(result<1) {
			throw new CartException("Cart 등록 실패");
		}
	}
	
	public void update(Cart cart) throws CartException{
		int result = sqlSessionTemplate.update("Cart.update", cart);
		if(result<1) {
			throw new CartException("Cart 등록 실패");
		}
	}

	public void delete(Cart cart) throws CartException{
		int result = sqlSessionTemplate.delete("Cart.delete", cart);
		if(result<1) {
			throw new CartException("Cart 삭제 실패");
		}
	}
}
