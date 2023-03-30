package team1.project.bookshop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseBody {
	private int totalAmount;
	private String method;	// 결제방법(pay_method.payment)
	private Card card;
}
