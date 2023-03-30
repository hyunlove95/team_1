package team1.project.bookshop.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team1.project.bookshop.domain.LoginForm;
import team1.project.bookshop.exception.MemberException;

@Repository
public class MybatisLoginFormDAO implements LoginFormDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public LoginForm select(int member_idx) {
		return sqlSessionTemplate.selectOne("LoginForm.select", member_idx);
	}

	@Override
	public Integer selectIdxByIdAndPass(LoginForm loginForm) {
		return sqlSessionTemplate.selectOne("LoginForm.selectIdxByIdAndPass", loginForm);
	}

	@Override
	public void insert(LoginForm loginForm) {
		int result = sqlSessionTemplate.insert("LoginForm.insert", loginForm);
		if(result<1) {
			throw new MemberException("회원 등록 실패");
		}
	}

	@Override
	public void update(LoginForm loginForm) {
		int result = sqlSessionTemplate.update("LoginForm.update", loginForm);
		if(result<1) {
			throw new MemberException("회원 수정 실패");
		}
	}

	@Override
	public void delete(int member_idx) {
		int result = sqlSessionTemplate.delete("LoginForm.delete", member_idx);
		if(result<1) {
			throw new MemberException("회원 삭제 실패");
		}
	}

}