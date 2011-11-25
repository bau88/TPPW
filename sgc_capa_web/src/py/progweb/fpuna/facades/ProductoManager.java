package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Producto;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class ProductoManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Producto> productos = null;
	
	public ProductoManager() {
		if (productos == null) {
			productos = new ArrayList<Producto>();
			Producto producto1= new Producto();
			producto1.setId(1);
			producto1.setDescripcion("Cola de sapatero");
			producto1.setCantidad(5);
			producto1.setPrecio(5000.0);
			producto1.setPorcganancia(3.0);
			Producto producto2= new Producto();
			producto2.setId(2);
			producto2.setDescripcion("Craquele");
			producto2.setCantidad(10);
			producto2.setPorcganancia(10.0);
			producto2.setPrecio(3000.0);
			productos.add(producto1);
			productos.add(producto2);
			
		}
	}
	
	public List<Producto> getProductos() {
		//alumnos = null;
		
		return this.productos;
	}
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}
	
	public Producto getProducto(int cedula) {
		for(Producto producto : productos) {
			if (producto.getPK().equals(cedula))
				return producto;
		}
		
		return null;
	}
	
        public Producto buscar(Integer cedula){
		return getProducto(cedula);
	}

	public void eliminar(Integer cedula){
		productos.remove(getProducto(cedula));
	}

	public void guardar(Producto producto){
		productos.add(producto);
	}

	public List<Producto> listar(){
		return this.productos;

	}
}
