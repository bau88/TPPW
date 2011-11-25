package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import py.progweb.fpuna.entidades.Pago;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class CobranzaManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Pago> cobranzas = null;
	
	public CobranzaManager() {
		if (cobranzas == null) {
			cobranzas = new ArrayList<Pago>();
			Pago cobranza1 = new Pago();
			cobranza1.setId(1);
			cobranza1.setMonto(50000);
			cobranza1.setFechacierre(new Date());
			cobranza1.setCerrado("SI");
			
			Pago cobranza2 = new Pago();
			cobranza2.setId(2);
			cobranza2.setMonto(55000);
			cobranza2.setFechacierre(new Date());
			cobranza2.setCerrado("SI");
			
			cobranzas.add(cobranza1);
			cobranzas.add(cobranza2);
		}
	}
	
	public List<Pago> getCobranzas() {
		//alumnos = null;
		
		return this.cobranzas;
	}
	
	public void addCobranza(Pago cobranza) {
		cobranzas.add(cobranza);
	}
	
	public Pago getCobranza(int cedula) {
		for(Pago cobranza : cobranzas) {
			if (cobranza.getPK().equals(cedula))
				return cobranza;
		}
		
		return null;
	}
	
        public Pago buscar(Integer cedula){
		return getCobranza(cedula);
	}

	public void eliminar(Integer cedula){
		cobranzas.remove(getCobranza(cedula));
	}

	public void guardar(Pago cobranza){
		cobranzas.add(cobranza);
	}

	public List<Pago> listar(){
		return this.cobranzas;

	}
}
