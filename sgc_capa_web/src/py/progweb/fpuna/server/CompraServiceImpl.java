package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.CompraService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CompraService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Compra;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.CompraManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CompraServiceImpl extends RemoteServiceServlet implements
		CompraService {
	//@EJB(beanInterface=CobranzaFacadeLocal.class,mappedName="CobranzaFacade/local")
	CompraManager compraFacade= new CompraManager();
	
	//@Override
	public Compra buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return compraFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		compraFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Compra> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Compra entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		compraFacade.guardar(entidad);
	}

	//@Override
	public List<Compra> listar(Compra entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return compraFacade.listar();
	}
}
