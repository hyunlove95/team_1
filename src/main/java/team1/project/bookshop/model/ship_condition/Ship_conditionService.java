package team1.project.bookshop.model.ship_condition;

import java.util.List;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Ship_condition;

public interface Ship_conditionService {
	public List selectAll();
	public Ship_condition select(int ship_condition_idx);
	public void insert(Ship_condition ship_condition);
	public void delete(int ship_condition_idx);
}
