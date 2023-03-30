package team1.project.bookshop.model.member;

import java.util.List;

import team1.project.bookshop.domain.Sns;


public interface SnsDAO {
	public List selectAll();
	public Sns selectById(int sns_idx);
	public Sns selectByName(String sns_name);
}