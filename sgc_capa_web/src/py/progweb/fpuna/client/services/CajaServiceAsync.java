package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import py.progweb.fpuna.entidades.Caja;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CajaServiceAsync {
	void buscar(Integer entity, AsyncCallback<Caja> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Caja> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Caja entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Caja entidad, String orden,AsyncCallback<List<Caja>> callback) throws EntidadBaseException;
}
