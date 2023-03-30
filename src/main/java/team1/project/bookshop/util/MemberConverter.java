package team1.project.bookshop.util;

import org.springframework.stereotype.Component;

import team1.project.bookshop.domain.Info;
import team1.project.bookshop.domain.LoginForm;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.domain.MemberForm;
import team1.project.bookshop.domain.Sns;

public class MemberConverter {
	
	/**회원가입 시 필요하다...*/
	public Member getMember(MemberForm memberForm) {
		
		Member member = new Member();
		
		member.setId(memberForm.getId());
		member.setName(memberForm.getName());
		member.setEmail(memberForm.getEmail());
		
		member.setSns(getSns(memberForm));
		member.setInfo(getInfo(memberForm));
		member.setLoginForm(getLoginForm(memberForm));
		
		return member;
	}
	
	/**회원가입 시 필요하다...*/
	public Info getInfo(MemberForm memberForm) {
		
		Info info=new Info();
		info.setAddress(memberForm.getAddress());
		info.setAddress_detail(memberForm.getAddress_detail());
		info.setAddress_number(memberForm.getAddress_number());
		info.setPhone_number(memberForm.getPhone_number());
		
		return info;
	}
	
	/**회원가입 시 필요하다...*/
	public LoginForm getLoginForm(MemberForm memberForm) {
		
		LoginForm loginForm = new LoginForm();
		loginForm.setId(memberForm.getId());
		loginForm.setPass(memberForm.getPass());
		
		return loginForm;
	}
	
	public Sns getSns(MemberForm memberForm) {
		
		Sns sns= new Sns();
		sns.setSns_idx(memberForm.getSns_idx());
		return sns;
	}
	
	public MemberForm getMemberForm(Member member) {
		MemberForm memberForm=new MemberForm();
		
		memberForm.setMember_idx(member.getMember_idx());
		memberForm.setId(member.getId());
		memberForm.setName(member.getName());
		if(member.getLoginForm()!=null) {
			memberForm.setPass(member.getLoginForm().getPass());
		}
		memberForm.setEmail(member.getEmail());
		memberForm.setAddress(member.getInfo().getAddress());
		memberForm.setAddress_detail(member.getInfo().getAddress_detail());
		memberForm.setAddress_number(member.getInfo().getAddress_number());
		memberForm.setPhone_number(member.getInfo().getPhone_number());
		memberForm.setSns_idx(member.getSns().getSns_idx());
		memberForm.setMember_point(member.getMember_point());
		return memberForm;
	}
	
	/**sns계정의 info 수정 시 사용*/
	public MemberForm setInfo(MemberForm memberForm, Info info) {
		memberForm.setAddress(info.getAddress());
		memberForm.setAddress_detail(info.getAddress_detail());
		memberForm.setAddress_number(info.getAddress_number());
		memberForm.setPhone_number(info.getPhone_number());
		
		return memberForm;
	}
	
}
