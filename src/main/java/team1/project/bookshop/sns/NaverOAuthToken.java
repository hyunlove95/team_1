package team1.project.bookshop.sns;

import lombok.Data;

@Data
public class NaverOAuthToken {
	private String access_token;
	private String refresh_token; //재발급시 필요한 토큰
	private String token_type;
	private int expires_in; //유효기간
}
