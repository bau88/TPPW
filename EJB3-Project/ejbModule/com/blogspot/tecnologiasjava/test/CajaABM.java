package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.CajaManagerRemote;
import com.blogspot.tecnologiasjava.model.Caja;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class CajaABM {
	CajaManagerRemote cajaFacade;
	public CajaABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		cajaFacade = null; 
		try {          
			cajaFacade= (CajaManagerRemote) ctx.lookup("CajaManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Caja> getCajas() {
		//alumnos = null;
		List<Caja> todos_cajas= new ArrayList<Caja>();
		todos_cajas=cajaFacade.listar();
		return todos_cajas;
	}
	
	
	
	public Caja getCaja(int cedula) {
		List<Caja> cajas= new ArrayList<Caja>();
		cajas=cajaFacade.listar();		
		for(Caja caja : cajas) {
			if (caja.getPK().equals(cedula))
				return caja;
		}
		
		return null;
	}
	
    public Caja buscar(Integer cedula){
		return getCaja(cedula);
	}

	public void eliminar(Integer cedula){
		cajaFacade.eliminar(cedula.intValue());
	}

	public void guardar(Caja caja){
		cajaFacade.guardar(caja);
	}

	public List<Caja> listar(){
		List<Caja> todos_cajas= new ArrayList<Caja>();
		todos_cajas=cajaFacade.listar();
		return todos_cajas;

	}

}
