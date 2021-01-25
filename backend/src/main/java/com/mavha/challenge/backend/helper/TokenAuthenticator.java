package com.mavha.challenge.backend.helper;

import java.util.Date;

import com.mavha.challenge.backend.model.User;
import com.mavha.challenge.backend.service.ServiceException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticator {

	private static final String KEY = "MysecReT12345";
	private static final String ID = "tokenAuth";
	
	public static final String PREFIXCLAIM = "userId";
	
	public static User generate(User user) {
		String token = "Bearer " + Jwts.builder()
		.setId(ID)
		.setSubject(user.getEmail())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.claim(PREFIXCLAIM, user.getId())
			.signWith(SignatureAlgorithm.HS512, KEY.getBytes())
			.compact();
		user.setToken(token);		
		return user;
	}
	
	public static String decode(String token) throws ServiceException {
		try {
			return Jwts.parser().setSigningKey(KEY.getBytes())
					.parseClaimsJws(token.replace("Bearer ", "")).getBody().get(PREFIXCLAIM).toString();			
		} catch (MalformedJwtException e) {
			throw new ServiceException(409,Response.TOKEN_NOT_VALIDATE);
		}
	} 
	
}
