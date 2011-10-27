package py.progweb.fpuna.client.abm;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.fpuna.pweb.client.RolService;
//import com.fpuna.pweb.client.RolServiceAsync;
import py.progweb.fpuna.client.Sgc_capa_web;
import py.progweb.fpuna.client.services.UsuarioService;

import py.progweb.fpuna.client.services.UsuarioServiceAsync;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;   
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;   
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;   
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;   
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

//import entidades.Rol;
import py.progweb.fpuna.entidades.*;

public class UsuarioDetalle extends Canvas {

	public UsuarioDetalle(final Sgc_capa_web mainWindow) {
		this(null, mainWindow);
	}
	
	public UsuarioDetalle(Usuario usuario, final Sgc_capa_web mainWindow) {
		
		VLayout layout = new VLayout(10);
		
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
        label.setContents("<div style='color:black;font-size:15'><b>Detalles de Usuario</b></div>");
        
        TextItem codigoText = new TextItem("codigo");
        codigoText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        codigoText.setTitle("Codigo");
        codigoText.setDisabled(true);
        codigoText.setWrapTitle(false);
  
        TextItem nombreText = new TextItem("nombre");
        nombreText.setTitle("Nombre");
        nombreText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        nombreText.setWrapTitle(false);

        TextItem nombreusuarioText = new TextItem("nombreusuario");
        nombreusuarioText.setTitle("Nombre de Usuario");
        nombreusuarioText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        nombreusuarioText.setWrapTitle(false);
        
        PasswordItem pwdText = new PasswordItem("pwd");
        pwdText.setTitle("Password");
        pwdText.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        pwdText.setWrapTitle(false);

        final Map<String, String> rol = new HashMap<String, String>();
        
        final SelectItem rolItem = new SelectItem();  
        rolItem.setName("rol");
        rolItem.setTitle("Rol");
        rolItem.setTitleStyle("{font-color:white;font-weigh:bold;font-size:14}");
        rolItem.setAddUnknownValues(false);

        /* Para cargar el comboBox de Roles */
        
       // RolServiceAsync service = GWT.create(RolService.class);	

		/*try {
			Rol rol_listar = new Rol();
			String orden = "idRol";
			service.listar(rol_listar, orden, new AsyncCallback<List<Rol>>() {
				@Override
				public void onSuccess(List<Rol> result) {
					String [] r = new String [result.size()];
					for(int f = 0; f < result.size(); f++) {
						if(result.get(0)!=null){
							rol.put(result.get(f).getNombre(), new String ("" + result.get(f).getIdRol()));
							r[f] = new String(result.get(f).getNombre());
						}
					}
					rolItem.setValueMap(r);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
				}
			});
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
      
        ButtonItem button = new ButtonItem("save", "Guardar");
        button.setStartRow(false);
        button.setWidth(80);
        button.setIcon("approve.png");
        button.addClickHandler(new ClickHandler() {
        	//@Override
            public void onClick(ClickEvent event) {
        		
        		UsuarioServiceAsync service = GWT.create(UsuarioService.class);
        		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
			serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()+ "usuarioService");

				final Usuario usuario = new Usuario();
				//final Rol retorno = null;
				usuario.setNombre(form.getValueAsString("nombre"));
				usuario.setNombreusuario(form.getValueAsString("nombreusuario"));
				usuario.setContrasenha(form.getValueAsString("pwd"));
								
				if(form.getValueAsString("codigo") != null){
					//usuario.setIdUsuario(Integer.valueOf(form.getValueAsString("codigo")));
					usuario.setIdusuario(Integer.valueOf(form.getValueAsString("codigo")));
				}

				try { 
					//usuario.setRol(retorno);
					String aux = rol.get(rolItem.getValue());
					service.guardar(usuario,  new AsyncCallback<Void>() {

						//@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocurrio un error: " + caught.getClass().getName() + " " + caught.getMessage()) ;
						}

						//@Override
						public void onSuccess(Void result) {
							new ListaUsuarios(mainWindow);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});

        
		if (usuario != null){
			codigoText.setDefaultValue(String.valueOf(usuario.getIdusuario()));
			nombreText.setDefaultValue(usuario.getNombre());
			nombreusuarioText.setDefaultValue(usuario.getNombreusuario());
			//pwdText.setDefaultValue(usuario.getContrasenha());
			//rolItem.setValue(usuario.getRol().getNombre());
		}
		
				
		form.setFields(codigoText, nombreText, nombreusuarioText, pwdText, rolItem, button);
		layout.addMember(label);
        layout.addMember(form);
        mainWindow.showDialog(layout);
	}
}
