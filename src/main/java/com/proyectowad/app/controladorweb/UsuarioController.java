package com.proyectowad.app.controladorweb;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectowad.app.modelo.dao.IUsuarioDAO;
import com.proyectowad.app.modelo.entidades.Usuario;
import com.proyectowad.app.modelo.service.JpaUserDetailsService;

@Controller
public class UsuarioController{

	@Autowired
	private IUsuarioDAO userRepo;
	
	@RequestMapping(value = "/register")
	public String register(Map<String, Object> model) {
	    //model.addAttribute("user", new Usuario());
	     Usuario user = new Usuario();
	     model.put("user", user);
	    return "register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Usuario user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    userRepo.save(user);
	     
	    return "register_success";
	}

	
}
