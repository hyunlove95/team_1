package team1.project.bookshop.domain;

import lombok.Data;

@Data
public class Current_ship_condition {
	private int current_ship_condition_idx;
	private Orders orders;
	private Ship_condition ship_condition;
	private String invoice_number;	// 송장번호
}
