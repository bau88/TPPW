package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Rol;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("rolService")
public interface RolService extends RemoteService{
	Rol buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Rol> entidad) throws EntidadBaseException;
	void guardar(Rol entidad) throws EntidadBaseException;
	List<Rol> listar(Rol entidad, String orden) throws EntidadBaseException;
}
