package com.blogspot.tecnologiasjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the factura_detalle database table.
 * 
 */
@Entity
@Table(name="factura_detalle")
public class FacturaDetalle extends EntidadBase implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer cantidad;

	@Column(name="precio_venta")
	private Double precioVenta;
	
	@ManyToOne
	private Factura factura;
	
	@ManyToMany()
    private List<Producto> productos = new ArrayList<Producto>();
    

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	private Integer Id_producto;
	    
	public Integer getId_producto() {
			return Id_producto;
	}


	public void setId_producto(Integer id) {
			this.Id_producto = id;
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

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public Object getPK(){
		return this.id;
	}

	
}