package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import py.progweb.fpuna.entidades.Compra;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CompraServiceAsync {
	void buscar(Integer entity, AsyncCallback<Compra> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Compra> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Compra entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Compra entidad, String orden,AsyncCallback<List<Compra>> callback) throws EntidadBaseException;
}
