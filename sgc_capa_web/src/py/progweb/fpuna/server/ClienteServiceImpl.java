package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.ClienteService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.ClienteService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Cliente;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.ClienteManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ClienteServiceImpl extends RemoteServiceServlet implements
		ClienteService {
	//@EJB(beanInterface=ClienteFacadeLocal.class,mappedName="ClienteFacade/local")
	ClienteManager clienteFacade= new ClienteManager();
	
	@Override
	public Cliente buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return clienteFacade.buscar(entity);
		
	}

	@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		clienteFacade.eliminar(id);
	}

	@Override
	public void eliminar(List<Cliente> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//clienteFacade.eliminar(entidad);	
	}

	@Override
	public void guardar(Cliente entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		clienteFacade.guardar(entidad);
	}

	@Override
	public List<Cliente> listar(Cliente entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return clienteFacade.listar_remoto(entidad, orden);
		return clienteFacade.listar();
	}
}
