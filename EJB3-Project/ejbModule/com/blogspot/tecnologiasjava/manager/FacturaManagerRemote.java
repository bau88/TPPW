package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Factura;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface FacturaManagerRemote{
	public void guardar(Factura factura) throws EntidadBaseException;
	public void agregarFactura(Factura factura);
	public Factura getFactura(Integer idFactura); 
	public Factura buscar(Integer idFactura);
	public void eliminar(Integer idFactura);
	public List<Factura> listar();
	public List<Factura> listar_remoto(Factura e, String orden)	throws EntidadBaseException; 
	public int facturar(Factura factura) throws EntidadBaseException;
}
