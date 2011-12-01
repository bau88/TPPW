package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.*;
import com.blogspot.tecnologiasjava.model.Factura;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.CompraManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class CompraManager
 */
@Stateless  
public class CompraDetalleManager implements CompraDetalleManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(CompraDetalle entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarCompraDetalle(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarCompraDetalle(CompraDetalle compradetalle) {
    	
    	try {			
    		em.merge(compradetalle);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<CompraDetalle> listar_remoto(CompraDetalle e, String orden)
			throws EntidadBaseException {
		try {			
			List<CompraDetalle> lista_comp = (List<CompraDetalle>)em.createQuery("select u from CompraDetalle u").getResultList();
			List<CompraDetalle> lista=new ArrayList<CompraDetalle>();
			for (CompraDetalle prov_i:lista_comp){
				prov_i.setProductos(null);
				
				lista.add(prov_i);
			}
			return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public CompraDetalle getCompraDetalle(Integer idCompraDetalle) {
		try {			
			List<CompraDetalle> lista = listar();
			for(CompraDetalle compra : lista) {
				if (compra.getPK().equals(idCompraDetalle)){
					
				    compra.setProductos(null);
					return compra;}
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener CompraDetalle. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public CompraDetalle buscar(Integer idCompraDetalle){
		try {		
			CompraDetalle compra=getCompraDetalle(idCompraDetalle);
			compra.setProductos(null);
			
			return compra;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar CompraDetalle. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idCompraDetalle){
		//Baja
		
		try {			
			em.remove(getCompraDetalle(idCompraDetalle));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar CompraDetalle. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<CompraDetalle> listar(){
		try {			
			List<CompraDetalle> lista_comp = (List<CompraDetalle>)em.createQuery("select u from CompraDetalle u").getResultList();
			List<CompraDetalle> lista=new ArrayList<CompraDetalle>();
			for (CompraDetalle prov_i:lista_comp){
				prov_i.setProductos(null);
				
				lista.add(prov_i);
			}
			return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
