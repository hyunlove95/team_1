package team1.project.bookshop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Confirm;
import team1.project.bookshop.exception.ConfirmException;

@Service
public class ConfirmServiceImpl implements ConfirmService{

	@Autowired
	private ConfirmDAO confirmDAO;
	
	public List selectAll() {
		return confirmDAO.selectAll();
	}

	public Confirm select(Confirm confirm) {
		return confirmDAO.select(confirm);
	}

	public void insert(Confirm confirm) throws ConfirmException{
		confirmDAO.insert(confirm);
	}

	public void update(boolean order_checked) throws ConfirmException{
		confirmDAO.update(order_checked);
	}

	public void delete(int confirm_idx) throws ConfirmException{
		confirmDAO.delete(confirm_idx);
	}
}
