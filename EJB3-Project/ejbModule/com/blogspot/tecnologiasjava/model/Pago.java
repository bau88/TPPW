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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pago")
public class Pago extends EntidadBase implements Serializable{
	 private static final long serialVersionUID = 1L; 
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 
	 //Los demas atributos seran persistidos por reflexion
	 private Date fechacierre;
	 
	 private Integer monto;
	 
	 private String cerrado;
	 
	/* @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	 private RegistroPago registroPago;*/
	 
	/* @ManyToOne
	 private Factura factura;*/

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

	public Date getFechacierre() {
		return fechacierre;
	}

	public void setFechacierre(Date fechacierre) {
		this.fechacierre = fechacierre;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	public String getCerrado() {
		return cerrado;
	}

	public void setCerrado(String cerrado) {
		this.cerrado = cerrado;
	}

	/*public RegistroPago getRegistroPago() {
		return registroPago;
	}

	public void setRegistroPago(RegistroPago registroPago) {
		this.registroPago = registroPago;
	}*/
	public Object getPK(){
		return this.id;
	}
}
