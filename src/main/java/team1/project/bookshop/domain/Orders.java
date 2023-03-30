package team1.project.bookshop.domain;

import lombok.Data;

@Data
public class Orders {
	private int orders_idx;
	private Member member;
	private String order_date;	// 주문일
	private String ship_date;		// 배송일
	private int total_pay;
	private Pay_method pay_method;	// 결제 방법
}
