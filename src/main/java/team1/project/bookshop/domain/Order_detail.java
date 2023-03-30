package team1.project.bookshop.domain;

import lombok.Data;

@Data
public class Order_detail {
	private int order_detail_idx;
	private Orders orders;
	private Book book;
	private int final_price;
	private int quantity;
	private boolean refund;
}
