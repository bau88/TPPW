package com.blogspot.tecnologiasjava.model;

import java.io.Serializable; 
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="proveedor")
public class Proveedor extends EntidadBase implements Serializable{
	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	private static final long serialVersionUID = 1L; 
	 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@ManyToMany
	private List<Producto> productos = new ArrayList<Producto>();
	
	@OneToMany(mappedBy="proveedor")
	private List<Compra> compras = new ArrayList<Compra>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public Object getPK(){
		return this.id;
	}
}
