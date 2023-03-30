package team1.project.bookshop.model.ship_condition;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Ship_condition;
import team1.project.bookshop.exception.Ship_conditionException;

@Repository
public class MybatisShip_conditionDAO implements Ship_conditionDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("Ship_condition.selectAll");
	}

	public Ship_condition select(int ship_condition_idx) {
		return sqlSessionTemplate.selectOne("Ship_condition.select", ship_condition_idx);
	}

	public void insert(Ship_condition ship_condition) throws Ship_conditionException{
		int result = sqlSessionTemplate.insert("Ship_condition.insert", ship_condition);
		if(result<1) {
			throw new Ship_conditionException("배송상태 등록 실패");
		}
	}

	public void delete(int ship_condition_idx) throws Ship_conditionException{
		int result = sqlSessionTemplate.delete("Ship_condition.delete", ship_condition_idx);
		if(result<1) {
			throw new Ship_conditionException("배송상태 삭제 실패");
		}
	}
}
