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
    	
    	try {			
    		em.merge(usuario);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@Override 
	public List<Usuario> listar_remoto(Usuario e, String orden)
			throws EntidadBaseException {
		try {			
			List<Usuario> lista = (List<Usuario>)em.createQuery("select u from Usuario u").getResultList();
	    	return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Usuario getUsuario(Integer idUsuario) {
		try {			
			List<Usuario> lista = listar();
			for(Usuario usuario : lista) {
				if (usuario.getPK().equals(idUsuario))
					return usuario;
			}
			
			return null;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Usuario. " + ex.getMessage());
		}
		
	}
	
	@Override	
	public Usuario buscar(Integer idUsuario){
		try {			
			return getUsuario(idUsuario);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar usuario. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idUsuario){
		//Baja
		
		try {			
			em.remove(getUsuario(idUsuario));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar usuario. " + e.getMessage());
		}
	}
	
	@Override
	public List<Usuario> listar(){
		try {			
			List<Usuario> lista = (List<Usuario>)em.createQuery("select u from Usuario u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
	}
	
}
