package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Producto;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("productoService")
public interface ProductoService extends RemoteService {
	Producto buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Producto> entidad) throws EntidadBaseException;
	void guardar(Producto entidad) throws EntidadBaseException;
	List<Producto> listar(Producto entidad, String orden) throws EntidadBaseException;
}
