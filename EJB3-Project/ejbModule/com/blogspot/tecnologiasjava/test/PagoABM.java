package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.PagoManagerRemote;
import com.blogspot.tecnologiasjava.model.Pago;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class PagoABM {
	PagoManagerRemote pagoFacade;
	public PagoABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		pagoFacade = null; 
		try {          
			pagoFacade= (PagoManagerRemote) ctx.lookup("PagoManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Pago> getPagos() {
		//alumnos = null;
		List<Pago> todos_pagos= new ArrayList<Pago>();
		todos_pagos=pagoFacade.listar();
		return todos_pagos;
	}
	
	
	
	public Pago getPago(int cedula) {
		List<Pago> pagos= new ArrayList<Pago>();
		pagos=pagoFacade.listar();		
		for(Pago pago : pagos) {
			if (pago.getPK().equals(cedula))
				return pago;
		}
		
		return null;
	}
	
    public Pago buscar(Integer cedula){
		return getPago(cedula);
	}

	public void eliminar(Integer cedula){
		pagoFacade.eliminar(cedula.intValue());
	}

	public void guardar(Pago pago){
		pagoFacade.guardar(pago);
	}

	public List<Pago> listar(){
		List<Pago> todos_pagos= new ArrayList<Pago>();
		todos_pagos=pagoFacade.listar();
		return todos_pagos;

	}

}
