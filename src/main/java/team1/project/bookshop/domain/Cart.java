package team1.project.bookshop.domain;

import lombok.Data;

@Data
public class Cart {
	private int cart_idx;
	private Book book;
	private Member member;
	private int quantity;
}
