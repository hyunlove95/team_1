package team1.project.bookshop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
	private Member member;
	private String id;
	private String pass;
	
	private Boolean before_info=false;
}