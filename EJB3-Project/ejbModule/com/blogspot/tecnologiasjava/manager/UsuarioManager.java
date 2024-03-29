package com.blogspot.tecnologiasjava.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.blogspot.tecnologiasjava.model.*;
import com.blogspot.tecnologiasjava.model.Usuario;
import com.blogspot.tecnologiasjava.manager.*;

import com.blogspot.tecnologiasjava.manager.UsuarioManagerRemote;
import javax.annotation.security.PermitAll;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;

/**
 * Session Bean implementation class UsuarioManager
 */
@Stateless  
public class UsuarioManager implements UsuarioManagerRemote {
	
    @PersistenceContext(unitName="Curso") 
    private EntityManager em; 
	
	@Override
	public void guardar(Usuario entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		/*if (entidad.getId_caja() != null)
			entidad.setCaja(cajafacade.buscar(entidad.getId_caja()));
		if (entidad.getId_rol() != null)
			entidad.setRol(rolfacade.buscar(entidad.getId_rol()));*/
		try {			
			agregarUsuario(entidad);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
	}
	
	@Override 
    public void agregarUsuario(Usuario usuario) {
    	
    	try {			
    		em.merge(usuario);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Objeto no guardado. " + e.getMessage());
		}
    	//em.persist(usuario); 
    } 

	@PermitAll
	public List<Usuario> listar_remoto(Usuario e, String orden)
			throws EntidadBaseException {
		try {			
			List<Usuario> lista = (List<Usuario>)em.createQuery("select u from Usuario u").getResultList();
			List<Usuario> listado_nuevo = new ArrayList<Usuario>();
		 	for(Usuario usuario: lista){				
			 	if (usuario.getRoles() != null)
				 	usuario.setRoles(null);
			 
			 	listado_nuevo.add(usuario);
		 	}
	    		return listado_nuevo;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Listado. " + ex.getMessage());
		}
		
		
		
	}
	
	@Override 
	public Usuario getUsuario(Integer idUsuario) {
		try {			
			List<Usuario> lista = listar();
			for(Usuario usuario : lista) {
				if (usuario.getPK().equals(idUsuario)){
					usuario.setRoles(null);
					return usuario;}
			}
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: En Obtener Usuario. " + ex.getMessage());
		}
		return null;
		
	}
	
	@Override	
	public Usuario buscar(Integer idUsuario){
		try {		
			Usuario usuario=getUsuario(idUsuario);
			usuario.setRoles(null);
			return usuario;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar usuario. " + e.getMessage());
		}
		
	}
	
	@Override
	public void eliminar(Integer idUsuario){
		//Baja
		
		try {			
			em.remove(getUsuario(idUsuario));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al eliminar usuario. " + e.getMessage());
		}
	}
	
	@PermitAll
	public List<Usuario> listar(){
		try {			
			List<Usuario> lista = (List<Usuario>)em.createQuery("select u from Usuario u").getResultList();
			//Se setea a null setRoles
			List<Usuario> listado_nuevo = new ArrayList<Usuario>();
		 	for(Usuario usuario: lista){				
			 	if (usuario.getRoles() != null)
				 	usuario.setRoles(null);
			 
			 	listado_nuevo.add(usuario);
		 	}
	    		return listado_nuevo;

		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al listar. " + e.getMessage());
		}
		
		
	}
	@Override
	public List<Rol> obtenerRolesUsuario(Integer idUsuario){
		try {		
			List<Usuario> lista = (List<Usuario>)em.createQuery("select u from Usuario u").getResultList();
			List<Rol> roles=null;
			for(Usuario usuario : lista) {
				if (usuario.getPK().equals(idUsuario))
					//usuario.setRoles(null);
					roles=usuario.getRoles();
			}
			List<Rol> rolesnuevo= new ArrayList<Rol>();
			for (Rol rol: roles){
				rol.setUsuarios(null);
				rolesnuevo.add(rol);
			}
			return rolesnuevo;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EntidadBaseException("ERROR: Al buscar roles de usuario. " + e.getMessage());
		}
	}
	
}
