package team1.project.bookshop.model.order_detail;

import java.util.List;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Order_detail;

public interface Order_detailService {
	public List selectAll(Order_detail order_detail);
	public Order_detail select(Order_detail order_detail);
	public void insert(Order_detail order_detail);
	public void delete(Order_detail order_detail);
}
