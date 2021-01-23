package com.proyectowad.app.modelo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyectowad.app.modelo.entidades.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);

	public void save(Producto categoria);

	public Producto findOne(Long id);

	public void delete(Long id);
}
