package py.progweb.fpuna.facades;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Compra;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class CompraManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Compra> compras = null;
	
	public CompraManager() {
		if (compras == null) {
			compras = new ArrayList<Compra>();
			Compra compra1 = new Compra();
			compra1.setId(1);
			compra1.setFecha(new Date());
			compra1.setTotalCompra(1200000);			
			Compra compra2 = new Compra();
			compra2.setId(2);
			compra2.setFecha(new Date());
			compra2.setTotalCompra(500000);
			compras.add(compra1);
			compras.add(compra2);
			
		}
	}
	
	public List<Compra> getCompras() {
		//alumnos = null;
		
		return this.compras;
	}
	
	public void addCaja(Compra compra) {
		compras.add(compra);
	}
	
	public Compra getCompra(int cedula) {
		for(Compra compra : compras) {
			if (compra.getPK().equals(cedula))
				return compra;
		}
		
		return null;
	}
	
    public Compra buscar(Integer cedula){
		return getCompra(cedula);
	}

	public void eliminar(Integer cedula){
		compras.remove(getCompra(cedula));
	}

	public void guardar(Compra compra){
		compras.add(compra);
	}

	public List<Compra> listar(){
		return this.compras;

	}
}
