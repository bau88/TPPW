package py.progweb.fpuna.client.abm;
import java.util.Date;
import java.util.List;

import py.progweb.fpuna.client.services.FacturaService;
import py.progweb.fpuna.client.services.FacturaServiceAsync;
import py.progweb.fpuna.client.services.FacturaDetalleService;
import py.progweb.fpuna.client.services.FacturaDetalleServiceAsync;
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

public class VentaDetalle extends Canvas {

	public VentaDetalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public VentaDetalle(Factura factura, final Sgc_capa_web mainWindow) {
		
		VLayout layout = new VLayout(10);
		layout.setBackgroundColor("#006633");
		final Factura factura_actual=factura;
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
        label.setContents("<div style='color:black;font-size:15'><b>Detalle de Factura</b></div>");
                
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
        
        TextItem numeroText = new TextItem("numero");
        numeroText.setTitle("Nro. Factura");
        numeroText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        numeroText.setWrapTitle(false);
        
        TextItem pendienteText = new TextItem("pendiente");
        pendienteText.setTitle("Pendiente");
        pendienteText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        pendienteText.setWrapTitle(false);
        
        TextItem saldoText = new TextItem("saldo");
        saldoText.setTitle("Saldo");
        saldoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        saldoText.setWrapTitle(false);

        ButtonItem button = new ButtonItem("save", "Guardar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		FacturaServiceAsync service = GWT.create(FacturaService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
    			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "facturaService");
    			Factura factura = new Factura();
    			
    			factura.setFecha((Date)fechaText.getValue());
    			factura.setNumero(Integer.parseInt(form.getValueAsString("numero")));
    			factura.setPendiente(form.getValueAsString("pendiente"));
    			factura.setSaldo(Integer.parseInt(form.getValueAsString("saldo")));
				//compra.setTotalCompra(Integer.parseInt("15000"));
				if(form.getValueAsString("codigo") != null){
					factura.setId(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try {
					service.guardar(factura, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						@Override
						public void onSuccess(Void result) {
							new ListaVentas(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});      
  
		if (factura != null){
			codigoText.setDefaultValue(String.valueOf(factura.getId()));
			fechaText.setDefaultValue(factura.getFecha());
			numeroText.setDefaultValue(factura.getNumero());
			pendienteText.setDefaultValue(factura.getPendiente());
			saldoText.setDefaultValue(factura.getSaldo());
		}
		
		//En esta parte del código se agrega para el tema del listado de los 
		//Detalles de la factura
		final ListGrid facturadetalleGrid = new ListGrid();         
		facturadetalleGrid.setWidth(500);  
		facturadetalleGrid.setHeight(120);  
		facturadetalleGrid.setShowAllRecords(true);  
		facturadetalleGrid.setAlternateRecordStyles(true);
		facturadetalleGrid.setShowEdges(true);
		facturadetalleGrid.setBorder("0px");
		facturadetalleGrid.setBodyStyleName("normal");
		facturadetalleGrid.setLeaveScrollbarGap(false);
		facturadetalleGrid.setBackgroundColor("#99ffcc");
		
		ListGridField codigoField = new ListGridField("codigo", "Codigo");
        ListGridField productoField = new ListGridField("producto", "Producto"); 
        ListGridField cantidadField = new ListGridField("cantidad", "Cantidad"); 
        ListGridField precioventaField = new ListGridField("precioventa", "Precio"); 
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
        
        facturadetalleGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 3) {
                	FacturaDetalle facturadetalle = new FacturaDetalle();                	   	               	
                	facturadetalle.setIdFactDet(record.getAttributeAsInt("codigo"));
                	facturadetalle.setId_producto(record.getAttributeAsInt("producto"));
                	facturadetalle.setCantidad(Integer.parseInt(record.getAttribute("cantidad")));
                	facturadetalle.setPrecioVenta(Integer.parseInt(record.getAttribute("precioventa")));
                	                	
                	if (col == 4) {	/* Editar */
                		//Aca hay que llamar para editar el detalle, o sea a CargaProductoFactura
                		//new VentaDetalle(factura, mainWindow);
                		
                	} 
                	else {		/* Borrar */
        				
                		FacturaDetalleServiceAsync service = GWT.create(FacturaDetalleService.class);
                		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
                		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "facturadetalleService");
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
        							new VentaDetalle(factura_actual,mainWindow);
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
        precioventaField.setWidth(50);
        facturadetalleGrid.setFields(codigoField, productoField, cantidadField,precioventaField, editarField, borrarField);  
        facturadetalleGrid.setCanResizeFields(true);
        
        ButtonItem buttondetalles = new ButtonItem("add", "Cargar");
        buttondetalles.setStartRow(false);
        buttondetalles.setWidth(80);
        buttondetalles.setIcon("add.png");
        buttondetalles.setAlign(Alignment.CENTER);
        buttondetalles.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		//Aca tengo que llamar al formulario CargaProductoFactura
        		new CargaProductoFactura(mainWindow);
			}
		});
        listar(facturadetalleGrid, new FacturaDetalle(), "nombre");
        
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
        
		form.setFields(codigoText, fechaText, numeroText,pendienteText,saldoText,button, buttondetalles);
		
		
		layout.addMember(label);
        layout.addMember(form);
        layout.addMember(labeldetalles);
        layout.addMember(facturadetalleGrid);
        mainWindow.showDialog(layout);
	}
	
private void listar(final ListGrid facturadetalleGrid,FacturaDetalle facturadetalle, String orden) {
		
	    FacturaDetalleServiceAsync service = GWT.create(FacturaDetalleService.class);	
		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "facturadetalleService");

			try {
				service.listar(facturadetalle, orden, new AsyncCallback<List<FacturaDetalle>>() {
					@Override
					public void onSuccess(List<FacturaDetalle> result) {
						FacturaDetalleRecord [] r = new FacturaDetalleRecord [result.size()];
						for(int f = 0; f < result.size(); f++) {
							if(result.get(0)!=null){
								r[f] = new FacturaDetalleRecord((int)result.get(f).getIdFactDet(), result.get(f).getId_producto(), result.get(f).getCantidad(), result.get(f).getPrecioVenta());
							}
						}
						facturadetalleGrid.setData(r);
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
class FacturaDetalleRecord extends ListGridRecord {

	public FacturaDetalleRecord() {
	}

	public FacturaDetalleRecord(int codigo, Integer producto, Integer cantidad, Double precioventa) {
		setCodigo(codigo);
		setProducto(producto);
		setCantidad(cantidad);
		setPrecioventa(precioventa);
		
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
	   
	public void setPrecioventa(Double precioventa) {
		setAttribute("precioventa",precioventa);
	}
	
	public void setEditar(String edit) {
		setAttribute("edit", edit);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}


