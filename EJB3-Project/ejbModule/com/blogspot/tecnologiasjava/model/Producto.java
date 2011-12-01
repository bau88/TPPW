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
public class Producto extends EntidadBase implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cantidad;
	private String descripcion;
	private Double porcganancia;
	private Double precio;
	
	@ManyToMany
	private List<Factura> facturas = new ArrayList<Factura>();
	
	@ManyToMany(mappedBy="productos")
	private List<Proveedor> proveedores = new ArrayList<Proveedor>();
	
	@ManyToMany(mappedBy="productos")
	private List<Compra> compras = new ArrayList<Compra>();
	
	 @ManyToMany(mappedBy="productos")
	 private List<CompraDetalle> compradetalles = new ArrayList<CompraDetalle>();
	
	 @ManyToMany(mappedBy="productos")
	 private List<FacturaDetalle> facturadetalles = new ArrayList<FacturaDetalle>();
	

	public List<FacturaDetalle> getFacturadetalles() {
		return facturadetalles;
	}

	public void setFacturadetalles(List<FacturaDetalle> facturadetalles) {
		this.facturadetalles = facturadetalles;
	}

	public List<CompraDetalle> getCompradetalles() {
		return compradetalles;
	}

	public void setCompradetalles(List<CompraDetalle> compradetalles) {
		this.compradetalles = compradetalles;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

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

	
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Object getPK(){
		return this.id;
	}

    
}
