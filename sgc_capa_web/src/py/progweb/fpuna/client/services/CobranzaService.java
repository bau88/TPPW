package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Pago;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cobranzaService")
public interface CobranzaService extends RemoteService {
	Pago buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Pago> entidad) throws EntidadBaseException;
	void guardar(Pago entidad) throws EntidadBaseException;
	List<Pago> listar(Pago entidad, String orden) throws EntidadBaseException;
}
