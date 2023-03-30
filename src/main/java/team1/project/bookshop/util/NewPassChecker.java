package team1.project.bookshop.util;

import team1.project.bookshop.domain.NewPassCheckForm;

public class NewPassChecker {
	
	public Message newpasscheck(NewPassCheckForm newPassCheckForm) {
		
		Message message=new Message();
		
		//새 비밀번호는 입력했지만, 확인은 입력 안했을 때
		if(	
				!newPassCheckForm.getNewpass().equals("") &&
				newPassCheckForm.getNewpasscheck().equals("")) {
			message.setMsg("새 비밀번호 확인을 입력해주세요.");
			message.setCode(3);
		}//새 비밀번호, 확인 둘다 입력 안했을 때 -> 기존의 비밀번호로 수정
		else if(
				newPassCheckForm.getNewpass().equals("") &&
				newPassCheckForm.getNewpasscheck().equals("")) {
			message.setMsg("");
			message.setCode(1);
		}//비밀번호 일치할 때 (둘다 입력했을 때) -> 새 비밀번호로 수정
		else if(newPassCheckForm.getNewpass().equals(newPassCheckForm.getNewpasscheck())) {
			message.setMsg("새 비밀번호가 일치합니다.");
			message.setCode(0);
		}//새 비밀번호만 입력하고 확인은 안했을 때, 비밀번호가 틀릴 때
		else {
			message.setMsg("새 비밀번호가 일치하지 않습니다.");
			message.setCode(2);
		}
		return message;
	}
	
}

