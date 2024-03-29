package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.ClienteManagerRemote;
import com.blogspot.tecnologiasjava.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class ClienteABM {
	ClienteManagerRemote clienteFacade;
	public ClienteABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		clienteFacade = null; 
		try {          
			clienteFacade= (ClienteManagerRemote) ctx.lookup("ClienteManager/remote");  
			
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Cliente> getClientes() {
		//alumnos = null;
		List<Cliente> todos_clientes= new ArrayList<Cliente>();
		todos_clientes=clienteFacade.listar();
		return todos_clientes;
	}
	
	
	
	public Cliente getCliente(int cedula) {
		List<Cliente> clientes= new ArrayList<Cliente>();
		clientes=clienteFacade.listar();		
		for(Cliente cliente : clientes) {
			if (cliente.getPK().equals(cedula))
				return cliente;
		}
		
		return null;
	}
	
    public Cliente buscar(Integer cedula){
		return getCliente(cedula);
	}

	public void eliminar(Integer cedula){
		clienteFacade.eliminar(cedula.intValue());
	}

	public void guardar(Cliente cliente){
		clienteFacade.guardar(cliente);
	}

	public List<Cliente> listar(){
		List<Cliente> todos_clientes= new ArrayList<Cliente>();
		todos_clientes=clienteFacade.listar();
		return todos_clientes;

	}

}
