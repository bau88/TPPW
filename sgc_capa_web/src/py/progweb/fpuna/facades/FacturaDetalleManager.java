package py.progweb.fpuna.facades;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.FacturaDetalle;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class FacturaDetalleManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<FacturaDetalle> facturadetalles = null;
	
	public FacturaDetalleManager() {
		if (facturadetalles == null) {
			facturadetalles = new ArrayList<FacturaDetalle>();
			FacturaDetalle facturadetalle1 = new FacturaDetalle();
			facturadetalle1.setIdFactDet(1);
			facturadetalle1.setId_producto(1);
			facturadetalle1.setCantidad(10);
			facturadetalle1.setPrecioVenta(1000);
			
						
			FacturaDetalle facturadetalle2 = new FacturaDetalle();
			facturadetalle2.setIdFactDet(2);
			facturadetalle2.setId_producto(2);
			facturadetalle2.setCantidad(5);
			facturadetalle2.setPrecioVenta(500);
			facturadetalles.add(facturadetalle1);
			facturadetalles.add(facturadetalle2);
			
		}
	}
	
	public List<FacturaDetalle> getFacturaDetalles() {
		//alumnos = null;
		
		return this.facturadetalles;
	}
	
	public void addCaja(FacturaDetalle facturadetalle) {
		facturadetalles.add(facturadetalle);
	}
	
	public FacturaDetalle getFacturaDetalles(int cedula) {
		for(FacturaDetalle facturadetalle : facturadetalles) {
			if (facturadetalle.getPK().equals(cedula))
				return facturadetalle;
		}
		
		return null;
	}
	
    public FacturaDetalle buscar(Integer cedula){
		return getFacturaDetalles(cedula);
	}

	public void eliminar(Integer cedula){
		facturadetalles.remove(getFacturaDetalles(cedula));
	}

	public void guardar(FacturaDetalle factura){
		facturadetalles.add(factura);
	}

	public List<FacturaDetalle> listar(){
		return this.facturadetalles;

	}
}
