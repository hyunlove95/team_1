package team1.project.bookshop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Pay_method;
import team1.project.bookshop.exception.Pay_methodException;

@Service
public class Pay_methodServiceImpl implements Pay_methodService{

	@Autowired
	private Pay_methodDAO pay_methodDAO;
	
	public List selectAll() {
		return pay_methodDAO.selectAll();
	}

	public Pay_method select(int pay_method_idx) {
		return pay_methodDAO.select(pay_method_idx);
	}

	public void insert(Pay_method pay_method) throws Pay_methodException{
		pay_methodDAO.insert(pay_method);
	}

	public void update(Pay_method pay_method) throws Pay_methodException{
		pay_methodDAO.update(pay_method);
	}

	public void delete(int pay_method_idx) throws Pay_methodException{
		pay_methodDAO.delete(pay_method_idx);
	}
}
