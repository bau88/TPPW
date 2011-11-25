package py.progweb.fpuna.client.abm;
import java.util.Date;
import java.util.List;

import py.progweb.fpuna.client.services.CompraDetalleService;
import py.progweb.fpuna.client.services.CompraDetalleServiceAsync;
import py.progweb.fpuna.client.services.CompraService;
import py.progweb.fpuna.client.services.CompraServiceAsync;
import py.progweb.fpuna.client.Sgc_capa_web;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;   
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;   
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;   
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;   
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import py.progweb.fpuna.entidades.*;

public class Compra_Detalle extends Canvas {

	public Compra_Detalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public Compra_Detalle(Compra compra, final Sgc_capa_web mainWindow) {
		
		VLayout layout = new VLayout(10);
		layout.setBackgroundColor("#006633");
		final Compra compra_actual=compra;
		String PATH_IMG = "/images/";
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
        label.setContents("<div style='color:black;font-size:15'><b>Detalle de Compra</b></div>");
                
        TextItem codigoText = new TextItem("codigo");
        codigoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        codigoText.setTitle("Codigo");
        codigoText.setDisabled(true);
        codigoText.setWrapTitle(false);
  
        final TextItem fechaText = new TextItem("fecha");
        fechaText.setTitle("Fecha");
        fechaText.setDefaultValue(new Date());
        fechaText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        fechaText.setWrapTitle(false);
        
        final TextItem totalText = new TextItem("total");
        totalText.setTitle("Total de Compra");
        totalText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        totalText.setWrapTitle(false);

        ButtonItem button = new ButtonItem("save", "Guardar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		CompraServiceAsync service = GWT.create(CompraService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "compraService");
    			Compra compra = new Compra();     			
				compra.setFecha((Date)fechaText.getValue());
				compra.setTotalCompra(Integer.parseInt(form.getValueAsString("total")));
				//compra.setTotalCompra(Integer.parseInt("15000"));
				if(form.getValueAsString("codigo") != null){
					compra.setId(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(compra, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new ListaCompras(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
        
		if (compra != null){
			codigoText.setDefaultValue(String.valueOf(compra.getId()));
			fechaText.setDefaultValue(compra.getFecha());
			totalText.setDefaultValue(compra.getTotalCompra());
		}
		
		//En esta parte del código se agrega para el tema del listado de los 
		//Detalles de la Compra
		final ListGrid compradetalleGrid = new ListGrid();         
		compradetalleGrid.setWidth(500);  
		compradetalleGrid.setHeight(120);  
		compradetalleGrid.setShowAllRecords(true);  
		compradetalleGrid.setAlternateRecordStyles(true);
		compradetalleGrid.setShowEdges(true);
		compradetalleGrid.setBorder("0px");
		compradetalleGrid.setBodyStyleName("normal");
		compradetalleGrid.setLeaveScrollbarGap(false);
		compradetalleGrid.setBackgroundColor("#99ffcc");
				
		ListGridField codigoField = new ListGridField("codigo", "Codigo");
		ListGridField productoField = new ListGridField("producto", "Producto"); 
		ListGridField cantidadField = new ListGridField("cantidad", "Cantidad"); 
		ListGridField preciocompraField = new ListGridField("preciocompra", "Compra"); 
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
		        
		compradetalleGrid.addCellClickHandler(new CellClickHandler() {
		@Override
		public void onCellClick(CellClickEvent event) {
		                ListGridRecord record =  event.getRecord();
		                int col = event.getColNum();
		                if (col > 3) {
		                	CompraDetalle compradetalle = new CompraDetalle();                	   	               	
		                	compradetalle.setIdCompDet(record.getAttributeAsInt("codigo"));
		                	compradetalle.setId_producto(record.getAttributeAsInt("producto"));
		                	compradetalle.setCantidad(Integer.parseInt(record.getAttribute("cantidad")));
		                	compradetalle.setPrecioCompra(Integer.parseInt(record.getAttribute("preciocompra")));
		                	                	
		                	if (col == 4) {	/* Editar */
		                		//Aca hay que llamar para editar el detalle, o sea a CargaProductoFactura
		                		//new VentaDetalle(factura, mainWindow);
		                		
		                	} 
		                	else {		/* Borrar */
		        				
		                		CompraDetalleServiceAsync service = GWT.create(CompraDetalleService.class);
		                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
		                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "compradetalleService");
		        				try {
		        					service.eliminar(record.getAttributeAsInt("codigo"), new AsyncCallback<Void>() {

		        						@Override
		        						public void onFailure(Throwable caught) {
		        							Window.alert("Ocurrio un error y no se puedo eliminar (objeto referenciado)");// " + caught.getClass().getName() + " " + caught.getMessage()) ;
		        						}

		        						@Override
		        						public void onSuccess(Void result) {
		        							//Aqui hay que llamar al mismo formulario pasandole la factura actual
		        							//Aqui hay que implementar un metodo que retorne la factura del 
		        							//cual se esta desplegando el detalle
		        							new Compra_Detalle(compra_actual,mainWindow);
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
		 productoField.setWidth(180);
		 cantidadField.setWidth(50);
		 preciocompraField.setWidth(50);
		 compradetalleGrid.setFields(codigoField, productoField, cantidadField,preciocompraField, editarField, borrarField);  
		 compradetalleGrid.setCanResizeFields(true);
		        
		 ButtonItem buttondetalles = new ButtonItem("add", "Cargar");
		 buttondetalles.setStartRow(false);
		 buttondetalles.setWidth(80);
		 buttondetalles.setIcon("add.png");
		 buttondetalles.setAlign(Alignment.CENTER);
		 buttondetalles.addClickHandler(new ClickHandler() {
		 @Override
		 public void onClick(ClickEvent event) {
		        		//Aca tengo que llamar al formulario CargaProductoFactura
		        		new CargaProductoCompra(mainWindow);
					}
				});
		 listar(compradetalleGrid, new CompraDetalle(), "nombre");
		        
		 Label labeldetalles = new Label();
		 labeldetalles.setBackgroundColor("#99ffcc");
		 labeldetalles.setHeight(2);
		 labeldetalles.setWidth(500);
		 labeldetalles.setPadding(5);   
		 labeldetalles.setAlign(Alignment.CENTER);   
		 labeldetalles.setValign(VerticalAlignment.CENTER);   
		 labeldetalles.setWrap(false);
		 labeldetalles.setShowEdges(true);   
		 labeldetalles.setContents("<div style='color:black;font-size:15'><b>Detalles</b></div>");
    
		form.setFields(codigoText, fechaText, totalText,button,buttondetalles);
		
		layout.addMember(label);
        layout.addMember(form);
        layout.addMember(labeldetalles);
        layout.addMember(compradetalleGrid);
        mainWindow.showDialog(layout);
	}
	private void listar(final ListGrid compradetalleGrid,CompraDetalle compradetalle, String orden) {
		
	    CompraDetalleServiceAsync service = GWT.create(CompraDetalleService.class);	
		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "compradetalleService");

			try {
				service.listar(compradetalle, orden, new AsyncCallback<List<CompraDetalle>>() {
					@Override
					public void onSuccess(List<CompraDetalle> result) {
						CompraDetalleRecord [] r = new CompraDetalleRecord [result.size()];
						for(int f = 0; f < result.size(); f++) {
							if(result.get(0)!=null){
								r[f] = new CompraDetalleRecord((int)result.get(f).getIdCompDet(), result.get(f).getId_producto(), result.get(f).getCantidad(), result.get(f).getPrecioCompra());
							}
						}
						compradetalleGrid.setData(r);
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
class CompraDetalleRecord extends ListGridRecord {

	public CompraDetalleRecord() {
	}

	public CompraDetalleRecord(int codigo, Integer producto, Integer cantidad, Double precioventa) {
		setCodigo(codigo);
		setProducto(producto);
		setCantidad(cantidad);
		setPreciocompra(precioventa);
		
		setEditar("edit");
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}

	public void setProducto(Integer producto) {
		setAttribute("producto", producto);
	}
	
	public void setCantidad(Integer cantidad) {
		setAttribute("cantidad",cantidad);
	}
	   
	public void setPreciocompra(Double precioventa) {
		setAttribute("precioventa",precioventa);
	}
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
