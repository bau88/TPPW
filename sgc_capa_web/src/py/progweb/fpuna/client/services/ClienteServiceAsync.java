package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.Cliente;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ClienteServiceAsync {
	void buscar(Integer entity, AsyncCallback<Cliente> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Cliente> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Cliente entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Cliente entidad, String orden,AsyncCallback<List<Cliente>> callback) throws EntidadBaseException;
}
