package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.CompraDetalle;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("compradetalleService")
public interface CompraDetalleService extends RemoteService {
	CompraDetalle buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<CompraDetalle> entidad) throws EntidadBaseException;
	void guardar(CompraDetalle entidad) throws EntidadBaseException;
	List<CompraDetalle> listar(CompraDetalle entidad, String orden) throws EntidadBaseException;
}
