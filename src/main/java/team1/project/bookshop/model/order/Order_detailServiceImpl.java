package team1.project.bookshop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Order_detail;
import team1.project.bookshop.exception.Order_detailException;

@Service
public class Order_detailServiceImpl implements Order_detailService{

	@Autowired
	private Order_detailDAO order_detailDAO;

	public List selectAll(Order_detail order_detail) {
		return order_detailDAO.selectAll(order_detail);
	}

	public Order_detail select(Order_detail order_detail) {
		return order_detailDAO.select(order_detail);
	}

	public void insert(Order_detail order_detail) throws Order_detailException{
		order_detailDAO.insert(order_detail);
	}

	public void delete(Order_detail order_detail) throws Order_detailException{
		order_detailDAO.delete(order_detail);
	}
}
