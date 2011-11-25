package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Proveedor;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proveedorService")
public interface ProveedorService extends RemoteService {
	Proveedor buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Proveedor> entidad) throws EntidadBaseException;
	void guardar(Proveedor entidad) throws EntidadBaseException;
	List<Proveedor> listar(Proveedor entidad, String orden) throws EntidadBaseException;
}
