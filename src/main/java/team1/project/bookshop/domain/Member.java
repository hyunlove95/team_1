package team1.project.bookshop.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private int member_idx;
	private String id;
	private String name;
	private String email;
	
	private Sns sns;
	private LoginForm loginForm;
	private Info info;
	
	private int member_point;
	private String create_date;
	private String update_date;
}