package py.progweb.fpuna.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

//import entidades.Caja;
import py.progweb.fpuna.entidades.Usuario;
import py.progweb.fpuna.client.services.UsuarioService;
import py.progweb.fpuna.client.services.UsuarioServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sgc_capa_web implements EntryPoint {
	
	final Map<String,Boolean> permisos = new HashMap<String,Boolean>();
	
	SimplePanel sp;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	//final Caja caja = new Caja();
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		this.sp = new SimplePanel();
		VerticalPanel vPanel = new VerticalPanel();
		EncabezadoPrincipal encabezadoprincipal = new EncabezadoPrincipal();
		
		TopMenuBar menuBar = new TopMenuBar(this);
		
		vPanel.setWidth("100%");
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel.add(encabezadoprincipal);
		vPanel.add(menuBar);
		
		
		MenuBar mb = new MenuBar(); // Puse xq no se me ocurria poner otra cosa para que la tabla baje mas.
		mb.setHeight("30");
		vPanel.add(mb);
		
		vPanel.add(this.sp);
		
		/* Login ------------------------------ */
		final Window winModal = new Window();
		winModal.setBackgroundColor("#99ffcc");
        winModal.setWidth(360);
        winModal.setHeight(115);
        winModal.setTitle("Login");
        
        winModal.setShowMinimizeButton(false);
        winModal.setShowCloseButton(false);
        winModal.setCanDragReposition(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();

        DynamicForm form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setItemLayout(FormLayoutType.TABLE);
        form.setBackgroundColor("#006633");
        
        final TextItem userItem = new TextItem();
        userItem.setTitle("Usuario");
        userItem.setTitleStyle("{font-color:#F0F0F0;font-weigh:bold}");
        userItem.setRequired(true);
        
        final PasswordItem pwdItem = new PasswordItem();
        pwdItem.setTitle("Password");
        pwdItem.setTitleStyle("{font-color:#F0F0F0;font-weigh:bold}");
        pwdItem.setRequired(true);
        
        ButtonItem loginItem = new ButtonItem("login", "Ingresar");
        loginItem.setLeft(200);
        loginItem.setWidth(100);
        //loginItem.setIcon("icons/16/approved.png");
        loginItem.setIcon("approve.png");
        loginItem.addClickHandler(new ClickHandler() {
        	@Override
            public void onClick(ClickEvent event) {
        		
        		final Usuario usuario  = new Usuario();
        		
        		final String user = (String)userItem.getValue();
        		final String pwd = (String)pwdItem.getValue();
        		
        		if(user != null && pwd != null) {
        			usuario.setNombreusuario(user);
        			usuario.setContrasenha(pwd);

        			UsuarioServiceAsync service = GWT.create(UsuarioService.class);

        			try {
        				//winModal.destroy();
        				//service.buscar_por_nombreusuario(usuario.getNombreusuario(), new AsyncCallback<Usuario>());
        			      service.listar(usuario, "nombre", new AsyncCallback<List<Usuario>>() {
        					@Override
        					public void onSuccess(List<Usuario> result) {
        						if (result.size() > 0) {
        							int success=0;
        							int encontrado=0;
        							for (Usuario usuario: result){
    									if (usuario.getNombreusuario().equals(user)){
    										encontrado=1;
    										if (usuario.getContrasenha().equals(pwd)){
    											success=1;
    										}
    										else{
    											com.google.gwt.user.client.Window.alert("Contraseña incorrecta");
    										}
    									}
    								}
        							if(success== 1) {
        								//Se pone administrador para que pueda tener acceso a todos los permisos
        								if (verificarAccesoRol("Administrador")) {
    										winModal.destroy();
    									}
        								/*caja.setIdCaja(result.get(0).getId_caja());
        								if (result.get(0).getRol() != null) {
        									if (verificarAccesoRol(result.get(0).getRol().getNombre())) {
        										winModal.destroy();
        									}
        								}*/
        							}
        							if (encontrado==0){
        								com.google.gwt.user.client.Window.alert("Usuario no encontrado");
        							}
        						} else {
        							com.google.gwt.user.client.Window.alert("Usuario y/o password incorrectos");
        						}
        					}
        					
        					@Override
        					public void onFailure(Throwable caught) {
        						com.google.gwt.user.client.Window.alert("Ocurrio un error durante la autenticacion");
        					}
        				});
        			} catch (Exception e) {
        				e.printStackTrace();
        			} 

        		} else {
        			com.google.gwt.user.client.Window.alert("Usuario y/o password en blanco");
        		}        	
        	}
		});
        
        form.setFields(userItem, pwdItem, loginItem);
        winModal.addItem(form);
        winModal.show();
        /* ------------------------------------ */
		
		RootPanel.get().add(vPanel);
	}

	/**
	 * Mostrar en ventana principal el dialogo pasado
	 * @param widget
	 */
	public void showDialog(Widget widget) {
		if (this.sp.getWidget() != null)
			this.sp.remove(this.sp.getWidget());
		
		this.sp.setWidget(widget);
	}
		
	public boolean tienePermiso(String menu) {
		if (permisos.get(menu) != false)
			return true;
		
		com.google.gwt.user.client.Window.alert("No tiene permisos suficiente para realizar esta operacion");
		return false;
	}
	
	public int getCaja() {
		//return caja.getIdCaja();
		return 0;
	}
	
	private boolean verificarAccesoRol(String rol) {

		if (rol != null) {
			//Nuestra "tablita" de permisos inicializamos a false cada componente			
			permisos.put("Cliente", false);
			permisos.put("Producto", false);
			permisos.put("Proveedor", false);
			permisos.put("Caja", false);
			permisos.put("Rol", false);
			permisos.put("Compra", false);
			permisos.put("Venta", false);
			permisos.put("Cobranza", false);
			permisos.put("Usuario", false);
			//Se pregunta si el rol es administrador
			if (rol.equals("Administrador")) {
				permisos.put("Cliente", true);
				permisos.put("Producto", true);
				permisos.put("Proveedor", true);
				permisos.put("Caja", true);
				permisos.put("Rol", true);
				permisos.put("Compra", true);
				permisos.put("Venta", true);
				permisos.put("Cobranza", true);
				permisos.put("Usuario", true);
			}
			//Se pregunta si el rol es cajero
			else if (rol.equals("Cajero")) {
				permisos.put("Caja", true);
				permisos.put("Cobranza", true);
			}
			//Se pregunta si el rol es comprador
			else if (rol.equals("Comprador")) {
				permisos.put("Compra", true);
				permisos.put("Producto", true);
				permisos.put("Proveedor", true);
			}
			//Se pregunta si el rol es vendedor
			else if (rol.equals("Vendedor")) {
				permisos.put("Cliente", true);
				permisos.put("Venta", true);
			}
			else {
				com.google.gwt.user.client.Window.alert("No se encuentra el rol en la base de datos");
				return false;
			}
			
			return true;
		}

		com.google.gwt.user.client.Window.alert("Ocurrio un error interno, no se encontra el rol correspondiente");
		
		return false;
	}
}