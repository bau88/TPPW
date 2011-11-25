 package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.CajaService;

import py.progweb.fpuna.client.services.CajaServiceAsync;

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

import py.progweb.fpuna.entidades.*;

public class ListaCajas extends Canvas {
	
	public ListaCajas(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid cajaGrid = new ListGrid(); 
        
        cajaGrid.setWidth(500);  
        cajaGrid.setHeight(224);  
        cajaGrid.setShowAllRecords(true);  
        cajaGrid.setAlternateRecordStyles(true);
        cajaGrid.setShowEdges(true);
        cajaGrid.setBorder("0px");
        cajaGrid.setBodyStyleName("normal");
        cajaGrid.setLeaveScrollbarGap(false);
        cajaGrid.setBackgroundColor("#99ffcc");
	    
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
        				Caja caja = new Caja();
        				caja.setIdcaja(Integer.parseInt(codigoText.getValue().toString()));
            			listar(cajaGrid, caja, "codigo");	
        			}
        			
        		}
			}
        });
        
        final TextItem nombreText = new TextItem("nombre");
        nombreText.setWrapTitle(false);
        nombreText.setLeft(55);
        nombreText.setWidth(180);
        nombreText.addKeyPressHandler(new KeyPressHandler () {
        	public void onKeyPress(KeyPressEvent event) {
        		if ("Enter".equals(event.getKeyName())) {
        			/* buscar por el campo correspondiente */
        			//Usuario usuario = null;
        			if(nombreText.getValue() != null){
        				Caja caja = new Caja();
        				caja.setDescripcion(nombreText.getValue().toString());
            			listar(cajaGrid, caja, "nombre");	
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
        		
            	Caja caja = new Caja();    			
    			if(nombreText.getValue() != null)
    				caja.setDescripcion(nombreText.getValue().toString());
    			/*if(nombreusuarioText.getValue() != null)
    				cliente.setNombreusuario(nombreusuarioText.getValue().toString());*/
    			listar(cajaGrid, caja, "nombre");
			}
		});
        
        buscarFields.setFields(codigoText, nombreText, buscarButton);
        /*--------------------------------------*/
        
        ListGridField codigoField = new ListGridField("codigo", "Codigo");
        ListGridField nombreField = new ListGridField("nombre", "Nombre");        
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

        cajaGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 1) {
                	
                	Caja caja = new Caja();               	
                	caja.setIdcaja(record.getAttributeAsInt("codigo"));
                	caja.setDescripcion(record.getAttribute("nombre"));
                	
                	
                	if (col == 2) {	/* Editar */
                		
                		new CajaDetalle(caja, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		CajaServiceAsync service = GWT.create(CajaService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "cajaService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						@Override
        						public void onSuccess(Void result) {
        							new ListaCajas(mainWindow);
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
        nombreField.setWidth(180);
        
        cajaGrid.setFields(codigoField, nombreField, editarField, borrarField);  
        cajaGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		new CajaDetalle(mainWindow);
			}
		});

        listar(cajaGrid, new Caja(), "nombre");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Cajas</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(cajaGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid cajaGrid, Caja caja, String orden) {
		
    CajaServiceAsync service = GWT.create(CajaService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "cajaService");

		try {
			service.listar(caja, orden, new AsyncCallback<List<Caja>>() {
				@Override
				public void onSuccess(List<Caja> result) {
					CajaRecord [] r = new CajaRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new CajaRecord((int)result.get(f).getIdcaja(), result.get(f).getDescripcion());
						}
					}
					cajaGrid.setData(r);
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

class CajaRecord extends ListGridRecord {

	public CajaRecord() {
	}

	public CajaRecord(int codigo, String nombre) {
		setCodigo(codigo);
		setNombre(nombre);
		
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setNombre(String nombre) {
		setAttribute("nombre", nombre);
	}

	
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
