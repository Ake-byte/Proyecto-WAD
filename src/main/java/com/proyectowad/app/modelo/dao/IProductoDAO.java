package com.proyectowad.app.modelo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyectowad.app.modelo.entidades.Producto;

public interface IProductoDAO extends PagingAndSortingRepository<Producto, Long>{

}
