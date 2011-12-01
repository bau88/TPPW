package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Compra;
import com.blogspot.tecnologiasjava.model.Factura;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.CompraManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class CompraManager
 */
@Stateless  
public class CompraManager implements CompraManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Compra entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarCompra(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarCompra(Compra compra) {
    	
    	try {			
    		em.merge(compra);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Compra> listar_remoto(Compra e, String orden)
			throws EntidadBaseException {
		try {			
			List<Compra> lista_comp = (List<Compra>)em.createQuery("select u from Compra u").getResultList();
			List<Compra> lista=new ArrayList<Compra>();
			for (Compra prov_i:lista_comp){
				prov_i.setProductos(null);
				prov_i.setCompraDetalles(null);
				lista.add(prov_i);
			}
			return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Compra getCompra(Integer idCompra) {
		try {			
			List<Compra> lista = listar();
			for(Compra compra : lista) {
				if (compra.getPK().equals(idCompra)){
					compra.setCompraDetalles(null);
				    compra.setProductos(null);
					return compra;}
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Compra. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Compra buscar(Integer idCompra){
		try {		
			Compra compra=getCompra(idCompra);
			compra.setProductos(null);
			compra.setCompraDetalles(null);
			return compra;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Compra. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idCompra){
		//Baja
		
		try {			
			em.remove(getCompra(idCompra));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Compra. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Compra> listar(){
		try {			
			List<Compra> lista_comp = (List<Compra>)em.createQuery("select u from Compra u").getResultList();
			List<Compra> lista=new ArrayList<Compra>();
			for (Compra prov_i:lista_comp){
				prov_i.setProductos(null);
				prov_i.setCompraDetalles(null);
				lista.add(prov_i);
			}
			return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
