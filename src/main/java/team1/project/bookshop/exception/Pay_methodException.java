package team1.project.bookshop.exception;

public class Pay_methodException extends RuntimeException{
	public Pay_methodException(String msg) {
		super(msg);
	}
	
	public Pay_methodException(String msg, Throwable e) {
		super(msg, e);
	}
}
