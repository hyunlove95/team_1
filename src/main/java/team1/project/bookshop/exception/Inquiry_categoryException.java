package team1.project.bookshop.exception;

public class Inquiry_categoryException extends RuntimeException{
	
	public Inquiry_categoryException(String msg) {
		super(msg);
	}
	public Inquiry_categoryException(String msg, Throwable e) {
		super(msg, e);
	}
}
