package com.proyectowad.app.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectowad.app.modelo.dao.IProductoDAO;
import com.proyectowad.app.modelo.entidades.Producto;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoDAO productoDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoDAO.save(producto);
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findOne(Long id) {
		return productoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDAO.deleteById(id);
	}

	@Override
	public Page<Producto> findAll(Pageable pageable) {
		return productoDAO.findAll(pageable);
	}

}
