package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import py.progweb.fpuna.entidades.FacturaDetalle;
import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface FacturaDetalleServiceAsync {
	void buscar(Integer entity, AsyncCallback<FacturaDetalle> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<FacturaDetalle> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(FacturaDetalle entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(FacturaDetalle entidad, String orden,AsyncCallback<List<FacturaDetalle>> callback) throws EntidadBaseException;
}
