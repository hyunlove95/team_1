package team1.project.bookshop.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import team1.project.bookshop.exception.HashException;

public class PassConverter {
	
	public static String convertHash(String text) throws HashException{
		
		StringBuilder sb = new StringBuilder();
		
		try {
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			//입력받은 비밀번호 스트링 쪼개기
			byte[] hash = digest.digest(text.getBytes("UTF-8"));
			for(int i=0;i<hash.length;i++) {
				//16진수 문자열로 변환
				String hex=Integer.toHexString(0xff&hash[i]);
				if(hex.length()==1) {
					sb.append("0"); //1자리수 → 0추가해서 2자리수
				}
				sb.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new HashException("암호화 실패", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new HashException("암호화 실패", e);
		}
		String password=sb.toString();
		return password;
	}
	
}
	

