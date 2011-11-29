package com.blogspot.tecnologiasjava.manager;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
//import com.blogspot.tecnologiasjava.ejb.EAOManager;
import com.blogspot.tecnologiasjava.model.Cliente;
import com.blogspot.tecnologiasjava.model.EntidadBaseException;


@Remote
public interface ClienteManagerRemote{
	public void guardar(Cliente cliente) throws EntidadBaseException;
	public void agregarCliente(Cliente cliente);
	public Cliente getCliente(Integer idCliente); 
	public Cliente buscar(Integer idCliente);
	public void eliminar(Integer idCliente);
	public List<Cliente> listar();
	public List<Cliente> listar_remoto(Cliente e, String orden)	throws EntidadBaseException; 
}
