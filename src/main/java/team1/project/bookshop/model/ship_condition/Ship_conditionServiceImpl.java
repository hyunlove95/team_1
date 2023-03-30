package team1.project.bookshop.model.ship_condition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Ship_condition;
import team1.project.bookshop.exception.Ship_conditionException;

@Service
public class Ship_conditionServiceImpl implements Ship_conditionService{

	@Autowired
	private Ship_conditionDAO ship_conditionDAO;
	
	public List selectAll() {
		return ship_conditionDAO.selectAll();
	}

	public Ship_condition select(int ship_condition_idx) {
		return ship_conditionDAO.select(ship_condition_idx);
	}

	public void insert(Ship_condition ship_condition) throws Ship_conditionException{
		ship_conditionDAO.insert(ship_condition);
	}

	public void delete(int ship_condition_idx) throws Ship_conditionException{
		ship_conditionDAO.delete(ship_condition_idx);
	}
}
