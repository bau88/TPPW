package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Proveedor;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface ProveedorManagerRemote{
	public void guardar(Proveedor proveedor) throws EntidadBaseException;
	public void agregarProveedor(Proveedor proveedor);
	public Proveedor getProveedor(Integer idProveedor); 
	public Proveedor buscar(Integer idProveedor);
	public void eliminar(Integer idProveedor);
	public List<Proveedor> listar();
	public List<Proveedor> listar_remoto(Proveedor e, String orden)	throws EntidadBaseException; 
}
