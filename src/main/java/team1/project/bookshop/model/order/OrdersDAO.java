package team1.project.bookshop.model.order;

import java.util.List;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Current_ship_condition;
import team1.project.bookshop.domain.Orders;

public interface OrdersDAO {
	public List selectAll();
	public List selectAllByMember(Orders orders);
	public Orders select(Orders orders);
	public void insert(Orders orders);
	public void delete(Orders orders);
}
