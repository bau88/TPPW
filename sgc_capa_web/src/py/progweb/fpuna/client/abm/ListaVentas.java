 package py.progweb.fpuna.client.abm;
import java.util.Date;
import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.FacturaService;

import py.progweb.fpuna.client.services.FacturaServiceAsync;

import com.google.gwt.user.client.rpc.ServiceDefTarget;

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
import com.blogspot.tecnologiasjava.model.*;
import py.progweb.fpuna.entidades.*;

public class ListaVentas extends Canvas {
	
	public ListaVentas(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/sgc_capa_web/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid facturaGrid = new ListGrid(); 
        
        facturaGrid.setWidth(500);  
        facturaGrid.setHeight(224);  
        facturaGrid.setShowAllRecords(true);  
        facturaGrid.setAlternateRecordStyles(true);
        facturaGrid.setShowEdges(true);
        facturaGrid.setBorder("0px");
        facturaGrid.setBodyStyleName("normal");
        facturaGrid.setLeaveScrollbarGap(false);
        facturaGrid.setBackgroundColor("#99ffcc");
	    
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
        				
        				Factura factura = new Factura();
        				factura.setId(Integer.parseInt(codigoText.getValue().toString()));
            			listar(facturaGrid, factura, "codigo");	
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
        		Factura factura= new Factura();
            	
    			if(codigoText.getValue() != null)
    				factura.setId(Integer.parseInt(codigoText.getValue().toString()));
    			/*if(nombreusuarioText.getValue() != null)
    				cliente.setNombreusuario(nombreusuarioText.getValue().toString());*/
    			listar(facturaGrid, factura, "nombre");
			}
		});
        
        buscarFields.setFields(codigoText, buscarButton);
        /*--------------------------------------*/
        
        ListGridField codigoField = new ListGridField("codigo", "Codigo");
        ListGridField fechaField = new ListGridField("fecha", "Fecha"); 
        ListGridField numeroField = new ListGridField("numero", "Numero"); 
        ListGridField pendienteField = new ListGridField("pendiente", "Pendiente"); 
        ListGridField saldoField = new ListGridField("saldo", "Saldo"); 
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

        facturaGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 4) {
                	
                	Factura factura= new Factura();             	               	
                	factura.setId(record.getAttributeAsInt("codigo"));
                	factura.setFecha(record.getAttributeAsDate("fecha"));
                	factura.setNumero(Integer.parseInt(record.getAttribute("numero")));
                	factura.setPendiente(record.getAttribute("pendiente"));
                	factura.setSaldo(Double.parseDouble(record.getAttribute("saldo")));
                	
                	if (col == 5) {	/* Editar */
                		
                		new VentaDetalle(factura, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		FacturaServiceAsync service = GWT.create(FacturaService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "facturaService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						@Override
        						public void onSuccess(Void result) {
        							new ListaVentas(mainWindow);
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
        fechaField.setWidth(180);
        numeroField.setWidth(50);
        pendienteField.setWidth(50);
        saldoField.setWidth(50);
        facturaGrid.setFields(codigoField, fechaField, numeroField,pendienteField,saldoField,editarField, borrarField);  
        facturaGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		new VentaDetalle(mainWindow);
			}
		});

        listar(facturaGrid, new Factura(), "nombre");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Ventas</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(facturaGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid facturaGrid,Factura factura, String orden) {
		
    FacturaServiceAsync service = GWT.create(FacturaService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "facturaService");

		try {
			service.listar(factura, orden, new AsyncCallback<List<Factura>>() {
				@Override
				public void onSuccess(List<Factura> result) {
					FacturaRecord [] r = new FacturaRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new FacturaRecord((int)result.get(f).getId(), result.get(f).getFecha(), result.get(f).getNumero(), result.get(f).getPendiente(), result.get(f).getSaldo());
						}
					}
					facturaGrid.setData(r);
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

class FacturaRecord extends ListGridRecord {

	public FacturaRecord() {
	}

	public FacturaRecord(int codigo, Date fecha, Integer numero, String pendiente, Double saldo) {
		setCodigo(codigo);
		setFecha(fecha);
		setNumero(numero);
		setPendiente(pendiente);
		setSaldo(saldo);
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setFecha(Date fecha) {
		setAttribute("fecha", fecha);
	}
	
	public void setNumero(Integer numero) {
		setAttribute("numero",numero);
	}
	
	public void setSaldo(Double saldo) {
		setAttribute("saldo",saldo);
	}
    
	public void setPendiente(String pendiente) {
		setAttribute("pendiente",pendiente);
	}
	
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
