package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.*;
import com.blogspot.tecnologiasjava.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
public class AdministradorABM {
	UsuarioManagerRemote usuarioFacade;
	RolManagerRemote rolFacade;
	public AdministradorABM() throws NamingException{
		usuarioFacade=null;
		InstanciaFacade instancia= new InstanciaFacade();
		usuarioFacade=instancia.Instanciar(usuarioFacade);	
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
	
	public List<Usuario> getUsuarios() {
		//alumnos = null;
		List<Usuario> todos_usuarios= new ArrayList<Usuario>();
		todos_usuarios=usuarioFacade.listar();
		return todos_usuarios;
	}
	
	
	
	public Usuario getUsuario(int cedula) {
		List<Usuario> usuarios= new ArrayList<Usuario>();
		usuarios=usuarioFacade.listar();		
		for(Usuario usuario : usuarios) {
			if (usuario.getPK().equals(cedula))
				return usuario;
		}
		
		return null;
	}
	
    public Usuario buscar(Integer cedula){
		return getUsuario(cedula);
	}

	public void eliminar(Integer cedula){
		usuarioFacade.eliminar(cedula.intValue());
	}

	public void guardar(Usuario usuario){
		usuarioFacade.guardar(usuario);
	}

	public List<Usuario> listar(){
		List<Usuario> todos_usuarios= new ArrayList<Usuario>();
		todos_usuarios=usuarioFacade.listar();
		return todos_usuarios;

	}
	
	public List<Rol> obtenerRolesUsuario(Integer entity){
		return usuarioFacade.obtenerRolesUsuario(entity);
	}
	
	public void guardarUsuarioRol(Usuario usuario, String rol){
		Rol rol_a_asignar = rolFacade.obtenerRolPorNombre(rol);
		usuario.getRoles().add(rol_a_asignar);
		usuarioFacade.guardar(usuario);
	}

}
