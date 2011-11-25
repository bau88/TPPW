package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.CobranzaService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CobranzaService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Pago;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.CobranzaManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CobranzaServiceImpl extends RemoteServiceServlet implements
		CobranzaService {
	//@EJB(beanInterface=CobranzaFacadeLocal.class,mappedName="CobranzaFacade/local")
	CobranzaManager cobranzaFacade= new CobranzaManager();
	
	//@Override
	public Pago buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return cobranzaFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		cobranzaFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Pago> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Pago entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		cobranzaFacade.guardar(entidad);
	}

	//@Override
	public List<Pago> listar(Pago entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return cobranzaFacade.listar();
	}
}
