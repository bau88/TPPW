package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.services.CajaService;
import py.progweb.fpuna.client.services.CajaServiceAsync;
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

public class CajaDetalle extends Canvas {

	public CajaDetalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public CajaDetalle(Caja caja, final Sgc_capa_web mainWindow) {
		
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
        label.setContents("<div style='color:black;font-size:15'><b>Detalles de Cajas</b></div>");
                
        TextItem codigoText = new TextItem("codigo");
        codigoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        codigoText.setTitle("Codigo");
        codigoText.setDisabled(true);
        codigoText.setWrapTitle(false);
  
        TextItem nombreText = new TextItem("nombre");
        nombreText.setTitle("Nombre");
        nombreText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        nombreText.setWrapTitle(false);

        ButtonItem button = new ButtonItem("save", "Guardar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		CajaServiceAsync service = GWT.create(CajaService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "cajaService");
    			Caja caja = new Caja();
    										
				caja.setDescripcion(form.getValueAsString("nombre"));
                
				if(form.getValueAsString("codigo") != null){
					caja.setIdcaja(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(caja, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new ListaCajas(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
        
		if (caja != null){
			codigoText.setDefaultValue(String.valueOf(caja.getIdcaja()));
			nombreText.setDefaultValue(caja.getDescripcion());
		}
    
		form.setFields(codigoText, nombreText, button);
		layout.addMember(label);
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
}