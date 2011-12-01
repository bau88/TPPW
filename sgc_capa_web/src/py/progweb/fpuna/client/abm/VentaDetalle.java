package py.progweb.fpuna.client.abm;

import py.progweb.fpuna.client.services.ClienteService;
import py.progweb.fpuna.client.services.ClienteServiceAsync;
import py.progweb.fpuna.client.services.FacturaService;
import py.progweb.fpuna.client.services.FacturaServiceAsync;
import py.progweb.fpuna.client.services.ProductoService;
import py.progweb.fpuna.client.services.ProductoServiceAsync;
import py.progweb.fpuna.client.Sgc_capa_web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.ListGridField;  
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import com.blogspot.tecnologiasjava.model.Cliente;
import com.blogspot.tecnologiasjava.model.Factura;
import com.blogspot.tecnologiasjava.model.FacturaDetalle;
//import py.progweb.fpuna.entidades.FacturaDetalle;
import com.blogspot.tecnologiasjava.model.Producto;
import java.util.Random;
public class VentaDetalle extends Canvas {
	
	public VentaDetalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	public VentaDetalle(Factura factura_actual, final Sgc_capa_web mainWindow) {
		
		String PATH_IMG = "/sgc_capa_web/images/";
		HLayout hLayout = new HLayout(2);
		VLayout vLayout = new VLayout(4);
        
		/* T�tulo ------------------------------------ */
		Label label = new Label();
		label.setBackgroundColor("#99ffcc");
        label.setHeight(30);
        label.setWidth(500);
        label.setPadding(10);   
        label.setAlign(Alignment.CENTER);   
        label.setValign(VerticalAlignment.CENTER);   
        label.setWrap(false);
        label.setShowEdges(true);   
        label.setContents("<div style='color:black;font-size:15'><b>Detalle de Ventas</b></div>");
		/* ------------------------------------------- */
        final Random rnd = new Random();
        /* Cabecera ---------------------------------- */
        final Map<String, String> cliente = new HashMap<String, String>();
        final SelectItem clienteItem = new SelectItem();
        clienteItem.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        clienteItem.setName("cliente");
        clienteItem.setTitle("Cliente");
        clienteItem.setAddUnknownValues(false);
        clienteItem.setWidth(150);
        clienteItem.setLeft(100);
        
        /* Para cargar el comboBox de Clientes */

        ClienteServiceAsync service = GWT.create(ClienteService.class);	

		try {
			Cliente cliente_listar = new Cliente();
			String orden = "idCliente";
			service.listar(cliente_listar, orden, new AsyncCallback<List<Cliente>>() {
				@Override
				public void onSuccess(List<Cliente> result) {
					String [] r = new String [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							cliente.put(result.get(f).getNombre(), new String ("" + result.get(f).getId()));
							r[f] = new String(result.get(f).getNombre());
						}
					}
					clienteItem.setValueMap(r);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	
        final TextItem nroFactText = new TextItem("nroFact");
        nroFactText.setTitle("Factura No");
        nroFactText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        nroFactText.setWrapTitle(false);
        nroFactText.setWidth(150);
        nroFactText.setLeft(360);
        nroFactText.setTextAlign(Alignment.CENTER);
        
		final DateItem fechaItem = new DateItem();
        fechaItem.setName("fecha");
        fechaItem.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        fechaItem.setTitle("Fecha");
        fechaItem.setUseTextField(true);
        fechaItem.setDefaultValue(new Date ());
        fechaItem.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
        fechaItem.setWidth(146);
        fechaItem.setLeft(400);
        
        final DynamicForm formCab = new DynamicForm();
        formCab.setBackgroundColor("#006633");
        formCab.setItems(clienteItem, nroFactText, fechaItem);
        formCab.setItemLayout(FormLayoutType.TABLE);
	    HLayout hLayoutCab = new HLayout(2);
	    hLayoutCab.addMember(formCab);
        
		/* Total ------------------------------------- */
        final TextItem totalText = new TextItem("total");
        totalText.setTitle("Total");
        totalText.setWrapTitle(false);
        totalText.setDisabled(true);
        totalText.setWidth(80);
        totalText.setLeft(360);
        totalText.setTextAlign(Alignment.RIGHT);
        /* ------------------------------------------- */
        
		/* Grilla ------------------------------------ */
        final ListGrid ventaGrid = new ListGrid();
        ventaGrid.setBackgroundColor("#99ffcc");
        ventaGrid.setWidth(500);  
        ventaGrid.setHeight(224);  
        ventaGrid.setShowAllRecords(true);  
        ventaGrid.setAlternateRecordStyles(true);
        ventaGrid.setShowEdges(true);
        ventaGrid.setBorder("0px");
        ventaGrid.setBodyStyleName("normal");
        ventaGrid.setLeaveScrollbarGap(false);
        ventaGrid.setAutoFetchData(true);  
        ventaGrid.setCanEdit(true);  
        ventaGrid.setEditEvent(ListGridEditEvent.CLICK);  
        ventaGrid.setListEndEditAction(RowEndEditAction.NEXT);
        
        
        /* Columnas ---------------------------------- */
        ListGridField codigoField = new ListGridField("codigo", "Codigo");
        final ListGridField productoField = new ListGridField("producto", "Producto");
        ListGridField cantidadField = new ListGridField("cantidad", "Cantidad");
        ListGridField precioField = new ListGridField("precio", "Precio");
        ListGridField totalField = new ListGridField("total", "Total");
        ListGridField borrarField = new ListGridField("remove", "Borrar");
        
        cantidadField.setType(ListGridFieldType.INTEGER);
        precioField.setType(ListGridFieldType.FLOAT);
        totalField.setType(ListGridFieldType.FLOAT);
                
        cantidadField.addChangedHandler(new ChangedHandler() {  
            public void onChanged(ChangedEvent event) {
            	calcularProducto(event.getRowNum(), ventaGrid, totalText);
            }  
        });
        
        codigoField.setAlign(Alignment.CENTER);
        cantidadField.setAlign(Alignment.RIGHT);
        precioField.setAlign(Alignment.RIGHT);
        totalField.setAlign(Alignment.RIGHT);
        borrarField.setAlign(Alignment.CENTER);

        precioField.setCanEdit(false);
        totalField.setCanEdit(false);
        borrarField.setCanEdit(false);
        
        codigoField.setHidden(true);
        productoField.setWidth(200);
        cantidadField.setWidth(79);
        precioField.setWidth(79);
        totalField.setWidth(80);
        borrarField.setWidth(50);
        
        borrarField.setType(ListGridFieldType.IMAGE);
        borrarField.setImageURLPrefix(PATH_IMG);
        borrarField.setImageURLSuffix(".png");
        
        borrarField.setDefaultValue("remove");
        
        /* Para cargar el comboBox de Productos */
        final Map<String, String> producto = new HashMap<String, String>();
		final Map<String, String> productoPrecio = new HashMap<String, String>();
        
        ProductoServiceAsync service_producto = GWT.create(ProductoService.class);	

		try {
			Producto producto_combo = new Producto();
			String orden = "id";
			service_producto.listar(producto_combo, orden, new AsyncCallback<List<Producto>>() {
				@Override
				public void onSuccess(List<Producto> result) {
					String [] r = new String [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							producto.put(result.get(f).getDescripcion(), new String ("" + result.get(f).getId()));
							//productoPrecio.put(result.get(f).getDescripcion(), new String ("" + result.get(f).getCostoActual()));
							productoPrecio.put(result.get(f).getDescripcion(), new String ("" + result.get(f).getPrecio()));
							r[f] = new String(result.get(f).getDescripcion());
						}
					}
					productoField.setValueMap(r);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
		//record.getAttributeAsString("producto")
		// recuperar el precio, no se usa aca
		productoField.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				String precio = productoPrecio.get(event.getValue());
        		ventaGrid.setEditValue(event.getRowNum(), "precio", precio);

				//Window.alert(event.getValue() + ", precio: " + precio + ", fila: " + event.getRowNum());
			}
		});
/*        productoField.addChangedHandler(new ChangedHandler() {  
            public void onChanged(ChangedEvent event) {
            	String precio = productoPrecio.get(productoField.getValueField());
        		ventaGrid.setEditValue(event.getRowNum(), "precio", precio);
            }  
        });
*/
		ventaGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
                ListGridRecord record =  event.getRecord();
                int col = event.getColNum();
                if (col > 3) { /* Eliminar de la grilla */
                	ventaGrid.removeData(record);
                	calcularTotal(ventaGrid, totalText);
                }
			}
		});

        ventaGrid.setFields(codigoField, productoField, cantidadField, precioField, totalField, borrarField);  
        ventaGrid.setCanResizeFields(true);
        /* ------------------------------------------- */

		/* Botones ----------------------------------- */
        ButtonItem buttonConfirmar = new ButtonItem("add", "Confirmar");
        buttonConfirmar.setStartRow(false);
        buttonConfirmar.setTop(55);
        buttonConfirmar.setLeft(-85);
        buttonConfirmar.setWidth(82);
        buttonConfirmar.setHeight(70);
        buttonConfirmar.setIcon("save.png");
        buttonConfirmar.setAlign(Alignment.CENTER);
        buttonConfirmar.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		/* Guardar la venta */
        		final Factura factura = new Factura();
        		factura.setFecha((Date)(fechaItem.getValue()));
        		factura.setCliente(null);
        		factura.setNumero((int)(rnd.nextDouble() * 1000));
        		factura.setPagos(null);
        		factura.setPendiente("SI");
        		factura.setProductos(null);
        		Double saldo_a_establecer = calcularTotal(ventaGrid);
        		factura.setSaldo(saldo_a_establecer);        		

        		for (int i=0; i<ventaGrid.getRecords().length; i++) {
        			
        			FacturaDetalle detalle = new FacturaDetalle();
        			detalle.setCantidad(Integer.parseInt(ventaGrid.getRecord(i).getAttribute("cantidad")));
        			detalle.setPrecioVenta(Double.parseDouble(ventaGrid.getRecord(i).getAttribute("precio")));
        			detalle.setId_producto(Integer.parseInt(producto.get(ventaGrid.getRecord(i).getAttribute("producto"))));
        			factura.getFacturaDetalles().add(detalle);        			
        			
        		}

                FacturaServiceAsync service = GWT.create(FacturaService.class);
        		try {
					service.guardarFacturaConDetalles(factura, new AsyncCallback<Integer>() {
 
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error y no se pudo registrar la venta");
						}

						@Override
						public void onSuccess(Integer result) {
							imprimir_factura(result);
							new ListaVentas(mainWindow);
						}

						private void imprimir_factura(Integer factura) {
							// TODO Auto-generated method stub 
							 String parametros= "width=200, height=77";
							 Window.open("http://www.google.com.py","Reporte impreso",parametros);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		ButtonItem buttonAdd = new ButtonItem("new", "Agregar Producto");  
		buttonAdd.setStartRow(false);
		buttonAdd.setWidth(160);
		buttonAdd.setIcon("add.png");
		buttonAdd.setAlign(Alignment.CENTER);
		buttonAdd.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
            	ventaGrid.startEditingNew();
            }  
        });

		DynamicForm formAdd = new DynamicForm();
		formAdd.setBackgroundColor("#006633");
        DynamicForm formSave = new DynamicForm();
        
        formAdd.setItemLayout(FormLayoutType.ABSOLUTE);
        formSave.setItemLayout(FormLayoutType.ABSOLUTE);
        formSave.setBackgroundColor("#006633");
        formSave.setItems(buttonConfirmar);		
		/* ------------------------------------------- */
		        
        formAdd.setItems(buttonAdd, totalText);
        /* ------------------------------------------- */
                
		/* Agregar los componentes en los layout's --- */
        vLayout.addMember(label);
        vLayout.setBackgroundColor("#006633");
        vLayout.addMember(hLayoutCab);
		vLayout.addMember(ventaGrid);
		vLayout.addMember(formAdd);

        hLayout.addMember(vLayout);
        hLayout.addMember(formSave);
        /* ------------------------------------------- */
        
        mainWindow.showDialog(hLayout);
	}
	
