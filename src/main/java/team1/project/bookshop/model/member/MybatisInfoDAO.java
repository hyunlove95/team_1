package team1.project.bookshop.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Info;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.exception.MemberException;
@Slf4j
@Repository
public class MybatisInfoDAO implements InfoDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Info select(int member_idx) {
		return sqlSessionTemplate.selectOne("Info.select", member_idx);
	}

	@Override
	public void insert(Info info) {
		int result = sqlSessionTemplate.insert("Info.insert", info);
		if(result<1) {
			throw new MemberException("회원 등록 실패");
		}
	}
	
	@Override
	public void snsInsert(Info info) {
		int result = sqlSessionTemplate.insert("Info.snsInsert", info);
		if(result<1) {
			throw new MemberException("회원 등록 실패");
		}
	}
	

	@Override
	public void update(Info info) {
		int result = sqlSessionTemplate.update("Info.update", info);
		if(result<1) {
			throw new MemberException("회원 수정 실패");
		}
	}
	
	@Override
	/**sns계정용 update*/
	public void snsUpdate(Info info) {
		log.info("왜안돼냐");
		int result = sqlSessionTemplate.update("Info.snsUpdate", info);
		if(result<1) {
			throw new MemberException("회원 수정 실패");
		}
	}

	@Override
	public void delete(int member_idx) {
		int result = sqlSessionTemplate.delete("Info.delete", member_idx);
		if(result<1) {
			throw new MemberException("회원 삭제 실패");
		}
	}

	@Override
	public Info getSnsInfo(int member_idx) {
		return sqlSessionTemplate.selectOne("Info.selectByMember", member_idx);
	}

}