package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Rol;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.RolManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class RolManager
 */
@Stateless  
public class RolManager implements RolManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Rol entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarRol(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarRol(Rol rol) {
    	
    	try {			
    		em.merge(rol);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Rol> listar_remoto(Rol e, String orden)
			throws EntidadBaseException {
		try {			
			List<Rol> lista = (List<Rol>)em.createQuery("select u from Rol u").getResultList();
	    	return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Rol getRol(Integer idRol) {
		try {			
			List<Rol> lista = listar();
			for(Rol rol : lista) {
				if (rol.getPK().equals(idRol))
					return rol;
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Rol. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Rol buscar(Integer idRol){
		try {			
			return getRol(idRol);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Rol. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idRol){
		//Baja
		
		try {			
			em.remove(getRol(idRol));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Rol. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Rol> listar(){
		try {			
			List<Rol> lista = (List<Rol>)em.createQuery("select u from Rol u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
