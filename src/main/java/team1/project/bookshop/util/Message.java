package team1.project.bookshop.util;

import lombok.Data;

//클라이언트에게 응답으로 보낼 메시지 객체
@Data
public class Message {
	private int code;
	private String msg;
}
