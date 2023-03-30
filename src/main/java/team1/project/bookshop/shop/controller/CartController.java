package team1.project.bookshop.shop.controller;

import java.util.List;
		
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.model.order.CartService;
import team1.project.bookshop.model.order.Order_detailService;


@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private Order_detailService order_detailService;
	
	@GetMapping("/shop_cart")
	public ModelAndView getShop_cart(Cart cart) {
		List cartList = cartService.selectAll(cart);
		
		ModelAndView mav = new ModelAndView("shop/shop_cart");
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	@GetMapping("/shop_order")
	public ModelAndView getShop_order() {
		return new ModelAndView("shop/shop_order");
	}
}
