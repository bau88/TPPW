package py.progweb.fpuna.entidades;

import java.io.Serializable;

public abstract class EntidadBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Object getPK();
}