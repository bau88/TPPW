package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.FacturaService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.FacturaService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.*;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import com.blogspot.tecnologiasjava.manager.*;
import com.blogspot.tecnologiasjava.test.*;
//import py.progweb.fpuna.facades.RolFacadeLocal;
import com.blogspot.tecnologiasjava.test.ProductoABM;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class FacturaServiceImpl extends RemoteServiceServlet implements
FacturaService {
	
	FacturaABM administrador;
	public FacturaServiceImpl()throws NamingException{
        administrador= new FacturaABM();
	}	
	
	@Override
	public Factura buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
	}

	@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	@Override
	public void eliminar(List<Factura> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//cobranzaFacade.eliminar(entidad);	
	}

	@Override
	public void guardar(Factura entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	@Override
	public List<Factura> listar(Factura entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return cobranzaFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
	
	@Override
	public Integer facturar(Factura entidad){
		return administrador.facturar(entidad);
	}
	
	@Override
	public Integer guardarFacturaConDetalles(Factura entidad){
		
		return administrador.guardarFacturaConDetalles(entidad);
	}
}
