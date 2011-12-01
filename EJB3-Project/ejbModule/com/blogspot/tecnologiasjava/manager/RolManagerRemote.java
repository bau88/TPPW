package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Rol;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface RolManagerRemote{
	public void guardar(Rol rol) throws EntidadBaseException;
	public void agregarRol(Rol rol);
	public Rol getRol(Integer idRol); 
	public Rol buscar(Integer idRol);
	public void eliminar(Integer idRol);
	public List<Rol> listar();
	public Rol obtenerRolPorNombre(String nombreRol);
	public List<Rol> listar_remoto(Rol e, String orden)	throws EntidadBaseException; 
}
