package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.RolService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.RolService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.Rol;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import com.blogspot.tecnologiasjava.test.*;
//import py.progweb.fpuna.facades.RolManager;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RolServiceImpl extends RemoteServiceServlet implements
RolService {
	
	RolABM administrador;
	public RolServiceImpl()throws NamingException{
		administrador= new RolABM();
	}
	
	@Override
	public Rol buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
	}

	@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	@Override
	public void eliminar(List<Rol> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//rolFacade.eliminar(entidad);	
	}

	@Override
	public void guardar(Rol entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	@Override
	public List<Rol> listar(Rol entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return rolFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
	
	@Override
	public Rol obtenerRolPorNombre(String nombrerol) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return rolFacade.listar_remoto(entidad, orden);
		return administrador.obtenerRolPorNombre(nombrerol);
	}
}
