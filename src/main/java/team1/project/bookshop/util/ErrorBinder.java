package team1.project.bookshop.util;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorBinder {
	
	public JSONObject bindError(BindingResult bindingResult) {
		List<ObjectError> errors = bindingResult.getAllErrors();
		HashMap<String, String> map = new HashMap<String, String>();
		for(ObjectError error : errors) {
			//log.info("회원정보 수정 시 발생된 에러메시지들 : {}", error.getDefaultMessage());
			FieldError fieldError = (FieldError) error;
			//log.info("필드 이름은 {}", fieldError.getField());
			map.put(fieldError.getField(), error.getDefaultMessage());
		}
		map.put("msg", "회원가입에 실패하셨습니다.");
		return new JSONObject(map);
	}
}
