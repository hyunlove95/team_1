package team1.project.bookshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Current_ship_condition;
import team1.project.bookshop.domain.Ship_condition;
import team1.project.bookshop.model.order.Current_ship_conditionService;
import team1.project.bookshop.model.order.Ship_conditionService;

@Controller
public class OrderController {
	
	@Autowired
	private Ship_conditionService ship_conditionService;
	
	@GetMapping("/order_detail")
	public ModelAndView getDetail() {
		List ship_conditionList = ship_conditionService.selectAll();
		
		ModelAndView mav = new ModelAndView("admin/order/orderDetail");
		mav.addObject("ship_conditionList", ship_conditionList);
		return mav;
	}
}
