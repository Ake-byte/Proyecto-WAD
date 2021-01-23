package com.proyectowad.app.modelo.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectowad.app.modelo.entidades.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);

}
