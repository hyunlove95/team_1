package team1.project.bookshop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Current_ship_condition;
import team1.project.bookshop.exception.Current_ship_conditionException;

@Service
public class Current_ship_conditioinServiceImpl implements Current_ship_conditionService{

	@Autowired
	private Current_ship_conditionDAO current_ship_conditionDAO;
	
	public List selectAll() {
		return current_ship_conditionDAO.selectAll();
	}

	public Current_ship_condition select(Current_ship_condition current_ship_condition) {
		return current_ship_conditionDAO.select(current_ship_condition);
	}

	public void insert(Current_ship_condition current_ship_condition) throws Current_ship_conditionException{
		current_ship_conditionDAO.insert(current_ship_condition);
	}

	public void update(Current_ship_condition current_ship_condition) {
		current_ship_conditionDAO.update(current_ship_condition);
	}
}
