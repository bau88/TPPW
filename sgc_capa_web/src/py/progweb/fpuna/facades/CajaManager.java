package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Caja;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class CajaManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Caja> cajas = null;
	
	public CajaManager() {
		if (cajas == null) {
			cajas = new ArrayList<Caja>();
			Caja caja1 = new Caja();
			caja1.setIdcaja(1);
			caja1.setDescripcion("Caja 1");
			Caja caja2 = new Caja();
			caja2.setIdcaja(2);
			caja2.setDescripcion("Caja 2");
			cajas.add(caja1);
			cajas.add(caja2);
			
		}
	}
	
	public List<Caja> getCajas() {
		//alumnos = null;
		
		return this.cajas;
	}
	
	public void addCaja(Caja caja) {
		cajas.add(caja);
	}
	
	public Caja getCaja(int cedula) {
		for(Caja caja : cajas) {
			if (caja.getPK().equals(cedula))
				return caja;
		}
		
		return null;
	}
	
        public Caja buscar(Integer cedula){
		return getCaja(cedula);
	}

	public void eliminar(Integer cedula){
		cajas.remove(getCaja(cedula));
	}

	public void guardar(Caja caja){
		cajas.add(caja);
	}

	public List<Caja> listar(){
		return this.cajas;

	}
}
