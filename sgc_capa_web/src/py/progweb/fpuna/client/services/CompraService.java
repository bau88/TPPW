package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Compra;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("compraService")
public interface CompraService extends RemoteService {
	Compra buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Compra> entidad) throws EntidadBaseException;
	void guardar(Compra entidad) throws EntidadBaseException;
	List<Compra> listar(Compra entidad, String orden) throws EntidadBaseException;
}
