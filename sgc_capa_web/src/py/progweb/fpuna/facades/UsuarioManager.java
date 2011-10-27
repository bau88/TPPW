package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Usuario;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class UsuarioManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Usuario> usuarios = null;
	
	public UsuarioManager() {
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
			
			usuarios.add(new Usuario(1,"Bau","Baudelio","12345"));
			usuarios.add(new Usuario(2,"Genio","Rodrigo","12345"));
			usuarios.add(new Usuario(3,"Juani","Juana","12345"));
		}
	}
	
	public List<Usuario> getUsuarios() {
		//alumnos = null;
		
		return this.usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Usuario getUsuario(int cedula) {
		for(Usuario usuario : usuarios) {
			if (usuario.getPK().equals(cedula))
				return usuario;
		}
		
		return null;
	}
	
        public Usuario buscar(Integer cedula){
		return getUsuario(cedula);
	}

	public void eliminar(Integer cedula){
		usuarios.remove(getUsuario(cedula));
	}

	public void guardar(Usuario usuario){
		usuarios.add(usuario);
	}

	public List<Usuario> listar(){
		return this.usuarios;

	}
}
