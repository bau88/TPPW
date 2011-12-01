package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Factura;
import com.blogspot.tecnologiasjava.model.Producto;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.FacturaManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class FacturaManager
 */
@Stateless  
public class FacturaManager implements FacturaManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Factura entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarFactura(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarFactura(Factura factura) {
    	
    	try {			
    		em.merge(factura);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Factura> listar_remoto(Factura e, String orden)
			throws EntidadBaseException {
		try {			
			List<Factura> lista_fact = (List<Factura>)em.createQuery("select u from Factura u").getResultList();
			List<Factura> lista=new ArrayList<Factura>();
			for (Factura prov_i:lista_fact){
				prov_i.setPagos(null);
				prov_i.setProductos(null);
				prov_i.setFacturaDetalles(null);
				prov_i.setCliente(null);
				lista.add(prov_i);
			}
			return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Factura getFactura(Integer idFactura) {
		try {			
			List<Factura> lista = listar();
			for(Factura factura : lista) {
				if (factura.getPK().equals(idFactura)){
					factura.setPagos(null);
					factura.setProductos(null);
					factura.setCliente(null);
					factura.setFacturaDetalles(null);
					return factura;}
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Factura. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Factura buscar(Integer idFactura){
		try {		
			Factura factura=getFactura(idFactura);
			factura.setPagos(null);
			factura.setProductos(null);
			factura.setCliente(null);
			factura.setFacturaDetalles(null);
			return factura;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Factura. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idFactura){
		//Baja
		
		try {			
			em.remove(getFactura(idFactura));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Factura. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Factura> listar(){
		try {			
			List<Factura> lista_fact = (List<Factura>)em.createQuery("select u from Factura u").getResultList();
			List<Factura> lista=new ArrayList<Factura>();
			for (Factura prov_i:lista_fact){
				prov_i.setPagos(null);
				prov_i.setProductos(null);
				prov_i.setCliente(null);
				prov_i.setFacturaDetalles(null);
				lista.add(prov_i);
			}
			return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
	public int facturar(Factura factura){
		int nro_factura=factura.getId().intValue();
		return nro_factura;
	}
	
}
