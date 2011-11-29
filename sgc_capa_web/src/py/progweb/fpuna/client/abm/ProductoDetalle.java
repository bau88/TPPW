package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.services.ProductoService;
import py.progweb.fpuna.client.services.ProductoServiceAsync;
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

import com.blogspot.tecnologiasjava.model.*;

public class ProductoDetalle extends Canvas {

	public ProductoDetalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public ProductoDetalle(Producto producto, final Sgc_capa_web mainWindow) {
		
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
        label.setContents("<div style='color:black;font-size:15'><b>Detalles del Producto</b></div>");
        
        
        TextItem codigoText = new TextItem("codigo");
        codigoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        codigoText.setTitle("Codigo");
        codigoText.setDisabled(true);
        codigoText.setWrapTitle(false);
  
        TextItem descripcionText = new TextItem("descripcion");
        descripcionText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        descripcionText.setTitle("Descripcion");
        descripcionText.setWrapTitle(false);
        
        TextItem precioText = new TextItem("precio");
        precioText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        precioText.setTitle("Precio");
        precioText.setWrapTitle(false);
        
        TextItem cantidadText = new TextItem("cantidad");
        cantidadText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        cantidadText.setTitle("Cantidad");
        cantidadText.setWrapTitle(false);
        
        TextItem porcgananciaText = new TextItem("porcganancia");
        porcgananciaText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        porcgananciaText.setTitle("Porcentaje ganancia");
        porcgananciaText.setWrapTitle(false);

        ButtonItem button = new ButtonItem("save", "Guardar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		ProductoServiceAsync service = GWT.create(ProductoService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "productoService"); 
    			Producto producto = new Producto();
				
				producto.setDescripcion(form.getValueAsString("descripcion"));
				producto.setPrecio(Double.parseDouble(form.getValueAsString("precio")));
				producto.setCantidad(Integer.parseInt(form.getValueAsString("cantidad")));
				producto.setPorcganancia(Double.parseDouble(form.getValueAsString("porcganancia")));
				if(form.getValueAsString("codigo") != null){
					producto.setId(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(producto, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new ListaProductos(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
        
		if (producto != null){
			codigoText.setDefaultValue(String.valueOf(producto.getId()));
			descripcionText.setDefaultValue(producto.getDescripcion());
			precioText.setDefaultValue(producto.getPrecio().toString());
			cantidadText.setDefaultValue(producto.getCantidad());
			porcgananciaText.setDefaultValue(producto.getPorcganancia().toString());
		}
    
		form.setFields(codigoText, descripcionText, precioText, cantidadText, porcgananciaText,  button);
		layout.addMember(label);
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
}