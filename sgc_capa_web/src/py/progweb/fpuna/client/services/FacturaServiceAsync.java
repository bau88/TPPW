package py.progweb.fpuna.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.blogspot.tecnologiasjava.model.Factura;
import com.blogspot.tecnologiasjava.model.FacturaDetalle;

import py.progweb.fpuna.excepciones.EntidadBaseException;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface FacturaServiceAsync {
	void buscar(Integer entity, AsyncCallback<Factura> callbak ) throws EntidadBaseException; 
	void eliminar(Integer id,AsyncCallback<Void> callback) throws EntidadBaseException;
	void eliminar(List<Factura> entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void guardar(Factura entidad,AsyncCallback<Void> callback) throws EntidadBaseException;
	void listar(Factura entidad, String orden,AsyncCallback<List<Factura>> callback) throws EntidadBaseException;
    void facturar(Factura entidad, AsyncCallback<Integer> callback ) throws EntidadBaseException;
    void guardarFacturaConDetalles(Factura entidad, AsyncCallback<Integer> callback)throws EntidadBaseException;
}
