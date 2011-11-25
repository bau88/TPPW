package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import py.progweb.fpuna.entidades.Caja;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cajaService")
public interface CajaService extends RemoteService {
	Caja buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Caja> entidad) throws EntidadBaseException;
	void guardar(Caja entidad) throws EntidadBaseException;
	List<Caja> listar(Caja entidad, String orden) throws EntidadBaseException;
}
