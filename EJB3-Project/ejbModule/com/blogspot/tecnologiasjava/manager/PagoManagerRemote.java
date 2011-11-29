package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Pago;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface PagoManagerRemote{
	public void guardar(Pago producto) throws EntidadBaseException;
	public void agregarPago(Pago producto);
	public Pago getPago(Integer idPago); 
	public Pago buscar(Integer idPago);
	public void eliminar(Integer idPago);
	public List<Pago> listar();
	public List<Pago> listar_remoto(Pago e, String orden)	throws EntidadBaseException; 
}
