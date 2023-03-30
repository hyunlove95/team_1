package team1.project.bookshop.model.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team1.project.bookshop.domain.Admin;


@Repository
public class MybatisAdminDAO implements AdminDAO{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Admin login(Admin admin) {
		return sqlSessionTemplate.selectOne("Admin.login", admin);
	}
	
	
}
