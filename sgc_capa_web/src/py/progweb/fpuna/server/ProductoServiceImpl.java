package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import py.progweb.fpuna.client.services.ProductoService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.ProductoService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.blogspot.tecnologiasjava.model.Producto;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import com.blogspot.tecnologiasjava.test.*;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProductoServiceImpl extends RemoteServiceServlet implements
		ProductoService {
	ProductoABM administrador;
	public ProductoServiceImpl()throws NamingException{
        administrador= new ProductoABM();
	}	
	
	//@Override
	public Producto buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return administrador.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.eliminar(id);
	}

	//@Override
	public void eliminar(List<Producto> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//productoFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Producto entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		administrador.guardar(entidad);
	}

	//@Override
	public List<Producto> listar(Producto entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return productoFacade.listar_remoto(entidad, orden);
		return administrador.listar();
	}
}
