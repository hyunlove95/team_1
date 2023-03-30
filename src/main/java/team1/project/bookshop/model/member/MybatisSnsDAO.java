package team1.project.bookshop.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team1.project.bookshop.domain.Sns;

@Repository
public class MybatisSnsDAO implements SnsDAO{

	@Autowired private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Sns.selectAll");
	}

	@Override
	public Sns selectById(int sns_idx) {
		return sqlSessionTemplate.selectOne("Sns.selectByIdx", sns_idx);
	}

	@Override
	public Sns selectByName(String sns_name) {
		return sqlSessionTemplate.selectOne("Sns.selectByName", sns_name);
	}

}