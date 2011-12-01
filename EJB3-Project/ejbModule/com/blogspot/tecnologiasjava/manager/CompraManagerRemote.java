package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Compra;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface CompraManagerRemote{
	public void guardar(Compra compra) throws EntidadBaseException;
	public void agregarCompra(Compra compra);
	public Compra getCompra(Integer idCompra); 
	public Compra buscar(Integer idCompra);
	public void eliminar(Integer idCompra);
	public List<Compra> listar();
	public List<Compra> listar_remoto(Compra e, String orden)	throws EntidadBaseException; 
}
