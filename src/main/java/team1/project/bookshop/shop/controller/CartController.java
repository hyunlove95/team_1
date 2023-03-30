package team1.project.bookshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.model.cart.CartService;
import team1.project.bookshop.model.order_detail.Order_detailService;


@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private Order_detailService order_detailService;
	
	@GetMapping("/shop_cart")
	public ModelAndView getShop_cart(HttpServletRequest request, Cart cart) {
		List cartList = cartService.selectAll(cart);
		
		ModelAndView mav = new ModelAndView("bookshop/order/shop_cart");
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	@GetMapping("/shop_order")
	public ModelAndView getShop_order(HttpServletRequest request) {
		return new ModelAndView("bookshop/order/shop_order");
	}
}
