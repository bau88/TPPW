package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.CobranzaService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CobranzaService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.Pago;
import py.progweb.fpuna.excepciones.EntidadBaseException;

import com.blogspot.tecnologiasjava.test.*;



/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CobranzaServiceImpl extends RemoteServiceServlet implements
		CobranzaService {
	PagoABM administrador;
	public CobranzaServiceImpl()throws NamingException{
        administrador= new PagoABM();
	}	
	
	//@Override
	public Pago buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	//@Override
	public void eliminar(List<Pago> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Pago entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	//@Override
	public List<Pago> listar(Pago entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
}
