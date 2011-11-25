package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Factura;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("facturaService")
public interface FacturaService extends RemoteService {
	Factura buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Factura> entidad) throws EntidadBaseException;
	void guardar(Factura entidad) throws EntidadBaseException;
	List<Factura> listar(Factura entidad, String orden) throws EntidadBaseException;
}
