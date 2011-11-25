 package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.CobranzaService;

import py.progweb.fpuna.client.services.CobranzaServiceAsync;

import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.ListGridField;  
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import py.progweb.fpuna.entidades.Pago;

public class ListaCobranzas extends Canvas {
	
	public ListaCobranzas(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid pagoGrid = new ListGrid(); 
        
        pagoGrid.setWidth(500);  
        pagoGrid.setHeight(224);  
        pagoGrid.setShowAllRecords(true);  
        pagoGrid.setAlternateRecordStyles(true);
        pagoGrid.setShowEdges(true);
        pagoGrid.setBorder("0px");
        pagoGrid.setBodyStyleName("normal");
        pagoGrid.setLeaveScrollbarGap(false);
        pagoGrid.setBackgroundColor("#99ffcc");
	    
        /*-Buscar ------------------------------*/
        DynamicForm buscarFields = new DynamicForm();
        //buscarFields.setBackgroundColor("#99ffcc");
        buscarFields.setItemLayout(FormLayoutType.ABSOLUTE);
        
        final TextItem codigoText = new TextItem("codigo");
        codigoText.setWrapTitle(false);
        codigoText.setLeft(10);
        codigoText.setWidth(43);
        codigoText.addKeyPressHandler(new KeyPressHandler () {
        	public void onKeyPress(KeyPressEvent event) {
        		if ("Enter".equals(event.getKeyName())) {
        			/* buscar por el campo correspondiente */
        			if(codigoText.getValue() != null){
        				Pago pago = new Pago();
        				pago.setId(Integer.parseInt(codigoText.getValue().toString()));
            			listar(pagoGrid, pago, "codigo");	
        			}
        			
        		}
			}
        });
        
        
        
        
        
        ButtonItem buscarButton = new ButtonItem("find", "");
        buscarButton.setIcon("view.png");
        buscarButton.setWidth(50);
        buscarButton.setLeft(443);
        buscarButton.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
    			/* buscar por el campo correspondiente */
        		Pago pago= new Pago();
            		
    			if(codigoText.getValue() != null)
    				pago.setId(Integer.parseInt(codigoText.getValue().toString()));
    			/*if(nombreusuarioText.getValue() != null)
    				cliente.setNombreusuario(nombreusuarioText.getValue().toString());*/
    			listar(pagoGrid, pago, "nombre");
			}
		});
        
        buscarFields.setFields(codigoText, buscarButton);
        /*--------------------------------------*/
        
        ListGridField codigoField = new ListGridField("codigo", "Codigo");
        ListGridField montoField = new ListGridField("monto", "Monto");  
        ListGridField fechacierreField = new ListGridField("fechacierre", "Fecha Cierre"); 
        ListGridField cerradoField = new ListGridField("cerrado", "Cerrado");         
        ListGridField editarField = new ListGridField("edit", "Editar");
        ListGridField borrarField = new ListGridField("remove", "Borrar");
        
        codigoField.setAlign(Alignment.CENTER);
        editarField.setAlign(Alignment.CENTER);
        borrarField.setAlign(Alignment.CENTER);

        editarField.setType(ListGridFieldType.IMAGE);
        borrarField.setType(ListGridFieldType.IMAGE);
        
        editarField.setImageURLPrefix(PATH_IMG); 
        borrarField.setImageURLPrefix(PATH_IMG);
        
        editarField.setImageURLSuffix(".png");
        borrarField.setImageURLSuffix(".png");

        pagoGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 3) {
                	Pago pago = new Pago();
                	pago.setId(record.getAttributeAsInt("codigo"));               
                	pago.setMonto(record.getAttributeAsInt("monto"));
                	pago.setFechacierre(record.getAttributeAsDate("fechacierre"));
                	pago.setCerrado(record.getAttribute("cerrado"));
                	
                	
                	if (col == 4) {	/* Editar */
                		
                		new CobranzaDetalle(pago, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		CobranzaServiceAsync service = GWT.create(CobranzaService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "cobranzaService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						@Override
        						public void onSuccess(Void result) {
        							new ListaCobranzas(mainWindow);
        						}       						
        					});
        				} catch (NumberFormatException e) {
        					e.printStackTrace();
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        				
                	}
                }
			}
		});
                
        codigoField.setWidth(50);
        montoField.setWidth(120);
        fechacierreField.setWidth(60);
        cerradoField.setWidth(60);
        
        pagoGrid.setFields(codigoField, montoField, fechacierreField, cerradoField, editarField, borrarField);  
        pagoGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		new CobranzaDetalle(mainWindow);
			}
		});

        listar(pagoGrid, new Pago(), "nombre");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Pagos</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(pagoGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid pagoGrid, Pago pago, String orden) {
		
    CobranzaServiceAsync service = GWT.create(CobranzaService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "cobranzaService");

		try {
			service.listar(pago, orden, new AsyncCallback<List<Pago>>() {
				@Override
				public void onSuccess(List<Pago> result) {
					PagoRecord [] r = new PagoRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new PagoRecord((int)result.get(f).getId(), result.get(f).getMonto(), result.get(f).getFechacierre(), result.get(f).getCerrado() );
						}
					}
					pagoGrid.setData(r);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 
	}
}

class PagoRecord extends ListGridRecord {

	public PagoRecord() {
	}

	public PagoRecord(int codigo, Integer monto, Date fecha, String cerrado) {
		setCodigo(codigo);
		setMonto(monto);
		setFechacierre(fecha);
		setCerrado(cerrado);
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setMonto(Integer monto) {
		setAttribute("monto", monto);
	}
	
	public void setFechacierre(Date fechacierre) {
		setAttribute("fechacierre", fechacierre);
	}
	
	public void setCerrado(String cerrado) {
		setAttribute("cerrado", cerrado);
	}

	
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
