package com.jsclub.fptuclub.JWT;

import com.jsclub.fptuclub.Security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	private String getJwtFromRequest(HttpServletRequest request){
		String bearerToken = request.getHeader("Authorizaton");
		//Kiem tra header authorization co jwt khong
		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
			return bearerToken.substring(7);
		}
		return null;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try{
			//Lay jwt tu request
			String jwt = getJwtFromRequest(request);
			if(StringUtils.hasText(jwt)&&jwtTokenProvider.validateToken(jwt)){
				//lay username tu jwt
				String userName = jwtTokenProvider.getUsernameFromJwt(jwt);
				//lay thong tin nguuoi dung tu username
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
				if(userDetails!=null){
					//neu nguoi dung hop le set thong tin cho security context
					UsernamePasswordAuthenticationToken authentication =
							new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}catch (Exception e){
			log.error("Fail on set user authentication",e);
		}
		filterChain.doFilter(request,response);
	}
}
