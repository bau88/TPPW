package com.fpuna.pweb.server.reportes;

import java.util.Collection;
import java.util.Iterator;

import entidades.Pago;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JRDataSourcePago implements JRDataSource {

	Iterator<Pago> it;
	private Pago pagos;

	public JRDataSourcePago(Collection<Pago> c) {
		it = c.iterator();
	}

	public boolean next() throws JRException {
		boolean ret = false;
		ret = it.hasNext();
		if (ret) {
			pagos = it.next();
		}
		return ret;
	}		

	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;		
		if (field.getName().equals("FACTURA")) {
			value = pagos.getFactura().getNumero();
		}		
		if (field.getName().equals("CAJA")) {						
			value = pagos.getCaja().getIdCaja();
		}
		if (field.getName().equals("MONTO")) {						
			value = pagos.getMonto();
		}
		return value;
	}

}
