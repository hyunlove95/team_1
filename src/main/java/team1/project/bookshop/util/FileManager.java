package team1.project.bookshop.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import team1.project.bookshop.domain.Inquiry;
import team1.project.bookshop.domain.Inquiry_img;
import team1.project.bookshop.exception.UploadException;

@Component
public class FileManager {

	//파일명 생성하기 
	public String createFileName(String filename) {
		//dkdkdd.jpg
		long time=System.currentTimeMillis();
		String ext=filename.substring(filename.lastIndexOf(".")+1, filename.length());
		
		return time+"."+ext;
	} 
	
	//지정된 디렉토리로 파일 저장 dir 저장될 디렉토리 위치
	public void save(Inquiry inquiry, String dir) throws UploadException{
		MultipartFile[] photoList=inquiry.getPhoto();
		
		//비어있는 리스트 만들어주기 
		List<Inquiry_img> inquiry_imgList = new ArrayList<Inquiry_img>();
		inquiry.setInquiry_imgList(inquiry_imgList);;
		
		//그리고 난 다음, 
		try {
			for(MultipartFile photo : photoList) {
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Inquiry_img inquiry_img =new Inquiry_img();
				inquiry_img.setInquiry(inquiry);; //이 시점에 pk 존재함
				inquiry_img.setFilename(createFileName(photo.getOriginalFilename()));;
				
				//리스트에  pimg  추가 
				inquiry_imgList.add(inquiry_img);
				
				photo.transferTo(new File(dir+inquiry_img.getFilename()));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		}
	}
	
}