package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.blogspot.tecnologiasjava.model.FacturaDetalle;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("facturadetalleService")
public interface FacturaDetalleService extends RemoteService {
	FacturaDetalle buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<FacturaDetalle> entidad) throws EntidadBaseException;
	void guardar(FacturaDetalle entidad) throws EntidadBaseException;
	List<FacturaDetalle> listar(FacturaDetalle entidad, String orden) throws EntidadBaseException;
}
