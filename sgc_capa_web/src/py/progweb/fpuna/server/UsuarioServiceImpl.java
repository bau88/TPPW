package py.progweb.fpuna.server;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import py.progweb.fpuna.client.services.UsuarioService;
//import py.progweb.fpuna.client.GreetingService;
import py.progweb.fpuna.client.services.UsuarioService;
//import py.progweb.fpuna.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import py.progweb.fpuna.entidades.Usuario;
import py.progweb.fpuna.excepciones.EntidadBaseException;
import py.progweb.fpuna.facades.UsuarioManager;
//import py.progweb.fpuna.facades.RolFacadeLocal;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UsuarioServiceImpl extends RemoteServiceServlet implements
		UsuarioService {
	//@EJB(beanInterface=UsuarioFacadeLocal.class,mappedName="UsuarioFacade/local")
	UsuarioManager usuarioFacade= new UsuarioManager();
	
	//@Override
	public Usuario buscar(Integer entity) throws EntidadBaseException {
		// TODO Auto-generated method stub
		return usuarioFacade.buscar(entity);
	}

	//@Override
	public void eliminar(Integer id) throws EntidadBaseException {
		// TODO Auto-generated method stub
		usuarioFacade.eliminar(id);
	}

	//@Override
	public void eliminar(List<Usuario> entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//usuarioFacade.eliminar(entidad);	
	}

	//@Override
	public void guardar(Usuario entidad) throws EntidadBaseException {
		// TODO Auto-generated method stub
		usuarioFacade.guardar(entidad);
	}

	//@Override
	public List<Usuario> listar(Usuario entidad, String orden) throws EntidadBaseException {
		// TODO Auto-generated method stub
		//return usuarioFacade.listar_remoto(entidad, orden);
		return usuarioFacade.listar();
	}
}
