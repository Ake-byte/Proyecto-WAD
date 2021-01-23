package com.proyectowad.app.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectowad.app.modelo.dao.ICategoriaDAO;
import com.proyectowad.app.modelo.entidades.Categoria;

@Service
public class CategoriaService implements ICategoriaService{
	
	@Autowired
	private ICategoriaDAO categoriaDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Categoria categoria) {
		categoriaDAO.save(categoria);
	}

	@Override
	@Transactional(readOnly=true)
	public Categoria findOne(Long id) {
		return categoriaDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		categoriaDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Categoria> findAll(Pageable pageable) {
		return categoriaDAO.findAll(pageable);		
	}
}
