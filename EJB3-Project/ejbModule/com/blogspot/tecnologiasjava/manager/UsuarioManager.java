package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Usuario;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.UsuarioManagerRemote;

import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class UsuarioManager
 */
@Stateless  
public class UsuarioManager implements UsuarioManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Usuario entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarUsuario(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarUsuario(Usuario usuario) {
    	em.merge(usuario);
    	//em.persist(usuario); 
    } 

	@Override 
	public List<Usuario> listar_remoto(Usuario e, String orden)
			throws EntidadBaseException {
		List<Usuario> lista = (List<Usuario>)em.createQuery("select r from usuario r").getResultList();
    	return lista;
		
		
	}
	
	@Override 
	public Usuario getUsuario(Integer idUsuario) {
		List<Usuario> lista = listar();
		for(Usuario usuario : lista) {
			if (usuario.getPK().equals(idUsuario))
				return usuario;
		}
		
		return null;
	}
	
	@Override	
	public Usuario buscar(Integer idUsuario){
		return getUsuario(idUsuario);
	}
	
	@Override
	public void eliminar(Integer idUsuario){
		//Baja
		em.remove(getUsuario(idUsuario));
	}
	
	@Override
	public List<Usuario> listar(){
		List<Usuario> lista = (List<Usuario>)em.createQuery("select r from usuario r").getResultList();
    	return lista;

	}
	
}