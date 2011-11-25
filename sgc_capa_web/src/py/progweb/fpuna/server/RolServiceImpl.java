package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.RolService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.RolService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Rol;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.RolManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RolServiceImpl extends RemoteServiceServlet implements
RolService {
	//@EJB(beanInterface=RolFacadeLocal.class,mappedName="RolFacade/local")
	RolManager rolFacade= new RolManager();
	
	//@Override
	public Rol buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return rolFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		rolFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Rol> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//rolFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Rol entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		rolFacade.guardar(entidad);
	}

	//@Override
	public List<Rol> listar(Rol entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return rolFacade.listar_remoto(entidad, orden);
		return rolFacade.listar();
	}
}
