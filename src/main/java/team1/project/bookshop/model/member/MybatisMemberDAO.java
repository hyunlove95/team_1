package team1.project.bookshop.model.member;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.exception.MemberException;

@Slf4j
@Primary
@Repository
public class MybatisMemberDAO implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Member.selectAll");
	}
	
	@Override
	public int selectCountMember() {
		int result=sqlSessionTemplate.selectOne("Member.selectCountMember");
		return result;
	}

	//로그인용
	@Override
	public Member select(int member_idx) {
		return sqlSessionTemplate.selectOne("Member.select", member_idx);
	}

	public int idCheck(String id) {
		int result=sqlSessionTemplate.selectOne("Member.idCheck", id);
		log.info("DAO의 ID : {}", id);
		if(id.isBlank()) {
			result=1;
		}
		log.info("결과 값은 {} 입니다.", result);
		return result;
	}

	
	public void insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result<1) {
			throw new MemberException("회원 등록 실패");
		}
		
	}

	public void update(Member member) throws MemberException {
		int result = sqlSessionTemplate.update("Member.update", member);
		if(result<1) {
			throw new MemberException("회원 수정 실패");
		}
	}

	public void delete(int member_idx) throws MemberException {
		int result = sqlSessionTemplate.delete("Member.delete", member_idx);
		if(result<1) {
			throw new MemberException("회원 삭제 실패");
		}
	}

	@Override
	public Member selectById(String id) {
		return sqlSessionTemplate.selectOne("Member.selectById", id);
	}

	/**Member만 가져온다... 하위 테이블 가져오지 않음.*/
	@Override
	public Member selectSnsMemberById(String id) {
		return sqlSessionTemplate.selectOne("Member.selectSnsMemberById", id);
	}

	@Override
	public List search(String keyword) {
		return sqlSessionTemplate.selectList("Member.search", keyword);
	}


}