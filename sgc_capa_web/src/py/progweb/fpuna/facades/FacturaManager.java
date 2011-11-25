package py.progweb.fpuna.facades;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Factura;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class FacturaManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Factura> facturas = null;
	
	public FacturaManager() {
		if (facturas == null) {
			facturas = new ArrayList<Factura>();
			Factura factura1 = new Factura();
			factura1.setId(1);
			factura1.setFecha(new Date());
			factura1.setNumero(50);
			factura1.setPendiente("SI");
			factura1.setSaldo(200);
						
			Factura factura2 = new Factura();
			factura2.setId(2);
			factura2.setFecha(new Date());
			factura2.setNumero(60);
			factura2.setPendiente("NO");
			factura2.setSaldo(0);
			facturas.add(factura1);
			facturas.add(factura2);
			
		}
	}
	
	public List<Factura> getFacturas() {
		//alumnos = null;
		
		return this.facturas;
	}
	
	public void addCaja(Factura factura) {
		facturas.add(factura);
	}
	
	public Factura getFactura(int cedula) {
		for(Factura factura : facturas) {
			if (factura.getPK().equals(cedula))
				return factura;
		}
		
		return null;
	}
	
    public Factura buscar(Integer cedula){
		return getFactura(cedula);
	}

	public void eliminar(Integer cedula){
		facturas.remove(getFactura(cedula));
	}

	public void guardar(Factura factura){
		facturas.add(factura);
	}

	public List<Factura> listar(){
		return this.facturas;

	}
}
