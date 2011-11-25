 package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.UsuarioService;

import py.progweb.fpuna.client.services.UsuarioServiceAsync;

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

import py.progweb.fpuna.entidades.Usuario;

public class ListaUsuarios extends Canvas {
	
	public ListaUsuarios(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid usuarioGrid = new ListGrid(); 
        
        usuarioGrid.setWidth(500);  
        usuarioGrid.setHeight(224);  
        usuarioGrid.setShowAllRecords(true);  
        usuarioGrid.setAlternateRecordStyles(true);
        usuarioGrid.setShowEdges(true);
        usuarioGrid.setBorder("0px");
        usuarioGrid.setBodyStyleName("normal");
        usuarioGrid.setLeaveScrollbarGap(false);
        usuarioGrid.setBackgroundColor("#99ffcc");
	    
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
        				Usuario usuario = new Usuario();
        				usuario.setIdusuario(Integer.parseInt(codigoText.getValue().toString()));
            			listar(usuarioGrid, usuario, "codigo");	
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
        				Usuario usuario = new Usuario();
        				usuario.setNombre(nombreText.getValue().toString());
            			listar(usuarioGrid, usuario, "nombre");	
        			}
        			
        		}
			}
        });
        
        final TextItem nombreusuarioText = new TextItem("nombreusuario");
        nombreusuarioText.setWrapTitle(false);
        nombreusuarioText.setLeft(237);
        nombreusuarioText.setWidth(150);
        nombreusuarioText.addKeyPressHandler(new KeyPressHandler () {
        	public void onKeyPress(KeyPressEvent event) {
        		if ("Enter".equals(event.getKeyName())) {
        			/* buscar por el campo correspondiente */
        			Usuario usuario = new Usuario();
        			usuario.setNombreusuario(nombreusuarioText.getValue().toString());
        			listar(usuarioGrid, usuario, "nombreusuario");
        		}
			}
        });
        
        ButtonItem buscarButton = new ButtonItem("find", "");
        buscarButton.setIcon("view.png");
        buscarButton.setWidth(50);
        buscarButton.setLeft(443);
        buscarButton.addClickHandler(new ClickHandler() {
        	//@Override
            public void onClick(ClickEvent event) {
    			/* buscar por el campo correspondiente */
    			Usuario usuario = new Usuario();
    			if(nombreText.getValue() != null)
    				usuario.setNombre(nombreText.getValue().toString());
    			if(nombreusuarioText.getValue() != null)
    				usuario.setNombreusuario(nombreusuarioText.getValue().toString());
    			listar(usuarioGrid, usuario, "nombre");
			}
		});
        
        buscarFields.setFields(codigoText, nombreText, nombreusuarioText, buscarButton);
        /*--------------------------------------*/
        
        ListGridField codigoField = new ListGridField("codigo", "Codigo");
        ListGridField nombreField = new ListGridField("nombre", "Nombre");
        ListGridField nombreusuarioField = new ListGridField("nombreusuario", "Nombre de Usuario");
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

        usuarioGrid.addCellClickHandler(new CellClickHandler() {
			//@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 2) {
                	
                	Usuario usuario = new Usuario();
                	usuario.setIdusuario(record.getAttributeAsInt("codigo"));
                	usuario.setNombre(record.getAttribute("nombre"));
                	usuario.setNombreusuario(record.getAttribute("nombreusuario"));
                	
                	if (col == 3) {	/* Editar */
                		
                		new UsuarioDetalle(usuario, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		UsuarioServiceAsync service = GWT.create(UsuarioService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "usuarioService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						//@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						//@Override
        						public void onSuccess(Void result) {
        							new ListaUsuarios(mainWindow);
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
        nombreusuarioField.setWidth(150);
        usuarioGrid.setFields(codigoField, nombreField, nombreusuarioField, editarField, borrarField);  
        usuarioGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	//@Override
            public void onClick(ClickEvent event) {
        		new UsuarioDetalle(mainWindow);
			}
		});

        listar(usuarioGrid, new Usuario(), "nombre");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Usuarios</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(usuarioGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid usuarioGrid, Usuario usuario, String orden) {
		
    UsuarioServiceAsync service = GWT.create(UsuarioService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "usuarioService");

		try {
			service.listar(usuario, orden, new AsyncCallback<List<Usuario>>() {
				//@Override
				public void onSuccess(List<Usuario> result) {
					UsuarioRecord [] r = new UsuarioRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new UsuarioRecord((int)result.get(f).getIdusuario(), result.get(f).getNombre(), result.get(f).getNombreusuario());
						}
					}
					usuarioGrid.setData(r);
				}
				
				//@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 
	}
}

class UsuarioRecord extends ListGridRecord {

	public UsuarioRecord() {
	}

	public UsuarioRecord(int codigo, String nombre, String nombreusuario) {
		setCodigo(codigo);
		setNombre(nombre);
		setNombreusuario(nombreusuario);
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setNombre(String nombre) {
		setAttribute("nombre", nombre);
	}

	public void setNombreusuario(String nombreusuario) {
		setAttribute("nombreusuario", nombreusuario);
	}
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
