package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.ProductoManagerRemote;
import com.blogspot.tecnologiasjava.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class ProductoABM {
	ProductoManagerRemote productoFacade;
	public ProductoABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		productoFacade = null; 
		try {          
			productoFacade= (ProductoManagerRemote) ctx.lookup("ProductoManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Producto> getProductos() {
		//alumnos = null;
		List<Producto> todos_productos= new ArrayList<Producto>();
		todos_productos=productoFacade.listar();
		return todos_productos;
	}
	
	
	
	public Producto getProducto(int cedula) {
		List<Producto> productos= new ArrayList<Producto>();
		productos=productoFacade.listar();		
		for(Producto producto : productos) {
			if (producto.getPK().equals(cedula))
				return producto;
		}
		
		return null;
	}
	
    public Producto buscar(Integer cedula){
		return getProducto(cedula);
	}

	public void eliminar(Integer cedula){
		productoFacade.eliminar(cedula.intValue());
	}

	public void guardar(Producto producto){
		productoFacade.guardar(producto);
	}

	public List<Producto> listar(){
		List<Producto> todos_productos= new ArrayList<Producto>();
		todos_productos=productoFacade.listar();
		return todos_productos;

	}

}