	private void calcularProducto(int fila, final ListGrid ventaGrid, final TextItem totalText) {

    	Object cant = ventaGrid.getEditedCell(fila, "cantidad");
    	Object prec = ventaGrid.getEditedCell(fila, "precio");
    	int cantidad = cant != null ? Integer.parseInt(cant.toString()) : 0;
    	double precio = prec != null ? Double.parseDouble(prec.toString()) : 0;

    	ventaGrid.setEditValue(fila, "total", "" + (cantidad*precio));
    	 
    	calcularTotal(ventaGrid, totalText);
	}
	
	private void calcularTotal(final ListGrid ventaGrid, final TextItem totalText) {
		int total = 0;
       	for (int i=0; i<ventaGrid.getRecords().length+1; i++) {
       		total += ventaGrid.getEditedCell(i, "total") != null ? Double.parseDouble(ventaGrid.getEditedCell(i, "total").toString()) : 0;
      	}
     	totalText.setValue(total);
	}
	
	private Double calcularTotal(final ListGrid ventaGrid) {
		Double total = 0.0;
       	for (int i=0; i<ventaGrid.getRecords().length+1; i++) {
       		total += ventaGrid.getEditedCell(i, "total") != null ? Double.parseDouble(ventaGrid.getEditedCell(i, "total").toString()) : 0;
      	}
     	//totalText.setValue(total);
       	return total;
	}
}

class VentaDetalleRecord extends ListGridRecord {

	public VentaDetalleRecord() {
	}

	public VentaDetalleRecord(int codigo, String producto, int cantidad, double precio, double total) {
		setCodigo(codigo);
		setProducto(producto);
		setCantidad(cantidad);
		setPrecio(precio);
		setTotal(total);
		setBorrar("remove");
	}

	public void setCodigo(int codigo) {
		setAttribute("codigo", codigo);
	}
	
	public void setProducto(String producto) {
		setAttribute("producto", producto);
	}
	
	public void setCantidad(int cantidad) {
		setAttribute("cantidad", cantidad);
	}
	
	public void setPrecio(double precio) {
		setAttribute("precio", precio);
	}
	
	public void setTotal(double total) {
		setAttribute("total", total);
	}

	public void setBorrar(String remove) {
		setAttribute("remove", remove);
	}
}
