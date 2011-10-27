package excepciones;

import java.io.Serializable;

import javax.ejb.ApplicationException;


/**
 * Excepcion General de todas las entidades
 */

@ApplicationException(rollback=true)
public class EntidadBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String error;
	public EntidadBaseException(){}
	public EntidadBaseException(String error) {
		super();
		this.error = error;
	}

	public String getMensajeError() {
		return error;
	}

	public void setMensajeError(String error) {
		this.error = error;
	}
	
}
