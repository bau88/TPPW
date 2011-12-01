package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Pago;
import com.blogspot.tecnologiasjava.model.Producto;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.PagoManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class PagoManager
 */
@Stateless  
public class PagoManager implements PagoManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Pago entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarPago(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarPago(Pago pago) {
    	
    	try {			
    		em.merge(pago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Pago> listar_remoto(Pago e, String orden)
			throws EntidadBaseException {
		try {			
			List<Pago> lista_pago = (List<Pago>)em.createQuery("select u from Pago u").getResultList();
			
			return lista_pago;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Pago getPago(Integer idPago) {
		try {			
			List<Pago> lista = listar();
			for(Pago pago : lista) {
				if (pago.getPK().equals(idPago)){
					return pago;}
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Pago. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Pago buscar(Integer idPago){
		try {			
			return getPago(idPago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Pago. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idPago){
		//Baja
		
		try {			
			em.remove(getPago(idPago));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Pago. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Pago> listar(){
		try {			
			List<Pago> lista = (List<Pago>)em.createQuery("select u from Pago u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
