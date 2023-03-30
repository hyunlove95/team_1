package team1.project.bookshop.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
	private int member_idx;
	@NotBlank(message = "아이디를 입력해주세요.")
	@Pattern(regexp = "(?=.[a-zA-Z])[0-9a-zA-Z]{1,15}", message = "아이디는 영문을 포함한 영문과 숫자로 1~15자리 이내로 입력해주세요")
	private String id;
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	@NotBlank(message = "암호를 입력해주세요.")
	private String pass;
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	@NotBlank(message = "우편번호를 입력해주세요.")
	@Pattern(regexp = "[0-9]{5,5}", message = "대한민국 우편번호는 숫자 5자리 입니다.")
	private String address_number;
	@NotBlank(message = "주소를 입력해주세요.")
	private String address;
	@NotBlank(message = "상세주소를 입력해주세요.")
	private String address_detail;
	@NotBlank(message = "휴대폰 번호를 입력해주세요.")
	@Pattern(regexp = "[0-9]{11,11}", message = "휴대폰 번호 11자리를 -를 제외하고 입력해주세요.")
	private String phone_number;
	
	
	private int sns_idx;
	private int member_point;
	private String create_date;
	private String update_date;
	
}
