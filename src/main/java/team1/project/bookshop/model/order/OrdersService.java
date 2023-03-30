package team1.project.bookshop.model.order;

import java.util.List;

import team1.project.bookshop.domain.Current_ship_condition;
import team1.project.bookshop.domain.Order_detail;
import team1.project.bookshop.domain.Orders;

public interface OrdersService {
	public List selectAll();
	public List selectAllByMember(Orders orders);
	public Orders select(Orders orders);
	public void regist(Orders orders);	// 고객 주문 + 배송 현황 및 상태(주문확인) + admin 주문확인처리준비
	public void delete(Orders orders);
}
