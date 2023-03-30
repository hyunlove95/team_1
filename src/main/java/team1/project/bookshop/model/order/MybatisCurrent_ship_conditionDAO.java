package team1.project.bookshop.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Current_ship_condition;
import team1.project.bookshop.exception.Current_ship_conditionException;

@Repository
public class MybatisCurrent_ship_conditionDAO implements Current_ship_conditionDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("Current_ship_condition.selectAll");
	}

	public Current_ship_condition select(Current_ship_condition current_ship_condition) {
		return sqlSessionTemplate.selectOne("Current_ship_condition.select", current_ship_condition);
	}

	public void insert(Current_ship_condition current_ship_condition) throws Current_ship_conditionException{
		int result = sqlSessionTemplate.insert("Current_ship_condition.insert", current_ship_condition);
		if(result<1) {
			throw new Current_ship_conditionException("배송현황 등록 실패");
		}
	}

	public void update(Current_ship_condition current_ship_condition){
		int result = sqlSessionTemplate.update("Current_ship_condition.update", current_ship_condition);
	}
}
