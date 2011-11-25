package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Rol;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class RolManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Rol> roles = null;
	
	public RolManager() {
		if (roles == null) {
			roles = new ArrayList<Rol>();
			Rol rol1 = new Rol();
			rol1.setId(1);
			rol1.setNombre("Administrador");
			Rol rol2 = new Rol();
			rol2.setId(2);
			rol2.setNombre("Comprador");
			Rol rol3 = new Rol();
			rol3.setId(3);
			rol3.setNombre("Vendedor");
			Rol rol4 = new Rol();
			rol4.setId(4);
			rol4.setNombre("Cajero");
			roles.add(rol1);
			roles.add(rol2);
			roles.add(rol3);
			roles.add(rol4);
		}
	}
	
	public List<Rol> getRoles() {
		//alumnos = null;
		
		return this.roles;
	}
	
	public void addRol(Rol rol) {
		roles.add(rol);
	}
	
	public Rol getRol(int cedula) {
		for(Rol rol : roles) {
			if (rol.getPK().equals(cedula))
				return rol;
		}
		
		return null;
	}
	
        public Rol buscar(Integer cedula){
		return getRol(cedula);
	}

	public void eliminar(Integer cedula){
		roles.remove(getRol(cedula));
	}

	public void guardar(Rol rol){
		roles.add(rol);
	}

	public List<Rol> listar(){
		return this.roles;

	}
}
