package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Proveedor;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.ProveedorManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class ProveedorManager
 */
@Stateless  
public class ProveedorManager implements ProveedorManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Proveedor entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarProveedor(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarProveedor(Proveedor proveedor) {
    	
    	try {			
    		em.merge(proveedor);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Proveedor> listar_remoto(Proveedor e, String orden)
			throws EntidadBaseException {
		try {			
			List<Proveedor> lista = (List<Proveedor>)em.createQuery("select u from Proveedor u").getResultList();
	    	return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Proveedor getProveedor(Integer idProveedor) {
		try {			
			List<Proveedor> lista = listar();
			for(Proveedor proveedor : lista) {
				if (proveedor.getPK().equals(idProveedor))
					return proveedor;
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Proveedor. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Proveedor buscar(Integer idProveedor){
		try {			
			return getProveedor(idProveedor);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Proveedor. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idProveedor){
		//Baja
		
		try {			
			em.remove(getProveedor(idProveedor));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Proveedor. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Proveedor> listar(){
		try {			
			List<Proveedor> lista = (List<Proveedor>)em.createQuery("select u from Proveedor u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
