package com.blogspot.tecnologiasjava.model;

import java.io.Serializable; 

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;

@Entity 
public class Cuenta implements Serializable{
	 private static final long serialVersionUID = 1L; 
     
	    @Id 
	    @GeneratedValue 
	    private Long id; 
	    private String numeroDeCuenta; 
	    private String nombreDelTitular; 
	    private Double saldo; 

	    public Long getId() { 
	        return id; 
	    } 

	    public void setId(Long id) { 
	        this.id = id; 
	    } 

	    public String getNumeroDeCuenta() { 
	        return numeroDeCuenta; 
	    } 
	    public void setNumeroDeCuenta(String numeroDeCuenta) { 
	        this.numeroDeCuenta = numeroDeCuenta; 
	    } 

	    public String getNombreDelTitular() { 
	        return nombreDelTitular; 
	    } 

	    public void setNombreDelTitular(String nombreDelTitular) { 
	        this.nombreDelTitular = nombreDelTitular; 
	    } 

	    public Double getSaldo() { 
	        return saldo; 
	    } 

	    public void setSaldo(Double saldo) { 
	        this.saldo = saldo; 
	    } 

	    public void aumentarSaldo(Double cantidad) { 
	        saldo += cantidad; 
	    } 

	    public void reducirSaldo(Double cantidad) { 
	        saldo -= cantidad; 
	    } 


}
