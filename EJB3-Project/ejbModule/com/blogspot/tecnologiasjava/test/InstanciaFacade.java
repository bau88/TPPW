package com.blogspot.tecnologiasjava.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.blogspot.tecnologiasjava.manager.UsuarioManagerRemote;

public class InstanciaFacade {
	Object instancia;
	public InstanciaFacade(){
		
	}
	public UsuarioManagerRemote Instanciar(UsuarioManagerRemote usuarioFacade2) throws NamingException{
		 
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		usuarioFacade2 = null; 
		try {          
			usuarioFacade2= (UsuarioManagerRemote) ctx.lookup("UsuarioManager/remote");  
			return usuarioFacade2;
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		    return null;
	}

}
