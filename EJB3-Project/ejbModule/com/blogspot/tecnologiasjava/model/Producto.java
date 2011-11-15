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
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidad;
	private String descripcion;
	private Double porcganancia;
	
	@ManyToMany
	private List<Venta> ventas = new ArrayList<Venta>();
	
	@ManyToMany(mappedBy="productos")
	private List<Proveedor> proveedores = new ArrayList<Proveedor>();
	
	@ManyToMany(mappedBy="productos")
	private List<Compra> compras = new ArrayList<Compra>();
	

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPorcganancia() {
		return porcganancia;
	}

	public void setPorcganancia(Double porcganancia) {
		this.porcganancia = porcganancia;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
    
}
