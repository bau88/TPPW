package py.progweb.fpuna.client.abm;
import java.util.Date;
import py.progweb.fpuna.client.services.FacturaDetalleService;
import py.progweb.fpuna.client.services.FacturaDetalleServiceAsync;
import py.progweb.fpuna.client.Sgc_capa_web;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;   
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;   
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;   
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;   
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import py.progweb.fpuna.entidades.*;

public class CargaProductoFactura extends Canvas {

	public CargaProductoFactura(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public CargaProductoFactura(FacturaDetalle facturadetalle, final Sgc_capa_web mainWindow) {
		
		VLayout layout = new VLayout(10);
		
        final DynamicForm form = new DynamicForm();
        form.setBackgroundColor("#006633");
        form.setBorder("2px");
        form.setAutoFocus(true);
        form.setNumCols(3);
        form.setWidth(500);

        Label label = new Label();
        label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true); 
        label.setContents("<div style='color:black;font-size:15'><b>Carga de Productos para la Factura</b></div>");
                
        TextItem codigoText = new TextItem("codigo");
        codigoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        codigoText.setTitle("Codigo");
        codigoText.setDisabled(true);
        codigoText.setWrapTitle(false);
  
        final TextItem productoText = new TextItem("producto");
        productoText.setTitle("Producto");
        productoText.setDefaultValue(new Date());
        productoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        productoText.setWrapTitle(false);
        
        final TextItem cantidadText = new TextItem("cantidad");
        cantidadText.setTitle("Cantidad");
        cantidadText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        cantidadText.setWrapTitle(false);
        
        final TextItem precioventaText = new TextItem("precioventa");
        precioventaText.setTitle("Precio");
        precioventaText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        precioventaText.setWrapTitle(false);

        ButtonItem button = new ButtonItem("save", "Aceptar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		FacturaDetalleServiceAsync service = GWT.create(FacturaDetalleService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "facturadetalleService");
    			FacturaDetalle facturadetalle = new FacturaDetalle();    			
    			facturadetalle.setId_producto(Integer.parseInt(form.getValueAsString("producto")));  			
				facturadetalle.setCantidad(Integer.parseInt(form.getValueAsString("cantidad")));
				facturadetalle.setPrecioVenta(Double.parseDouble(form.getValueAsString("precioventa")));
				//compra.setTotalCompra(Integer.parseInt("15000"));
				if(form.getValueAsString("codigo") != null){
					facturadetalle.setIdFactDet(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(facturadetalle, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new CargaProductoFactura(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
        
		if (facturadetalle!= null){
			codigoText.setDefaultValue(String.valueOf(facturadetalle.getIdFactDet()));
			productoText.setDefaultValue(facturadetalle.getId_producto());
			cantidadText.setDefaultValue(facturadetalle.getCantidad());
			precioventaText.setDefaultValue(((Object)facturadetalle.getPrecioVenta()).toString());
		}
    
		form.setFields(codigoText, productoText, cantidadText, precioventaText,button);
		layout.addMember(label);
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
}