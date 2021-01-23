package com.proyectowad.app.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyectowad.app.modelo.entidades.Categoria;

public interface ICategoriaService {
	public List<Categoria> findAll();
	
	public Page<Categoria> findAll(Pageable pageable);

	public void save(Categoria categoria);

	public Categoria findOne(Long id);

	public void delete(Long id);
}
