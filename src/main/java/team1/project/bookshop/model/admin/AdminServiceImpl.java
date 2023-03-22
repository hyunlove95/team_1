package team1.project.bookshop.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Admin;
import team1.project.bookshop.exception.AdminException;


@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;
	
	public void insert(Admin admin) throws AdminException{
		adminDAO.insert(admin);
	}
	
	public Admin select(Admin admin) throws AdminException{
		return adminDAO.select(admin);
	}

	
}
