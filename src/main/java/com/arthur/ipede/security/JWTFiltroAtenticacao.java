package com.arthur.ipede.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arthur.ipede.config.Credenciais;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTFiltroAtenticacao extends UsernamePasswordAuthenticationFilter{

	
	private AuthenticationManager authMan;
	
	private JWTUtil jwtUtil;
	
	public JWTFiltroAtenticacao(AuthenticationManager authMan, JWTUtil jwtUtil) {
		this.authMan = authMan;
		this.jwtUtil = jwtUtil;
	}
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
												HttpServletResponse res) throws AuthenticationException{
		
		try {
			Credenciais cred = new ObjectMapper()
					.readValue(req.getInputStream(), Credenciais.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getSenha());
			
			Authentication auth = authMan.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws AuthenticationException{
		String email = ((UserSS)auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(email);
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
	}
	
	
}
