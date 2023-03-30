package team1.project.bookshop.exception;

public class Current_ship_conditionException extends RuntimeException{
	public Current_ship_conditionException(String msg) {
		super(msg);
	}
	
	public Current_ship_conditionException(String msg, Throwable e) {
		super(msg, e);
	}
}
