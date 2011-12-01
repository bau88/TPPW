package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Caja;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface CajaManagerRemote{
	public void guardar(Caja caja) throws EntidadBaseException;
	public void agregarCaja(Caja caja);
	public Caja getCaja(Integer idCaja); 
	public Caja buscar(Integer iCaja);
	public void eliminar(Integer idCaja);
	public List<Caja> listar();
	public List<Caja> listar_remoto(Caja e, String orden)	throws EntidadBaseException; 
}
