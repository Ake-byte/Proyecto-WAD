package com.proyectowad.app.modelo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectowad.app.modelo.dao.IUsuarioDAO;
import com.proyectowad.app.modelo.entidades.Role;
import com.proyectowad.app.modelo.entidades.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDAO.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error login: no existe el usuario '" + username + "'");
			throw new UsernameNotFoundException("Username " + username + "no existe");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role: usuario.getRoles()) {
			logger.info("Rol: " + role.getAuthority());
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if(authorities.isEmpty()) {
			logger.error("Error login: no existe el usuario '" + username + "no tiene rol asignado");
			throw new UsernameNotFoundException("Error login: no existe el usuario '" + username + "no tiene rol asignado");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
