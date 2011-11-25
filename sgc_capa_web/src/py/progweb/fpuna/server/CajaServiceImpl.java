package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.CajaService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CajaService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Caja;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.CajaManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CajaServiceImpl extends RemoteServiceServlet implements
		CajaService {
	//@EJB(beanInterface=CajaFacadeLocal.class,mappedName="CajaFacade/local")
	CajaManager cajaFacade= new CajaManager();
	
	//@Override
	public Caja buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return cajaFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		cajaFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Caja> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cajaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Caja entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		cajaFacade.guardar(entidad);
	}

	//@Override
	public List<Caja> listar(Caja entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cajaFacade.listar_remoto(entidad, orden);
		return cajaFacade.listar();
	}
}
