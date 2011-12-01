package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.CompraService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.CompraService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.Compra;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import com.blogspot.tecnologiasjava.manager.*;
import com.blogspot.tecnologiasjava.test.*;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CompraServiceImpl extends RemoteServiceServlet implements
		CompraService {
	CompraABM administrador;
	public CompraServiceImpl()throws NamingException{
        administrador= new CompraABM();
	}	
	
	//@Override
	public Compra buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	//@Override
	public void eliminar(List<Compra> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Compra entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	//@Override
	public List<Compra> listar(Compra entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
}
