package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.Producto;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ProductoServiceAsync {
	void buscar(Integer entity, AsyncCallback<Producto> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Producto> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Producto entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Producto entidad, String orden,AsyncCallback<List<Producto>> callback) throws EntidadBaseException;
}
