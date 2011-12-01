package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Caja;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.CajaManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class CajaManager
 */
@Stateless  
public class CajaManager implements CajaManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Caja entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarCaja(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarCaja(Caja caja) {
    	
    	try {			
    		em.merge(caja);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Caja> listar_remoto(Caja e, String orden)
			throws EntidadBaseException {
		try {			
			List<Caja> lista = (List<Caja>)em.createQuery("select u from Caja u").getResultList();
	    	return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Caja getCaja(Integer idCaja) {
		try {			
			List<Caja> lista = listar();
			for(Caja caja : lista) {
				if (caja.getPK().equals(idCaja))
					
					return caja;
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Caja. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Caja buscar(Integer idCaja){
		try {			
			return getCaja(idCaja);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Caja. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idCaja){
		//Baja
		
		try {			
			em.remove(getCaja(idCaja));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Caja. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Caja> listar(){
		try {			
			List<Caja> lista = (List<Caja>)em.createQuery("select u from Caja u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
