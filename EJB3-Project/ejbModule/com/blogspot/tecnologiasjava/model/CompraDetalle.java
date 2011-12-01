package com.blogspot.tecnologiasjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the compra_detalle database table.
 * 
 */
@Entity
@Table(name="compra_detalle")
public class CompraDetalle extends EntidadBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCompDet;

	private Integer cantidad;

	@Column(name="precio_compra")
	private Double precioCompra;
	
	@ManyToOne
	private Compra compra;

	@ManyToMany()
    private List<Producto> productos = new ArrayList<Producto>();
    
    public List<Producto> getProductos() {
		return productos;
	}
    
    private Integer Id_producto;
    
    public Integer getId_producto() {
		return Id_producto;
	}


	public void setId_producto(Integer id) {
		this.Id_producto = id;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public Integer getIdCompDet() {
		return idCompDet;
	}


	public void setIdCompDet(Integer idCompDet) {
		this.idCompDet = idCompDet;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Double getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}


	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Object getPK(){
		return this.idCompDet;
	}

	public CompraDetalle() {
    
    }
 	
}