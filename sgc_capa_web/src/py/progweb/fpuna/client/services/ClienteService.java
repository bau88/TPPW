package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.blogspot.tecnologiasjava.model.Cliente;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("clienteService")
public interface ClienteService extends RemoteService {
	Cliente buscar(Integer entity) throws EntidadBaseException; 
	void eliminar(Integer id) throws EntidadBaseException;
	void eliminar(List<Cliente> entidad) throws EntidadBaseException;
	void guardar(Cliente entidad) throws EntidadBaseException;
	List<Cliente> listar(Cliente entidad, String orden) throws EntidadBaseException;
}
