package com.proyectowad.app.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Categoria",schema = "public")
public class Categoria implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "idCategoria")
	private Long id;
	
	@NotEmpty
	@Column(name = "nombre_Categoria")
	private String nombre;
	
	@NotEmpty
	@Column(name = "descripcion_Categoria")
	private String descripcion;
	
	private String foto;
	
	@OneToMany(mappedBy = "categoria", fetch=FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;
	
	
	public Categoria() {
		productos = new ArrayList<Producto>();
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}

	

	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}



	private static final long serialVersionUID = 1L;

}
