package team1.project.bookshop.model.order;

import java.util.List;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Current_ship_condition;

public interface Current_ship_conditionDAO {
	public List selectAll();
	public Current_ship_condition select(Current_ship_condition current_ship_condition);
	public void insert(Current_ship_condition current_ship_condition);
	public void update(Current_ship_condition current_ship_condition);
}
