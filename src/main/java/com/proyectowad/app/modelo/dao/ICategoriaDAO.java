package com.proyectowad.app.modelo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyectowad.app.modelo.entidades.Categoria;

public interface ICategoriaDAO extends PagingAndSortingRepository<Categoria, Long>{

}
