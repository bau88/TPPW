 package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.ProductoService;

import py.progweb.fpuna.client.services.ProductoServiceAsync;

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


public class ListaProductos extends Canvas {
	
	public ListaProductos(final Sgc_capa_web mainWindow) {

		String PATH_IMG = "/images/";
		VLayout layout = new VLayout(10);
        layout.setBackgroundColor("#006633");
        final ListGrid productoGrid = new ListGrid(); 
        
        productoGrid.setWidth(500);  
        productoGrid.setHeight(224);  
        productoGrid.setShowAllRecords(true);  
        productoGrid.setAlternateRecordStyles(true);
        productoGrid.setShowEdges(true);
        productoGrid.setBorder("0px");
        productoGrid.setBodyStyleName("normal");
        productoGrid.setLeaveScrollbarGap(false);
        productoGrid.setBackgroundColor("#99ffcc");
	    
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
        				Producto producto = new Producto();
        				producto.setId(Integer.parseInt(codigoText.getValue().toString()));
            			listar(productoGrid, producto, "codigo");	
        			}
        			
        		}
			}
        });
        
        final TextItem descripcionText = new TextItem("descripcion");
        descripcionText.setWrapTitle(false);
        descripcionText.setLeft(55);
        descripcionText.setWidth(180);
        descripcionText.addKeyPressHandler(new KeyPressHandler () {
        	public void onKeyPress(KeyPressEvent event) {
        		if ("Enter".equals(event.getKeyName())) {
        			/* buscar por el campo correspondiente */
        			//Usuario usuario = null;
        			if(descripcionText.getValue() != null){
        				Producto producto = new Producto();
        				producto.setDescripcion(descripcionText.getValue().toString());
            			listar(productoGrid, producto, "descripcion");	
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
            	Producto producto= new Producto();
    			
    			if(descripcionText.getValue() != null)
    				producto.setDescripcion(descripcionText.getValue().toString());
    			/*if(nombreusuarioText.getValue() != null)
    				cliente.setNombreusuario(nombreusuarioText.getValue().toString());*/
    			listar(productoGrid, producto, "descripcion");
			}
		});
        
        buscarFields.setFields(codigoText, descripcionText, buscarButton);
        /*--------------------------------------*/
        
        ListGridField codigoField = new ListGridField("codigo", "Codigo");        
        ListGridField descripcionField = new ListGridField("descripcion", "Descripcion");
        ListGridField precioField = new ListGridField("precio", "Precio");
        ListGridField cantidadField = new ListGridField("cantidad", "Cantidad");
        ListGridField porcgananciaField = new ListGridField("porcganancia", "% Ganancia");
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

        productoGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 4) {
                	Producto producto = new Producto();
                	
                	producto.setId(record.getAttributeAsInt("codigo"));
                	producto.setDescripcion(record.getAttribute("descripcion"));
                	producto.setPrecio(Double.parseDouble(record.getAttribute("precio")));
                	producto.setCantidad(Integer.parseInt(record.getAttribute("cantidad")));
                	producto.setPorcganancia(Double.parseDouble(record.getAttribute("porcganancia")));
                	
                	
                	if (col == 5) {	/* Editar */
                		
                		new ProductoDetalle(producto, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		ProductoServiceAsync service = GWT.create(ProductoService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "productoService");
        				try {
        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

        						@Override
        						public void onFailure(Throwable caught) {
        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
        						}

        						@Override
        						public void onSuccess(Void result) {
        							new ListaProductos(mainWindow);
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
        descripcionField.setWidth(180);
        precioField.setWidth(50);
        cantidadField.setWidth(50);
        porcgananciaField.setWidth(50);
        
        productoGrid.setFields(codigoField, descripcionField, precioField, cantidadField, porcgananciaField, editarField, borrarField);  
        productoGrid.setCanResizeFields(true);
        
        ButtonItem button = new ButtonItem("add", "Agregar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("add.png");
        button.setAlign(Alignment.CENTER);
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		new ProductoDetalle(mainWindow);
			}
		});

        listar(productoGrid, new Producto(), "descripcion");

		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Lista de Productos</b></div>");
        

        layout.addMember(label);
        layout.addMember(buscarFields);
        layout.addMember(productoGrid);

        DynamicForm form = new DynamicForm();   
        //form.setBackgroundColor("#99ffcc");
        form.setWidth(300);
        form.setItems(button);   
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
	
	private void listar(final ListGrid productoGrid, Producto producto, String orden) {
		
    ProductoServiceAsync service = GWT.create(ProductoService.class);	
	ServiceDefTarget serviceDef = (ServiceDefTarget) service;
	serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "productoService");

		try {
			service.listar(producto, orden, new AsyncCallback<List<Producto>>() {
				@Override
				public void onSuccess(List<Producto> result) {
					ProductoRecord [] r = new ProductoRecord [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							r[f] = new ProductoRecord((int)result.get(f).getId(), result.get(f).getDescripcion(), (double)result.get(f).getPrecio(),(Integer)result.get(f).getCantidad(),(double)result.get(f).getPorcganancia());
						}
					}
					productoGrid.setData(r);
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

class ProductoRecord extends ListGridRecord {

	public ProductoRecord() {
	}

	public ProductoRecord(int codigo, String descripcion, Double precio, Integer cantidad, Double porcganancia ) {
		setCodigo(codigo);
		setDescripcion(descripcion);
		setPrecio(precio);
		setCantidad(cantidad);
		setPorcganancia(porcganancia);
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setDescripcion(String descripcion) {
		setAttribute("descripcion", descripcion);
	}
	public void setPrecio(Double precio) {
		setAttribute("precio", precio);
	}
	public void setCantidad(Integer cantidad) {
		setAttribute("cantidad", cantidad);
	}
	public void setPorcganancia(Double porcganancia) {
		setAttribute("porcganancia", porcganancia);
	}

	
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
