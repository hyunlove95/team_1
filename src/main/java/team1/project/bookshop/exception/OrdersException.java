package team1.project.bookshop.exception;

public class OrdersException extends RuntimeException{
	public OrdersException(String msg) {
		super(msg);
	}
	
	public OrdersException(String msg, Throwable e) {
		super(msg, e);
	}
}
