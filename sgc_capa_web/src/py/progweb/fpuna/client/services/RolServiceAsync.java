package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.Rol;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RolServiceAsync {
	void buscar(Integer entity, AsyncCallback<Rol> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Rol> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Rol entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Rol entidad, String orden,AsyncCallback<List<Rol>> callback) throws EntidadBaseException;
}
