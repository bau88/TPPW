package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Producto;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface ProductoManagerRemote{
	public void guardar(Producto producto) throws EntidadBaseException;
	public void agregarProducto(Producto producto);
	public Producto getProducto(Integer idProducto); 
	public Producto buscar(Integer idProducto);
	public void eliminar(Integer idProducto);
	public List<Producto> listar();
	public List<Producto> listar_remoto(Producto e, String orden)	throws EntidadBaseException; 
}
