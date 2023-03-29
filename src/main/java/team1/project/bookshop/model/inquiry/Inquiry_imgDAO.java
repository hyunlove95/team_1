package team1.project.bookshop.model.inquiry;

import java.util.List;

import team1.project.bookshop.domain.Inquiry_img;

public interface Inquiry_imgDAO {
	public List selectAll();
	public List selectByInquiry(int inquiry_idx);
	public void insert(Inquiry_img inquiry_img);
	public void delete(int inquiry_idx);
}
