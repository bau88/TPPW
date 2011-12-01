package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.*;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface FacturaDetalleManagerRemote{
	public void guardar(FacturaDetalle factura) throws EntidadBaseException;
	public void agregarFacturaDetalle(FacturaDetalle factura);
	public FacturaDetalle getFacturaDetalle(Integer idFacturaDetalle); 
	public FacturaDetalle buscar(Integer idFacturaDetalle);
	public void eliminar(Integer idFacturaDetalle);
	public List<FacturaDetalle> listar();
	public List<FacturaDetalle> listar_remoto(FacturaDetalle e, String orden)	throws EntidadBaseException; 
	
}
