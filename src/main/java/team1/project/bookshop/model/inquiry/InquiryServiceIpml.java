package team1.project.bookshop.model.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team1.project.bookshop.domain.Inquiry;
import team1.project.bookshop.domain.Inquiry_img;
import team1.project.bookshop.exception.InquiryException;
import team1.project.bookshop.exception.Inquiry_imgException;
import team1.project.bookshop.exception.UploadException;
import team1.project.bookshop.util.FileManager;

@Service
public class InquiryServiceIpml implements InquiryService{
	
	//DAO 모델
	@Autowired
	private InquiryDAO inquiryDAO;
	
	@Autowired
	private Inquiry_imgDAO inquiry_imgDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List selectAll() {
		return inquiryDAO.selectAll();
	}

	@Override
	public Inquiry select(int inquiry_idx) {
		return inquiryDAO.select(inquiry_idx);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void regist(Inquiry inquiry, String dir) throws InquiryException, UploadException, Inquiry_imgException{
		//1:1게시물 저장 (부모 Inquiry)
		inquiryDAO.insert(inquiry);//select-key에 의해 pk존재하게 됨
		
		//파일저장 
		fileManager.save(inquiry, dir);
		
		List<Inquiry_img> inquiry_imgList=inquiry.getInquiry_imgList();
		
		for(Inquiry_img inquiry_img : inquiry_imgList) {
			inquiry_imgDAO.insert(inquiry_img);
		}		
	}

	@Override
	public void update(Inquiry inquiry) {
		inquiryDAO.update(inquiry);
	}

	@Override
	public void delete(int inquiry_idx) {
		inquiryDAO.delete(inquiry_idx);
	}

}
