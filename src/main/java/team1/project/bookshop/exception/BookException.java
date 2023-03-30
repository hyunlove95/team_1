package team1.project.bookshop.exception;

public class BookException extends RuntimeException{
	public BookException(String msg) {
		super(msg);
	}
	
	public BookException(String msg, Throwable e) {
		super(msg, e);
	}
}
