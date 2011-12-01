package com.blogspot.tecnologiasjava.test;
import com.blogspot.tecnologiasjava.manager.*;
import com.blogspot.tecnologiasjava.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
public class FacturaABM {
	FacturaManagerRemote facturaFacade;
	FacturaDetalleManagerRemote facturaDetalleFacade;
	public FacturaABM() throws NamingException{
		Properties properties = new Properties();        
		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");       
		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");      
		Context ctx = new InitialContext(properties);   		
		facturaFacade = null; 
		facturaDetalleFacade = null;
		try {          
			facturaFacade= (FacturaManagerRemote) ctx.lookup("FacturaManager/remote");  
			facturaDetalleFacade = (FacturaDetalleManagerRemote) ctx.lookup("FacturaDetalleManager/remote");  
		} catch (NamingException e) {           
				e.printStackTrace();       
		}   
		
	}
	
	public List<Factura> getFacturas() {
		//alumnos = null;
		List<Factura> todos_facturas= new ArrayList<Factura>();
		todos_facturas=facturaFacade.listar();
		return todos_facturas;
	}
	
	
	
	public Factura getFactura(int cedula) {
		List<Factura> facturas= new ArrayList<Factura>();
		facturas=facturaFacade.listar();		
		for(Factura factura : facturas) {
			if (factura.getPK().equals(cedula))
				return factura;
		}
		
		return null;
	}
	
    public Factura buscar(Integer cedula){
		return getFactura(cedula);
	}

	public void eliminar(Integer cedula){
		facturaFacade.eliminar(cedula.intValue());
	}

	public void guardar(Factura factura){
		facturaFacade.guardar(factura);
	}

	public List<Factura> listar(){
		List<Factura> todos_facturas= new ArrayList<Factura>();
		todos_facturas=facturaFacade.listar();
		return todos_facturas;

	}
	public int facturar(Factura factura){
		return facturaFacade.facturar(factura);
	}
	
	public int guardarFacturaConDetalles(Factura factura){
		if (factura.getCliente() != null)
			factura.setCliente(null);
		if (factura.getPK() == null)
			facturaFacade.guardar(factura);	
		List<FacturaDetalle> detalle=factura.getFacturaDetalles();		
		for (FacturaDetalle det: detalle){
			facturaDetalleFacade.guardar(det);
		}		
		
		return facturar(factura);
	}

}
