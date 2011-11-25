package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.FacturaDetalleService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.FacturaDetalleService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.FacturaDetalle;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.FacturaDetalleManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class FacturaDetalleServiceImpl extends RemoteServiceServlet implements
FacturaDetalleService {
	//@EJB(beanInterface=CobranzaFacadeLocal.class,mappedName="CobranzaFacade/local")
	FacturaDetalleManager facturadetalleFacade= new FacturaDetalleManager();
	
	//@Override
	public FacturaDetalle buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return facturadetalleFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		facturadetalleFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<FacturaDetalle> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(FacturaDetalle entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		facturadetalleFacade.guardar(entidad);
	}

	//@Override
	public List<FacturaDetalle> listar(FacturaDetalle entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return facturadetalleFacade.listar();
	}
}
