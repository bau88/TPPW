 package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.ClienteService;

import py.progweb.fpuna.client.services.ClienteServiceAsync;

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

import py.progweb.fpuna.entidades.Cliente;

public class ListaClientes extends Canvas {
	
	public ListaClientes(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid clienteGrid = new ListGrid(); 
        
        clienteGrid.setWidth(500);  
        clienteGrid.setHeight(224);  
        clienteGrid.setShowAllRecords(true);  
        clienteGrid.setAlternateRecordStyles(true);
        clienteGrid.setShowEdges(true);
        clienteGrid.setBorder("0px");
        clienteGrid.setBodyStyleName("normal");
        clienteGrid.setLeaveScrollbarGap(false);
        clienteGrid.setBackgroundColor("#99ffcc");
	    
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
        				Cliente cliente = new Cliente();
        				cliente.setId(Integer.parseInt(codigoText.getValue().toString()));
            			listar(clienteGrid, cliente, "codigo");	
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
        				Cliente cliente = new Cliente();
        				cliente.setNombre(nombreText.getValue().toString());
            			listar(clienteGrid, cliente, "nombre");	
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
            	Cliente cliente= new Cliente();    			
    			if(nombreText.getValue() != null)
    				cliente.setNombre(nombreText.getValue().toString());
    			/*if(nombreusuarioText.getValue() != null)
    				cliente.setNombreusuario(nombreusuarioText.getValue().toString());*/
    			listar(clienteGrid, cliente, "nombre");
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

        clienteGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 1) {
                	Cliente cliente = new Cliente();
                	
                	cliente.setId(record.getAttributeAsInt("codigo"));
                	cliente.setNombre(record.getAttribute("nombre"));
                	
                	
                	if (col == 2) {	/* Editar */
                		
                		new ClienteDetalle(cliente, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		ClienteServiceAsync service = GWT.create(ClienteService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "clienteService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						@Override
        						public void onSuccess(Void result) {
        							new ListaClientes(mainWindow);
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
        
        clienteGrid.setFields(codigoField, nombreField, editarField, borrarField);  
        clienteGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		new ClienteDetalle(mainWindow);
			}
		});

        listar(clienteGrid, new Cliente(), "nombre");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Clientes</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(clienteGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid clienteGrid, Cliente cliente, String orden) {
		
    ClienteServiceAsync service = GWT.create(ClienteService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "clienteService");

		try {
			service.listar(cliente, orden, new AsyncCallback<List<Cliente>>() {
				@Override
				public void onSuccess(List<Cliente> result) {
					ClienteRecord [] r = new ClienteRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new ClienteRecord((int)result.get(f).getId(), result.get(f).getNombre());
						}
					}
					clienteGrid.setData(r);
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

class ClienteRecord extends ListGridRecord {

	public ClienteRecord() {
	}

	public ClienteRecord(int codigo, String nombre) {
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
