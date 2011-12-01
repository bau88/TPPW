package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.CajaService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CajaService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.Caja;

import py.progweb.fpuna.excepciones.EntidadBaseException;
import com.blogspot.tecnologiasjava.manager.CajaManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;
import com.blogspot.tecnologiasjava.test.ClienteABM;
import com.blogspot.tecnologiasjava.test.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CajaServiceImpl extends RemoteServiceServlet implements
		CajaService {
	CajaABM administrador;
	public CajaServiceImpl()throws NamingException{
		administrador= new CajaABM();
	}
	
	//@Override
	public Caja buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	//@Override
	public void eliminar(List<Caja> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cajaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Caja entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	//@Override
	public List<Caja> listar(Caja entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cajaFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
}
