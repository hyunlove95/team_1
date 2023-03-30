package team1.project.bookshop.domain;

import lombok.Data;

@Data
public class Book {
	private int book_idx;
	private String book_name;
	private String writer;
	private String summary;
	private String publisher;
	private String create_date;
	private String admin_name;
	private int price;
	private int discount;
	private int point;
	private String filename;
}
