package team1.project.bookshop.model.cart;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.project.bookshop.domain.Cart;
import team1.project.bookshop.domain.Member;
import team1.project.bookshop.exception.BookException;
import team1.project.bookshop.exception.CartException;
import team1.project.bookshop.exception.MemberException;
import team1.project.bookshop.model.book.BookDAO;
import team1.project.bookshop.model.member.MemberDAO;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private MemberDAO memberDAO;

	public List selectAll(Cart cart) {
		return cartDAO.selectAll(cart);
	}

	public Cart select(Cart cart) {
		return cartDAO.select(cart);
	}
	
	public int selectDuplicate(Cart cart) {
		int result = cartDAO.selectDuplicate(cart);
		return result;
	}

	public void regist(Cart cart) throws CartException, BookException, MemberException{
		// book_idx 가져오기
		bookDAO.select(cart.getBook().getBook_idx());
		
		// member_idx 가져오기
		memberDAO.select(cart.getMember().getMember_idx());
		
		// cart insert
		cartDAO.insert(cart);
	}

	public void delete(Cart cart) throws CartException{
		cartDAO.delete(cart);
	}
}
