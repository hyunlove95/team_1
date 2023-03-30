package team1.project.bookshop.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Info {
	private Member member;
	@NotBlank(message = "주소를 입력해주세요.")
	private String address;
	@NotBlank(message = "상세주소를 입력해주세요.")
	private String address_detail;
	@NotBlank(message = "우편번호를 입력해주세요.")
	@Pattern(regexp = "[0-9]{5,5}", message = "대한민국 우편번호는 숫자 5자리 입니다.")
	private String address_number;
	@NotBlank(message = "휴대폰 번호를 입력해주세요.")
	@Pattern(regexp = "[0-9]{11,11}", message = "휴대폰 번호 11자리를 -를 제외하고 입력해주세요.")
	private String phone_number;
	
	private int member_idx;
}