package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.Proveedor;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ProveedorServiceAsync {
	void buscar(Integer entity, AsyncCallback<Proveedor> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Proveedor> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Proveedor entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Proveedor entidad, String orden,AsyncCallback<List<Proveedor>> callback) throws EntidadBaseException;
}
