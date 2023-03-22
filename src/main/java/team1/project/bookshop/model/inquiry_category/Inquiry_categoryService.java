package team1.project.bookshop.model.inquiry_category;

import java.util.List;

import team1.project.bookshop.domain.Inquiry_category;

public interface Inquiry_categoryService {
	public List selectAll();
	public Inquiry_category select(int inquiry_category_idx);
	public void insert(Inquiry_category inquiry_category);
	public void update(Inquiry_category inquiry_category);
	public void delete(int inquiry_category_idx);
}
