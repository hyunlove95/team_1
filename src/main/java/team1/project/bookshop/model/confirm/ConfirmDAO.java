package team1.project.bookshop.model.confirm;

import java.util.List;

import team1.project.bookshop.domain.Confirm;

public interface ConfirmDAO {
	public List selectAll();
	public Confirm select(Confirm confirm);
	public void insert(Confirm confirm);
	public void update(boolean order_checked);
	public void delete(int confirm_idx);
}
