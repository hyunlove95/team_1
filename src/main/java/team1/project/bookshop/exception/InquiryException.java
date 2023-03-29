package team1.project.bookshop.exception;

public class InquiryException extends RuntimeException{
	
	public InquiryException(String msg) {
		super(msg);
	}
	public InquiryException(String msg, Throwable e) {
		super(msg, e);
	}
}
