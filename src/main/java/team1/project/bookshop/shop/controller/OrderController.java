package team1.project.bookshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.model.orders.OrdersService;



@Controller
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrdersService ordersService;
	
	@GetMapping("/order/list")
	public ModelAndView getOrderList(HttpServletRequest request) {
		List orderList = ordersService.selectAll();
		
		ModelAndView mav = new ModelAndView("admin/order/orderList");
		mav.addObject("orderList", orderList);
		return mav;
	}
	
	/**Detail로 가는 GetMapping*/
	
	@GetMapping("/ordercomp")
	public ModelAndView getOrderComp(HttpServletRequest request) {
		return new ModelAndView("bookshop/shop");
	}
}
