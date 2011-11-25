package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.FacturaService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.FacturaService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Factura;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.FacturaManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class FacturaServiceImpl extends RemoteServiceServlet implements
FacturaService {
	//@EJB(beanInterface=CobranzaFacadeLocal.class,mappedName="CobranzaFacade/local")
	FacturaManager facturaFacade= new FacturaManager();
	
	//@Override
	public Factura buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return facturaFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		facturaFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Factura> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Factura entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		facturaFacade.guardar(entidad);
	}

	//@Override
	public List<Factura> listar(Factura entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return facturaFacade.listar();
	}
}
