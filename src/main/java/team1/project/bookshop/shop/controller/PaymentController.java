package team1.project.bookshop.shop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import team1.project.bookshop.domain.Card;
import team1.project.bookshop.domain.Orders;
import team1.project.bookshop.domain.Pay_method;
import team1.project.bookshop.domain.PaymentResponseBody;
import team1.project.bookshop.exception.PaymentException;
import team1.project.bookshop.model.order.OrdersService;

@Controller
public class PaymentController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired 
	private OrdersService ordersService;
	
	@GetMapping("/payment/callback/success")
	public ModelAndView payment(HttpServletRequest request, String paymentKey, String orderId, int amount, int returnAmount) throws IOException, InterruptedException, ParseException {
		System.out.println("결제 요청 성공");
		System.out.println("paymentKey :"+paymentKey);
		System.out.println("orderId :"+orderId);
		System.out.println("amount :"+amount);
		System.out.println("returnAmount :"+returnAmount);
		
		// 일치하는 경우
		Card card = new Card();
		PaymentResponseBody paymentResponseBody = new PaymentResponseBody();
		// card.amount == totalAmount
		if(amount != returnAmount) {
			throw new PaymentException("요청하신 가격과 주문 가격이 다릅니다");
		}
		
		HttpRequest req = HttpRequest.newBuilder()
		    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
		    .header("Authorization", "Basic dGVzdF9za19rNmJKWG1nbzI4ZTJuZ29vTW9qM0xBbkdLV3g0Og==")
		    .header("Content-Type", "application/json")
		    .method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\""+paymentKey+"\",\"amount\":"+amount+",\"orderId\":\""+orderId+"\"}"))
		    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
		System.out.println("결제 승인 요청 결과 : "+response.body());

		
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(response.body());
		JSONObject json = (JSONObject) obj;
				
		String method = (String)json.get("method");
		int totalAmount = (Integer)json.get("totalAmount");
		
		Orders orders = new Orders();
		orders.setTotal_pay(totalAmount);
		
		Pay_method pay_method = new Pay_method();
		pay_method.setPayment(method);
		orders.setPay_method(pay_method);
		
		ordersService.regist(orders);
		
		// 1) 주문요약 ordersummary - pk를 selectkey로 얻어와 orderdetail에 써먹기
		
		// 2) 주문상세 orderdetail(2건 이상일 경우 반복문으로 트랜잭션 걸어서)
		//		장바구니에 들어있는 상품만큼 반복문 돌린다
		
		// 3) 장바구니 테이블 비우기(delete)
		
		// 4) 이메일정송, 문자전송, 카톡메세지 전송
		
		// 5) 결제 결과
		
		ModelAndView mav = new ModelAndView();
		
		return null;	// 결제 결과 페이지 이동(우리가 디자인한)
	}
}
