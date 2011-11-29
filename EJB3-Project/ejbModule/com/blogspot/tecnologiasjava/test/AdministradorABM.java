package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.UsuarioManagerRemote;
import com.blogspot.tecnologiasjava.model.Usuario;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
public class AdministradorABM {
	UsuarioManagerRemote usuarioFacade;
	public AdministradorABM() throws NamingException{
		usuarioFacade=null;
		InstanciaFacade instancia= new InstanciaFacade();
		usuarioFacade=instancia.Instanciar(usuarioFacade);	
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

}
