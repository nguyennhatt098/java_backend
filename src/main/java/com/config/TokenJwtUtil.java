package com.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import static java.util.Collections.emptyList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

public class TokenJwtUtil {
	static final long EXPIRATIONTIME = 86_400_000; // 1 day
	static final String SECRET = "nhat";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public static String generateJwt(String userId) {
		long expirationTime = EXPIRATIONTIME;
		return Jwts.builder().setId(userId).setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token
			String userId = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getId();
//	            String userId = claims.getId();

			return userId != null ? new UsernamePasswordAuthenticationToken(userId, null, emptyList()) : null;
		}
		return null;
	}

	public static String getMD5(String data) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(data.getBytes());
		byte[] digest = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(Integer.toHexString((int) (b & 0xff)));
		}
		return sb.toString();
	}
}
