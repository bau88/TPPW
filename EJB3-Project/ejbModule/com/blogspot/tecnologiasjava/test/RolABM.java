package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.RolManagerRemote;
import com.blogspot.tecnologiasjava.model.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class RolABM {
	RolManagerRemote rolFacade;
	public RolABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		rolFacade = null; 
		try {          
			rolFacade= (RolManagerRemote) ctx.lookup("RolManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Rol> getRoles() {
		//alumnos = null;
		List<Rol> todos_roles= new ArrayList<Rol>();
		todos_roles=rolFacade.listar();
		return todos_roles;
	}
	
	
	
	public Rol getRol(int cedula) {
		List<Rol> roles= new ArrayList<Rol>();
		roles=rolFacade.listar();		
		for(Rol rol : roles) {
			if (rol.getPK().equals(cedula))
				return rol;
		}
		
		return null;
	}
	
    public Rol buscar(Integer cedula){
		return getRol(cedula);
	}

	public void eliminar(Integer cedula){
		rolFacade.eliminar(cedula.intValue());
	}

	public void guardar(Rol rol){
		rolFacade.guardar(rol);
	}

	public List<Rol> listar(){
		List<Rol> todos_roles= new ArrayList<Rol>();
		todos_roles=rolFacade.listar();
		return todos_roles;

	}

}
