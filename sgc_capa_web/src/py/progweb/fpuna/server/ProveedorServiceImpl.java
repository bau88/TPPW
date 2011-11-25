package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.ProveedorService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.ProveedorService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Proveedor;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.ProveedorManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProveedorServiceImpl extends RemoteServiceServlet implements
		ProveedorService {
	//@EJB(beanInterface=ProveedorFacadeLocal.class,mappedName="ProveedorFacade/local")
	ProveedorManager proveedorFacade= new ProveedorManager();
	
	//@Override
	public Proveedor buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return proveedorFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		proveedorFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Proveedor> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//proveedorFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Proveedor entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		proveedorFacade.guardar(entidad);
	}

	//@Override
	public List<Proveedor> listar(Proveedor entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return proveedorFacade.listar_remoto(entidad, orden);
		return proveedorFacade.listar();
	}
}
