package team1.project.bookshop.model.pay_method;

import java.util.List;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Pay_method;

public interface Pay_methodDAO {
	public List selectAll();
	public Pay_method select(int pay_method_idx);
	public void insert(Pay_method pay_method);
	public void update(Pay_method pay_method);
	public void delete(int pay_method_idx);
}
