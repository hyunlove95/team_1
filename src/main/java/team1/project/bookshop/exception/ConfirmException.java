package team1.project.bookshop.exception;

public class ConfirmException extends RuntimeException{
	public ConfirmException(String msg) {
		super(msg);
	}
	
	public ConfirmException(String msg, Throwable e) {
		super(msg, e);
	}
}
