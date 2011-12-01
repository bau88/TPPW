package com.blogspot.tecnologiasjava.model;


import java.io.Serializable; 
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="factura")
public class Factura extends EntidadBase implements Serializable{
	 private static final long serialVersionUID = 1L; 
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 private Integer numero;
	 private Date fecha;
	 private String pendiente;
	 private Double saldo;
	
	 //Relacion con Cliente
	 @ManyToOne
	 private Cliente cliente;
	 
	 //Relacion con Producto
	@ManyToMany(mappedBy="facturas")
	private List<Producto> productos = new ArrayList<Producto>();
	 
	//Relacion con Pago
	@OneToMany(mappedBy="factura")
	 private List<Pago> pagos = new ArrayList<Pago>();
	 
	@OneToMany(mappedBy="factura")
	private List<FacturaDetalle> facturaDetalles = new ArrayList<FacturaDetalle>();

	 public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}

	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public Date getFecha() {
			return fecha;
		}
		
		 public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		
		 public String getPendiente() {
			return pendiente;
		}
		
		 public void setPendiente(String pendiente) {
			this.pendiente = pendiente;
		}
		
		 public Double getSaldo() {
			return saldo;
		}
		
		 public void setSaldo(Double saldo) {
			this.saldo = saldo;
		}
		
	 public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	/*public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Object getPK(){
		return this.id;
	}
		
}
