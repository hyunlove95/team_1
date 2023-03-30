package team1.project.bookshop.exception;

public class Order_detailException extends RuntimeException{
	public Order_detailException(String msg) {
		super(msg);
	}
	
	public Order_detailException(String msg, Throwable e) {
		super(msg, e);
	}
}
