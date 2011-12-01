package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.*;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface CompraDetalleManagerRemote{
	public void guardar(CompraDetalle compra) throws EntidadBaseException;
	public void agregarCompraDetalle(CompraDetalle compra);
	public CompraDetalle getCompraDetalle(Integer idCompraDetalle); 
	public CompraDetalle buscar(Integer idCompraDetalle);
	public void eliminar(Integer idCompraDetalle);
	public List<CompraDetalle> listar();
	public List<CompraDetalle> listar_remoto(CompraDetalle e, String orden)	throws EntidadBaseException; 
}
