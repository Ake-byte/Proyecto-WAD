package com.proyectowad.app.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyectowad.app.modelo.entidades.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{

	public Usuario findByUsername(String username);

}
