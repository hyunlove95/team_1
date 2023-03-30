package team1.project.bookshop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Confirm;
import team1.project.bookshop.domain.Current_ship_condition;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.domain.Order_detail;
import team1.project.bookshop.domain.Orders;
import team1.project.bookshop.exception.CartException;
import team1.project.bookshop.exception.Order_detailException;
import team1.project.bookshop.exception.OrdersException;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private CartDAO cartDAO;
		
	@Autowired
	private Order_detailDAO order_detailDAO;

	@Autowired
	private Current_ship_conditionDAO current_ship_conditionDAO;
	
	@Autowired
	private ConfirmDAO confirmDAO;
	
	public List selectAll() {
		return ordersDAO.selectAll();
	}
	
	public List selectAllByMember(Orders orders) {
		return ordersDAO.selectAllByMember(orders);
	}

	public Orders select(Orders orders) {
		return ordersDAO.select(orders);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Orders orders) throws OrdersException, CartException, Order_detailException{
		//주문요약 정보 넣기(orders 테이블) 
		ordersDAO.insert(orders); // selectkey 작동했따면, orders안에 pk에 채워져있을거임..
		
		//주문 상세넣기  (order_detail 테이블)
		Member member = orders.getMember();
		Cart cart = new Cart();
		
		cart.setMember(member);
		
		List<Cart> cartList=cartDAO.selectAll(cart);
		
		for(int i=0;i<cartList.size();i++) {  //장바구니에서 주문상세로 이동..
			Cart obj = cartList.get(i);
			
			Order_detail orderDetail=new Order_detail();
			
			orderDetail.setOrders(orders);
			orderDetail.setBook(obj.getBook());
			orderDetail.setQuantity(obj.getQuantity());
			orderDetail.setFinal_price(obj.getBook().getPrice());//현재 가격
			
			order_detailDAO.insert(orderDetail);
		}
		
		//장바구니 비우기 
		for(int i=0;i<cartList.size();i++) {  //장바구니에서 주문상세로 이동..
			Cart delCart = cartList.get(i);
			cartDAO.delete(delCart);
		}
		
		// 배송현황 등록
		Current_ship_condition current_ship_condition = new Current_ship_condition();
		
		long time = System.currentTimeMillis();
		String invoice_number = String.valueOf(time);		// 송장번호를 현재ms로 구현
		
		current_ship_condition.setOrders(orders);
		current_ship_condition.setInvoice_number(invoice_number);
		
		current_ship_conditionDAO.insert(current_ship_condition);
		
		//이메일, sms, 카톡... 
		
	}
	
	public void delete(Orders orders) throws OrdersException{
		ordersDAO.delete(orders);
	}
}
