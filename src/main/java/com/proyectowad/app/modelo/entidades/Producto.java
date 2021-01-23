package com.proyectowad.app.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "Producto",schema = "public")
public class Producto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "idProducto")
	private Long id;
	
	@NotEmpty
	@Column(name = "nombre_Producto")
	private String nombre;
	
	@NotEmpty
	@Column(name = "descripcion_Producto")
	private String descripcion;
	
	
	private double precio;
	
	
	private Integer cantidad;
	
	//private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
	//@JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    //@ManyToOne
	private Categoria categoria;
	
	/*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "compra_id")
	private List<ItemCompra> items;*/
	
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



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public Integer getCantidad() {
		return cantidad;
	}



	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	

	/*public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}*/



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	private static final long serialVersionUID = 1L;

}
