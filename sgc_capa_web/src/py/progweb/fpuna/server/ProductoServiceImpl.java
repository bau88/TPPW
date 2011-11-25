package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.ProductoService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.ProductoService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Producto;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.ProductoManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProductoServiceImpl extends RemoteServiceServlet implements
		ProductoService {
	//@EJB(beanInterface=ProductoFacadeLocal.class,mappedName="ProductoFacade/local")
	ProductoManager productoFacade= new ProductoManager();
	
	//@Override
	public Producto buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return productoFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		productoFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Producto> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//productoFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Producto entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		productoFacade.guardar(entidad);
	}

	//@Override
	public List<Producto> listar(Producto entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return productoFacade.listar_remoto(entidad, orden);
		return productoFacade.listar();
	}
}
