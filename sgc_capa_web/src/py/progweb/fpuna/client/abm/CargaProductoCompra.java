package py.progweb.fpuna.client.abm;
import java.util.Date;
import py.progweb.fpuna.client.services.CompraDetalleService;
import py.progweb.fpuna.client.services.CompraDetalleServiceAsync;
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
import com.blogspot.tecnologiasjava.model.*;

public class CargaProductoCompra extends Canvas {

	public CargaProductoCompra(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public CargaProductoCompra(CompraDetalle compradetalle, final Sgc_capa_web mainWindow) {
		
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
        label.setContents("<div style='color:black;font-size:15'><b>Carga de Productos de la Compra</b></div>");
                
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
        
        final TextItem preciocompraText = new TextItem("preciocompra");
        preciocompraText.setTitle("Precio");
        preciocompraText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        preciocompraText.setWrapTitle(false);

        ButtonItem button = new ButtonItem("save", "Aceptar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		CompraDetalleServiceAsync service = GWT.create(CompraDetalleService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "compradetalleService");
    			CompraDetalle compradetalle = new CompraDetalle();    			
    			compradetalle.setId_producto(Integer.parseInt(form.getValueAsString("producto")));  			
    			compradetalle.setCantidad(Integer.parseInt(form.getValueAsString("cantidad")));
    			compradetalle.setPrecioCompra(Double.parseDouble(form.getValueAsString("preciocompra")));
				//compra.setTotalCompra(Integer.parseInt("15000"));
				if(form.getValueAsString("codigo") != null){
					compradetalle.setIdCompDet(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(compradetalle, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new CargaProductoCompra(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
        
		if (compradetalle!= null){
			codigoText.setDefaultValue(String.valueOf(compradetalle.getIdCompDet()));
			productoText.setDefaultValue(compradetalle.getId_producto());
			cantidadText.setDefaultValue(compradetalle.getCantidad());
			preciocompraText.setDefaultValue(((Object)compradetalle.getPrecioCompra()).toString());
		}
    
		form.setFields(codigoText, productoText, cantidadText, preciocompraText,button);
		layout.addMember(label);
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
}