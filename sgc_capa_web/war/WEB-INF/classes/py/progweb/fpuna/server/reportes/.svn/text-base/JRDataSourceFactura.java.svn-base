package com.fpuna.pweb.server.reportes;

import java.util.Collection;
import java.util.Iterator;

import entidades.FacturaDetalle;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JRDataSourceFactura implements JRDataSource {

	Iterator<FacturaDetalle> it;
	private FacturaDetalle detallefactura;

	public JRDataSourceFactura(Collection<FacturaDetalle> c) {
		it = c.iterator();
	}

	public boolean next() throws JRException {
		boolean ret = false;
		ret = it.hasNext();
		if (ret) {
			detallefactura = it.next();
		}
		return ret;
	}		

	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;		
		if (field.getName().equals("PRODUCTO")) {
			value = detallefactura.getProducto().getDescripcion();
		}		
		if (field.getName().equals("CANTIDAD")) {						
			value = detallefactura.getCantidad();
		}
		if (field.getName().equals("PRECIO_VENTA")) {						
			value = detallefactura.getPrecioVenta();
		}
		return value;
	}

}
