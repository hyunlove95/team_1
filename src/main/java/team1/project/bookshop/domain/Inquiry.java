package team1.project.bookshop.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Inquiry {
	private int Inquiry_idx;
	private String title;
	private String content;
	private String regdate;
	private boolean result;
	private Inquiry_category inquiry_category;
	private List<Inquiry_img> Inquiry_imgList;
	private MultipartFile[] photo;
}
