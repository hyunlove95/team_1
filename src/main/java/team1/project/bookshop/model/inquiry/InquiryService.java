package team1.project.bookshop.model.inquiry;

import java.util.List;

import team1.project.bookshop.domain.Inquiry;

public interface InquiryService {
	public List selectAll();
	public Inquiry select(int inquiry_idx);
	public void regist(Inquiry inquiry, String dir); //db연동+file제어+메일발송+sms
	public void update(Inquiry inquiry);
	public void delete(int inquiry_idx);
}
