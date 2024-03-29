package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.ProveedorManagerRemote;
import com.blogspot.tecnologiasjava.model.Proveedor;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class ProveedorABM {
	ProveedorManagerRemote proveedorFacade;
	public ProveedorABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		proveedorFacade = null; 
		try {          
			proveedorFacade= (ProveedorManagerRemote) ctx.lookup("ProveedorManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Proveedor> getProveedors() {
		//alumnos = null;
		List<Proveedor> todos_proveedors= new ArrayList<Proveedor>();
		todos_proveedors=proveedorFacade.listar();
		return todos_proveedors;
	}
	
	
	
	public Proveedor getProveedor(int cedula) {
		List<Proveedor> proveedores= new ArrayList<Proveedor>();
		proveedores=proveedorFacade.listar();		
		for(Proveedor proveedor : proveedores) {
			if (proveedor.getPK().equals(cedula))
				return proveedor;
		}
		
		return null;
	}
	
    public Proveedor buscar(Integer cedula){
		return getProveedor(cedula);
	}

	public void eliminar(Integer cedula){
		proveedorFacade.eliminar(cedula.intValue());
	}

	public void guardar(Proveedor proveedor){
		proveedorFacade.guardar(proveedor);
	}

	public List<Proveedor> listar(){
		List<Proveedor> todos_proveedores= new ArrayList<Proveedor>();
		todos_proveedores=proveedorFacade.listar();
		return todos_proveedores;

	}

}
