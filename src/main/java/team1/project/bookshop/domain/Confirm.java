package team1.project.bookshop.domain;

import lombok.Data;

@Data
public class Confirm {
	private int confirm_idx;
	private Admin admin;
	private Orders orders;
	private boolean order_checked;
}
