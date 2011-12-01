package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.*;
import com.blogspot.tecnologiasjava.model.Producto;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.FacturaManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class FacturaDetalleManager
 */
@Stateless  
public class FacturaDetalleManager implements FacturaDetalleManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(FacturaDetalle entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarFacturaDetalle(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarFacturaDetalle(FacturaDetalle factura) {
    	
    	try {			
    		em.merge(factura);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<FacturaDetalle> listar_remoto(FacturaDetalle e, String orden)
			throws EntidadBaseException {
		try {			
			List<FacturaDetalle> lista_fact = (List<FacturaDetalle>)em.createQuery("select u from FacturaDetalle u").getResultList();
			List<FacturaDetalle> lista=new ArrayList<FacturaDetalle>();
			for (FacturaDetalle prov_i:lista_fact){
				
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
	public FacturaDetalle getFacturaDetalle(Integer idFacturaDetalle) {
		try {			
			List<FacturaDetalle> lista = listar();
			for(FacturaDetalle factura : lista) {
				if (factura.getPK().equals(idFacturaDetalle)){
					
					factura.setProductos(null);
					
					return factura;}
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener FacturaDetalle. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public FacturaDetalle buscar(Integer idFacturaDetalle){
		try {		
			FacturaDetalle factura=getFacturaDetalle(idFacturaDetalle);
			
			factura.setProductos(null);
			
			return factura;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar FacturaDetalle. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idFactura){
		//Baja
		
		try {			
			em.remove(getFacturaDetalle(idFactura));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar FacturaDetalle. " + e.getMessage());
		}
	}
	
	@Override
	public List<FacturaDetalle> listar(){
		try {			
			List<FacturaDetalle> lista_fact = (List<FacturaDetalle>)em.createQuery("select u from FacturaDetalle u").getResultList();
			List<FacturaDetalle> lista=new ArrayList<FacturaDetalle>();
			for (FacturaDetalle prov_i:lista_fact){
				
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
