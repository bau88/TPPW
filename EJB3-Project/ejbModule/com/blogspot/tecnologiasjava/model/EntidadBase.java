package com.blogspot.tecnologiasjava.model;

import java.io.Serializable;

public class EntidadBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getPK(){
		throw new RuntimeException("Error en la obtencion de PK");
	}
}