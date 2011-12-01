 package py.progweb.fpuna.client.abm;
import java.util.Date;
import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.CompraService;

import py.progweb.fpuna.client.services.CompraServiceAsync;

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

public class ListaCompras extends Canvas {
	
	public ListaCompras(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/sgc_capa_web/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid compraGrid = new ListGrid(); 
        
        compraGrid.setWidth(500);  
        compraGrid.setHeight(224);  
        compraGrid.setShowAllRecords(true);  
        compraGrid.setAlternateRecordStyles(true);
        compraGrid.setShowEdges(true);
        compraGrid.setBorder("0px");
        compraGrid.setBodyStyleName("normal");
        compraGrid.setLeaveScrollbarGap(false);
        compraGrid.setBackgroundColor("#99ffcc");
	    
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
        				Compra compra = new Compra();
        				
        				compra.setId(Integer.parseInt(codigoText.getValue().toString()));
            			listar(compraGrid, compra, "codigo");	
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
        		
            	Compra compra = new Compra();    			
    			if(codigoText.getValue() != null)
    				compra.setId(Integer.parseInt(codigoText.getValue().toString()));
    			/*if(nombreusuarioText.getValue() != null)
    				cliente.setNombreusuario(nombreusuarioText.getValue().toString());*/
    			listar(compraGrid, compra, "nombre");
			}
		});
        
        buscarFields.setFields(codigoText, buscarButton);
        /*--------------------------------------*/
        
        ListGridField codigoField = new ListGridField("codigo", "Codigo");
        ListGridField fechaField = new ListGridField("fecha", "Fecha"); 
        ListGridField totalField = new ListGridField("total", "Total"); 
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

        compraGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 2) {
                	Compra compra = new Compra();
                	             	               	
                	compra.setId(record.getAttributeAsInt("codigo"));
                	compra.setFecha(record.getAttributeAsDate("fecha"));
                	System.out.println("Esto imprime "+record.getAttribute("total"));
                	compra.setTotalCompra(Integer.parseInt(record.getAttribute("total")));
                	
                	if (col == 3) {	/* Editar */
                		
                		new Compra_Detalle(compra, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		CompraServiceAsync service = GWT.create(CompraService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "compraService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						@Override
        						public void onSuccess(Void result) {
        							new ListaCompras(mainWindow);
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
        totalField.setWidth(80);
        compraGrid.setFields(codigoField, fechaField, totalField,editarField, borrarField);  
        compraGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		new Compra_Detalle(mainWindow);
			}
		});

        listar(compraGrid, new Compra(), "nombre");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Compras</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(compraGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid compraGrid,Compra compra, String orden) {
		
    CompraServiceAsync service = GWT.create(CompraService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "compraService");

		try {
			service.listar(compra, orden, new AsyncCallback<List<Compra>>() {
				@Override
				public void onSuccess(List<Compra> result) {
					CompraRecord [] r = new CompraRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new CompraRecord((int)result.get(f).getId(), result.get(f).getFecha(), result.get(f).getTotalCompra());
						}
					}
					compraGrid.setData(r);
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

class CompraRecord extends ListGridRecord {

	public CompraRecord() {
	}

	public CompraRecord(int codigo, Date fecha, Integer total) {
		setCodigo(codigo);
		setFecha(fecha);
		setTotal(total);
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setFecha(Date fecha) {
		setAttribute("fecha", fecha);
	}
	
	public void setTotal(Integer total) {
		setAttribute("total",total);
	}

	
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
