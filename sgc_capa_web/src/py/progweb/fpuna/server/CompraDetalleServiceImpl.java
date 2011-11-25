package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.CompraDetalleService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CompraDetalleService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.CompraDetalle;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.CompraDetalleManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CompraDetalleServiceImpl extends RemoteServiceServlet implements
CompraDetalleService {
	//@EJB(beanInterface=CobranzaFacadeLocal.class,mappedName="CobranzaFacade/local")
	CompraDetalleManager compradetalleFacade= new CompraDetalleManager();
	
	//@Override
	public CompraDetalle buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return compradetalleFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		compradetalleFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<CompraDetalle> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(CompraDetalle entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		compradetalleFacade.guardar(entidad);
	}

	//@Override
	public List<CompraDetalle> listar(CompraDetalle entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return compradetalleFacade.listar();
	}
}
