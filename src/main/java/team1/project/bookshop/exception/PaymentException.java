package team1.project.bookshop.exception;

public class PaymentException extends RuntimeException{
	public PaymentException(String msg) {
		super(msg);
	}
	
	public PaymentException(String msg, Throwable e) {
		super(msg, e);
	}
}
