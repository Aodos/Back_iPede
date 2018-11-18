package com.arthur.ipede.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.TbCliente;
import com.arthur.ipede.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TbCliente user = service.buscaPorEmail(username).get(0);
		
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return  new UserSS(user.getIdt_id_cliente(), user.getEml_email(), user.getPwd_senha());
	}

}
