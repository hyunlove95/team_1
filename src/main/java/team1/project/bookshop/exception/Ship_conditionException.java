package team1.project.bookshop.exception;

public class Ship_conditionException extends RuntimeException{
	public Ship_conditionException(String msg) {
		super(msg);
	}
	
	public Ship_conditionException(String msg, Throwable e) {
		super(msg, e);
	}
}
