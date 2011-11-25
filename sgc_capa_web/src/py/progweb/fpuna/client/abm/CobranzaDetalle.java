package py.progweb.fpuna.client.abm;

import java.util.Date;

import py.progweb.fpuna.client.services.CobranzaService;
import py.progweb.fpuna.client.services.CobranzaServiceAsync;
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

import py.progweb.fpuna.entidades.Pago;

public class CobranzaDetalle extends Canvas {

	public CobranzaDetalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public CobranzaDetalle(Pago pago, final Sgc_capa_web mainWindow) {
		
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
        label.setContents("<div style='color:black;font-size:15'><b>Detalle de Pago</b></div>");
                
        TextItem codigoText = new TextItem("codigo");
        codigoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        codigoText.setTitle("Codigo");
        codigoText.setDisabled(true);
        codigoText.setWrapTitle(false);
  
        TextItem montoText = new TextItem("monto");
        montoText.setTitle("Monto");
        montoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        montoText.setWrapTitle(false);
        
        final TextItem fechacierreText = new TextItem("fechacierre");
        fechacierreText.setTitle("Fecha de Cierre");
        fechacierreText.setDefaultValue(new Date());
        fechacierreText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        fechacierreText.setWrapTitle(false);
        
        TextItem cerradoText = new TextItem("cerrado");
        cerradoText.setTitle("Cerrado");
        cerradoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        cerradoText.setWrapTitle(false);
        
        

        ButtonItem button = new ButtonItem("save", "Guardar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		CobranzaServiceAsync service = GWT.create(CobranzaService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "cobranzaService"); 
    			Pago pago = new Pago();				
    			pago.setMonto(Integer.parseInt(form.getValueAsString("monto"))); 
    			pago.setFechacierre((Date)fechacierreText.getValue());
    			pago.setCerrado(form.getValueAsString("cerrado")); 
				if(form.getValueAsString("codigo") != null){
					pago.setId(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(pago, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new ListaCobranzas(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
        
		if (pago != null){
			codigoText.setDefaultValue(String.valueOf(pago.getId()));
			montoText.setDefaultValue(pago.getMonto());
			fechacierreText.setDefaultValue(pago.getFechacierre());
			cerradoText.setDefaultValue(pago.getCerrado());
		}
    
		form.setFields(codigoText, montoText, fechacierreText, cerradoText, button);
		layout.addMember(label);
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
}