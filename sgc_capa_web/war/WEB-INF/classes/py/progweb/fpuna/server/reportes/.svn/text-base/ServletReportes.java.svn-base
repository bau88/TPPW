package com.fpuna.pweb.server.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import entidades.Caja;
import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaDetalle;
import entidades.Pago;

import facade.CajaFacadeLocal;
import facade.ClienteFacadeLocal;
import facade.FacturaFacadeLocal;
import facade.PagoFacadeLocal;

/**
 * Servlet implementation class pruebaEJB
 */
public class ServletReportes extends HttpServlet {
	@EJB(mappedName="FacturaFacade/local")
	FacturaFacadeLocal facturaFacade;
	
	@EJB(mappedName="PagoFacade/local")
	PagoFacadeLocal pagoFacade;
	
	@EJB(mappedName="CajaFacade/local")
	CajaFacadeLocal cajaFacade;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	private void procesar(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
			String tipo_reporte = request.getParameter("reporte");
			tipo_reporte = tipo_reporte.toLowerCase();
			byte[] pdf_generado = null;
			if("factura".compareTo(tipo_reporte)==0){
				String numero_factura = request.getParameter("numero_factura");
				pdf_generado = pdf_reporte_factura(numero_factura);
			}else{
				if("cierrecaja".compareTo(tipo_reporte)==0){
					
					
					Integer caja_id = Integer.valueOf(request.getParameter("numero_caja"));					
					Caja caja_cerrar = cajaFacade.buscar(caja_id);
					pdf_generado = pdf_cierre_caja(caja_cerrar);	
										
								
				}
			}
			
			HttpServletResponse res =response;
			res.setContentType("application/pdf");
			res.setHeader("Content-disposition", "inline; filename="
					+ "temporal.pdf");		
			try {
				ServletOutputStream out = res.getOutputStream();
				out.write(pdf_generado);
				out.flush();
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			response.setContentType("text/html");
			
			
			
		
	}

	private byte[] pdf_cierre_caja(Caja pcaja) {
		// TODO Auto-generated method stub
		try {			
			//Pago pago_buscar = new Pago();
			//pago_buscar.setCaja(pcaja);			
			
			List<Pago> pago_imprimir = pagoFacade.CierreCaja(pcaja);			
			Date fechaCierre = new Date();
			
			if(pago_imprimir != null){
				
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("FECHA", fechaCierre);
					
					InputStream camino = getClass().getResourceAsStream("/reportes/Reporte_cierrecaja.jasper"); 
					JasperReport reportPrincipal = (JasperReport) JRLoader.loadObject(camino);
					
					JasperPrint print = new JasperPrint();
					
					List<Pago> detalle_impresion = new ArrayList<Pago>();					
					for(Pago pago_detalle:pago_imprimir){
						detalle_impresion.add(pago_detalle);									
					}
					
					print = JasperFillManager.fillReport(reportPrincipal, param, new JRDataSourcePago(detalle_impresion));
					
					//JasperPrintManager.printReport(print,true ); descomentar para imprimir directo true = seleccionar impresora, false = impresora por defecto					
					
					return JasperExportManager.exportReportToPdf(print);
			}
			
			return null;
		}		 
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}

	private byte[] pdf_reporte_factura(String numeroFactura) {
		// TODO Auto-generated method stub
		try {			
			Factura factura_buscar = new Factura();
			factura_buscar.setIdFactura(Integer.valueOf(numeroFactura));
			Factura factura_imprimir = facturaFacade.buscar_con_detalle(Integer.valueOf(numeroFactura));
			if(factura_imprimir != null){
					  
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("CLIENTE", factura_imprimir.getCliente().getNombre());
					param.put("NUMERO_FACTURA", factura_imprimir.getNumero());
					param.put("MONTO_TOTAL",factura_imprimir.getMontoTotal());
					param.put("FECHA", factura_imprimir.getFecha());	 
					
					InputStream camino = getClass().getResourceAsStream("/reportes/Reporte_factura.jasper"); 
					JasperReport reportPrincipal = (JasperReport) JRLoader.loadObject(camino);
					
					JasperPrint print = new JasperPrint();
					
					List<FacturaDetalle> detalle_impresion = new ArrayList<FacturaDetalle>();					
					for(FacturaDetalle detalle:factura_imprimir.getFacturaDetalles()){
						detalle_impresion.add(detalle);
					}
					
					print = JasperFillManager.fillReport(reportPrincipal, param, new JRDataSourceFactura(detalle_impresion));
					
					//JasperPrintManager.printReport(print,true ); descomentar para imprimir directo true = seleccionar impresora, false = impresora por defecto					
					
					return JasperExportManager.exportReportToPdf(print);
			}	
			return null;
		}		 
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}

}
