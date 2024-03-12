package com.jsclub.fptuclub.JWT;


import com.jsclub.fptuclub.Security.CustomUserDetails;
import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
	@Value("${com.jwt.secret}")
	private String JWT_SECRET;
	@Value("${com.jwt.expiration}")
	private int JWT_EXPIRATION;

	//tao ra jwt tu thong tin user
	public String generateToken(CustomUserDetails customUserDetails) {
		//ngay bat dau co hieu luc
		Date now = new Date();
		//ngay het han
		Date dateExpired = new Date(now.getTime() + JWT_EXPIRATION);
		//tao chuoi  json web token tu username cua user

		return Jwts.builder()
				.setSubject(customUserDetails.getUsername()) //sub
				.setIssuedAt(now) //iat
				.setExpiration(dateExpired) //exp
				//su dung giai thuat de tao chu ki
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET)
				.compact();
	}

	//Lay thong tin user tu jwt
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
				.parseClaimsJws(token).getBody();
		// tra lai usernamee
		return claims.getSubject();
	}
	//validate thong tin cua chuoi jwt
	public boolean validateToken(String authToker){
		try {
			Jwts.parser().setSigningKey(JWT_SECRET)
					.parseClaimsJws(authToker);
			return true;
		}catch (MalformedJwtException ex){
			log.error("Invalid JWT Token");
		}catch (ExpiredJwtException ex){
			log.error("Expired JWT Token");
		}catch (UnsupportedJwtException jwtException){
			log.error("Unsupported JWT Token");
		}catch (IllegalArgumentException ex){
			log.error("JWT claims String is empty");
		}
		return false;
	}
}
