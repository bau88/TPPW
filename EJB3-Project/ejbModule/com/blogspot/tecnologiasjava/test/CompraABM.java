package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.CompraManagerRemote;
import com.blogspot.tecnologiasjava.model.Compra;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class CompraABM {
	CompraManagerRemote compraFacade;
	public CompraABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		compraFacade = null; 
		try {          
			compraFacade= (CompraManagerRemote) ctx.lookup("CompraManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Compra> getCompras() {
		//alumnos = null;
		List<Compra> todos_compras= new ArrayList<Compra>();
		todos_compras=compraFacade.listar();
		return todos_compras;
	}
	
	
	
	public Compra getCompra(int cedula) {
		List<Compra> compras= new ArrayList<Compra>();
		compras=compraFacade.listar();		
		for(Compra compra : compras) {
			if (compra.getPK().equals(cedula))
				return compra;
		}
		
		return null;
	}
	
    public Compra buscar(Integer cedula){
		return getCompra(cedula);
	}

	public void eliminar(Integer cedula){
		compraFacade.eliminar(cedula.intValue());
	}

	public void guardar(Compra compra){
		compraFacade.guardar(compra);
	}

	public List<Compra> listar(){
		List<Compra> todos_compras= new ArrayList<Compra>();
		todos_compras=compraFacade.listar();
		return todos_compras;

	}

}
