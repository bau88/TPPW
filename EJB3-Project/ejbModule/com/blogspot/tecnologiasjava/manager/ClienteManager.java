package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blogspot.tecnologiasjava.model.Cliente;
import com.blogspot.tecnologiasjava.manager.*;

//import com.blogspot.tecnologiasjava.manager.ClienteManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class ClienteManager
 */
@Stateless  
public class ClienteManager implements ClienteManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Cliente entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarCliente(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarCliente(Cliente cliente) {
    	
    	try {			
    		em.merge(cliente);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Cliente> listar_remoto(Cliente e, String orden)
			throws EntidadBaseException {
		try {			
			List<Cliente> lista = (List<Cliente>)em.createQuery("select u from Cliente u").getResultList();
	    	return lista;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Cliente getCliente(Integer idCliente) {
		try {			
			List<Cliente> lista = listar();
			for(Cliente cliente : lista) {
				if (cliente.getPK().equals(idCliente))
					return cliente;
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Cliente. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Cliente buscar(Integer idCliente){
		try {			
			return getCliente(idCliente);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar Cliente. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idCliente){
		//Baja
		
		try {			
			em.remove(getCliente(idCliente));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar Cliente. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Cliente> listar(){
		try {			
			List<Cliente> lista = (List<Cliente>)em.createQuery("select u from Cliente u").getResultList();
	    	return lista;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	
}
