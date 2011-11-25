package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import py.progweb.fpuna.entidades.Usuario;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UsuarioServiceAsync {
	void buscar(Integer entity, AsyncCallback<Usuario> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Usuario> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Usuario entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Usuario entidad, String orden,AsyncCallback<List<Usuario>> callback) throws EntidadBaseException;
	
}
