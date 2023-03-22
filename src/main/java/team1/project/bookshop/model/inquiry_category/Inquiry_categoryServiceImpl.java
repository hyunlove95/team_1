package team1.project.bookshop.model.inquiry_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Inquiry_category;
import team1.project.bookshop.exception.Inquiry_categoryException;

@Service
public class Inquiry_categoryServiceImpl implements Inquiry_categoryService{
	
	@Autowired
	private Inquiry_categoryDAO inquiry_categoryDAO;
	
	@Override
	public List selectAll() {
		return inquiry_categoryDAO.selectAll();
	}

	@Override
	public Inquiry_category select(int inquiry_category_idx) {
		return inquiry_categoryDAO.select(inquiry_category_idx);
	}

	@Override
	public void insert(Inquiry_category inquiry_category) throws Inquiry_categoryException{
		inquiry_categoryDAO.insert(inquiry_category);
	}

	@Override
	public void update(Inquiry_category inquiry_category) throws Inquiry_categoryException{
		inquiry_categoryDAO.update(inquiry_category);
	}

	@Override
	public void delete(int inquiry_category_idx) throws Inquiry_categoryException{
		inquiry_categoryDAO.delete(inquiry_category_idx);
	}
	
}
