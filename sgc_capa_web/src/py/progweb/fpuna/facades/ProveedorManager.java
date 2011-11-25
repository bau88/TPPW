package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Proveedor;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class ProveedorManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Proveedor> proveedores = null;
	
	public ProveedorManager() {
		if (proveedores == null) {
			proveedores = new ArrayList<Proveedor>();
			
			Proveedor proveedor1 = new Proveedor();
			Proveedor proveedor2 = new Proveedor();
			proveedor1.setId(1);
			proveedor1.setNombre("CONTI PARAGUAY");
			proveedor2.setId(2);
			proveedor2.setNombre("ITAIPU BINACIONAL");
			proveedores.add(proveedor1);
			proveedores.add(proveedor2);
			
		}
	}
	
	public List<Proveedor> getProveedores() {
		//alumnos = null;
		
		return this.proveedores;
	}
	
	public void addProveedor(Proveedor proveedor) {
		proveedores.add(proveedor);
	}
	
	public Proveedor getProveedor(int cedula) {
		for(Proveedor proveedor : proveedores) {
			if (proveedor.getPK().equals(cedula))
				return proveedor;
		}
		
		return null;
	}
	
        public Proveedor buscar(Integer cedula){
		return getProveedor(cedula);
	}

	public void eliminar(Integer cedula){
		proveedores.remove(getProveedor(cedula));
	}

	public void guardar(Proveedor proveedor){
		proveedores.add(proveedor);
	}

	public List<Proveedor> listar(){
		return this.proveedores;

	}
}
