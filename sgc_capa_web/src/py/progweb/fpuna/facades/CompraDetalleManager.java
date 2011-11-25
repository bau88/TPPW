package py.progweb.fpuna.facades;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.CompraDetalle;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class CompraDetalleManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<CompraDetalle> compradetalles = null;
	
	public CompraDetalleManager() {
		if (compradetalles == null) {
			compradetalles = new ArrayList<CompraDetalle>();
			CompraDetalle compradetalle1 = new CompraDetalle();
			compradetalle1.setIdCompDet(1);			
			compradetalle1.setId_producto(1);
			compradetalle1.setCantidad(10);
			compradetalle1.setPrecioCompra(1000);			
						
			CompraDetalle compradetalle2 = new CompraDetalle();
			compradetalle2.setIdCompDet(2);			
			compradetalle2.setId_producto(2);
			compradetalle2.setCantidad(5);
			compradetalle2.setPrecioCompra(500);
			compradetalles.add(compradetalle1);
			compradetalles.add(compradetalle2);
			
		}
	}
	
	public List<CompraDetalle> getCompraDetalles() {
		//alumnos = null;
		
		return this.compradetalles;
	}
	
	public void addCaja(CompraDetalle compradetalle) {
		compradetalles.add(compradetalle);
	}
	
	public CompraDetalle getCompraDetalles(int cedula) {
		for(CompraDetalle compradetalle : compradetalles) {
			if (compradetalle.getPK().equals(cedula))
				return compradetalle;
		}
		
		return null;
	}
	
    public CompraDetalle buscar(Integer cedula){
		return getCompraDetalles(cedula);
	}

	public void eliminar(Integer cedula){
		compradetalles.remove(getCompraDetalles(cedula));
	}

	public void guardar(CompraDetalle compradetalle){
		compradetalles.add(compradetalle);
	}

	public List<CompraDetalle> listar(){
		return this.compradetalles;

	}
}
