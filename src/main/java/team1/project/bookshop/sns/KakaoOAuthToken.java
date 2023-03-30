package team1.project.bookshop.sns;

import lombok.Data;

@Data
public class KakaoOAuthToken {
	private String access_token;
	private String token_type;
	private String refresh_token; //재발급시 필요한 토큰
	private int expires_in; //유효기간
	private String scope;
	private String refresh_token_expires_in;
}
