package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.Pago;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CobranzaServiceAsync {
	void buscar(Integer entity, AsyncCallback<Pago> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Pago> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Pago entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Pago entidad, String orden,AsyncCallback<List<Pago>> callback) throws EntidadBaseException;
}
