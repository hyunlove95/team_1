package team1.project.bookshop.model.member;

import team1.project.bookshop.domain.LoginForm;

public interface LoginFormDAO {
	public LoginForm select(int member_idx);
	public Integer selectIdxByIdAndPass(LoginForm loginForm);
	public void insert(LoginForm loginForm);
	public void update(LoginForm loginForm);
	public void delete(int member_idx);
}