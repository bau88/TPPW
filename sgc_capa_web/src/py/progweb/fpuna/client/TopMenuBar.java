package py.progweb.fpuna.client;

import py.progweb.fpuna.client.Sgc_capa_web;

/*import com.fpuna.pweb.client.abm.CajaLista;
import com.fpuna.pweb.client.abm.ClienteLista;
import com.fpuna.pweb.client.abm.CompraLista;
import com.fpuna.pweb.client.abm.PagoLista;
import com.fpuna.pweb.client.abm.ProductoLista;
import com.fpuna.pweb.client.abm.ProveedorLista;
import com.fpuna.pweb.client.abm.RolLista;
import com.fpuna.pweb.client.abm.UsuarioLista;
import com.fpuna.pweb.client.abm.VentaLista;*/

import py.progweb.fpuna.client.abm.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TopMenuBar extends Composite {
	
	public TopMenuBar (final Sgc_capa_web mainWindow) {
		
		MenuBar menuBar = new MenuBar();
		MenuBar subMenuAdm = new MenuBar();
		MenuBar subMenuRol = new MenuBar();
		
		menuBar.setAutoOpen(true);
		subMenuAdm.setAutoOpen(true);
		subMenuRol.setAutoOpen(true);
				
		subMenuAdm.addItem("Cliente", new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Cliente"))
					new ListaClientes(mainWindow);
			}
		});
		
		subMenuAdm.addItem("Producto", new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Producto"))
					new ListaProductos(mainWindow);
			}
		});

		subMenuAdm.addItem("Proveedor", new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Proveedor"))
					new ListaProveedores(mainWindow);
			}
		});
				
		subMenuAdm.addItem("Caja", new Command() {
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Caja"))
					new ListaCajas(mainWindow);
			}
		});

		subMenuAdm.addItem("Rol", new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Rol"))
					new ListaRoles(mainWindow);
			}
		});

		subMenuAdm.addItem("Usuario", new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Usuario"))
					new ListaUsuarios(mainWindow);
			}
		});

		menuBar.addItem("Administracion", subMenuAdm);

		menuBar.addItem("Compra", new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Compra"))
					new ListaCompras(mainWindow);
			}
		});
		
		menuBar.addItem("Venta",new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Venta"))
					new ListaVentas(mainWindow);
			}
		});
		
		menuBar.addItem("Cobranza",new Command() {			
			@Override
			public void execute() {
				if (mainWindow.tienePermiso("Cobranza"))
					new ListaCobranzas(mainWindow);
			}
		});
		
		menuBar.addItem("Acerca de...",new Command() {			
			@Override
			public void execute() {
				//Esta parte defino para el cuadro dialogbox acercade
				final DialogBox acercade = new DialogBox();
				acercade.setText("Acerca De: ");
				acercade.setAnimationEnabled(true);
				final Button cerrarBoton = new Button("Cerrar");
				cerrarBoton.getElement().setId("cerrarBoton");
				acercade.center();
				VerticalPanel dialogVPanel = new VerticalPanel();
				dialogVPanel.addStyleName("dialogVPanel");
				dialogVPanel.add(new HTML("<b>Nombre del Producto: SGC</b>"));
				dialogVPanel.add(new HTML("<b>Version: 1.0</b>"));
				dialogVPanel.add(new HTML("<b>-----------------------------------------------------------------------------------------</b>"));
				dialogVPanel.add(new HTML("<br><b>Desarrollado por:</b>"));
				dialogVPanel.add(new HTML("<br><b>1-Baudelio Báez</b>"));
				dialogVPanel.add(new HTML("<br><b>2-Rodrigo Lugo</b>"));
				dialogVPanel.add(new HTML("<br><b>3-Juana Maldonado</b>"));
				dialogVPanel.add(new HTML("<b>-----------------------------------------------------------------------------------------</b>"));
				dialogVPanel.add(new HTML("<br><b>(c) Copyright SGC 2011. Todos los derechos reservados.  </b>")); 
				//dialogVPanel.add(new HTML("<br><b>Todos los derechos reservados. </b>"));
				dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
				cerrarBoton.setFocus(true);
				dialogVPanel.add(cerrarBoton);
				acercade.setWidget(dialogVPanel);
				cerrarBoton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						acercade.hide();						
					}
				});
			}
		});
		
		initWidget(menuBar);
	}
}