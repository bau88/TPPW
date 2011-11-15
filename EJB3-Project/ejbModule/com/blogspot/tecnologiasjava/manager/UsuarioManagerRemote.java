package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Usuario;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface UsuarioManagerRemote{
	public void guardar(Usuario usuario) throws EntidadBaseException;
	public void agregarUsuario(Usuario usuario);
	public Usuario getUsuario(Integer idUsuario); 
	public Usuario buscar(Integer idUsuario);
	public void eliminar(Integer idUsuario);
	public List<Usuario> listar();
	public List<Usuario> listar_remoto(Usuario e, String orden)	throws EntidadBaseException; 
}
