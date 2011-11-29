package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.ClienteService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.ClienteService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.Cliente;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import com.blogspot.tecnologiasjava.test.*;
import com.blogspot.tecnologiasjava.test.*;



/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ClienteServiceImpl extends RemoteServiceServlet implements
		ClienteService {
	ClienteABM administrador;
	public ClienteServiceImpl()throws NamingException{
		administrador= new ClienteABM();
	}
	
	@Override
	public Cliente buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
		
	}

	@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	@Override
	public void eliminar(List<Cliente> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//clienteFacade.eliminar(entidad);	
	}

	@Override
	public void guardar(Cliente entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	@Override
	public List<Cliente> listar(Cliente entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return clienteFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
}
