package team1.project.bookshop.model.admin;

import team1.project.bookshop.domain.Admin;

public interface AdminDAO {
	public void insert(Admin admin);
	public Admin select(Admin admin);
}
