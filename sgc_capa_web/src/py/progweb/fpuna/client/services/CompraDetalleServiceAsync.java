package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.CompraDetalle;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CompraDetalleServiceAsync {
	void buscar(Integer entity, AsyncCallback<CompraDetalle> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<CompraDetalle> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(CompraDetalle entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(CompraDetalle entidad, String orden,AsyncCallback<List<CompraDetalle>> callback) throws EntidadBaseException;
}
