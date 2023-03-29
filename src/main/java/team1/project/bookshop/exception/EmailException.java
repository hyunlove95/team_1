package team1.project.bookshop.exception;

public class EmailException extends RuntimeException{
	
	public EmailException(String msg) {
		super(msg);
	}
	public EmailException(String msg, Throwable e) {
		super(msg, e);
	}
}
