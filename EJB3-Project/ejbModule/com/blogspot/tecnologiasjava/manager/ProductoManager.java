package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Producto;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.ProductoManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class ProductoManager
 */
@Stateless  
public class ProductoManager implements ProductoManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Producto entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarProducto(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarProducto(Producto producto) {
    	
    	try {			
    		em.merge(producto);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Producto> listar_remoto(Producto e, String orden)
			throws EntidadBaseException {
		try {			
			List<Producto> lista = (List<Producto>)em.createQuery("select u from Producto u").getResultList();
	    	return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Producto getProducto(Integer idProducto) {
		try {			
			List<Producto> lista = listar();
			for(Producto producto : lista) {
				if (producto.getPK().equals(idProducto))
					return producto;
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Producto. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Producto buscar(Integer idProducto){
		try {			
			return getProducto(idProducto);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Producto. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idProducto){
		//Baja
		
		try {			
			em.remove(getProducto(idProducto));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Producto. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Producto> listar(){
		try {			
			List<Producto> lista = (List<Producto>)em.createQuery("select u from Producto u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
