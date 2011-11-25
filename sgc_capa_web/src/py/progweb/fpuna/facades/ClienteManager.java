package py.progweb.fpuna.facades;

import java.util.ArrayList;
import java.util.List;
import py.progweb.fpuna.entidades.Cliente;

/**
 * Clase encargada de manipular datos relacionados a Alumnos
 * @author jtalavera
 *
 */
public class ClienteManager {

	//Datos de prueba .... aun no conecta a base de datos
	private static ArrayList<Cliente> clientes = null;
	
	public ClienteManager() {
		if (clientes == null) {
			clientes = new ArrayList<Cliente>();
			Cliente cliente1 = new Cliente();
			cliente1.setId(1);
			cliente1.setNombre("Cliente1");
			Cliente cliente2 = new Cliente();
			cliente2.setNombre("Cliente2");
			cliente2.setId(2);
			clientes.add(cliente1);
			clientes.add(cliente2);
		}
	}
	
	public List<Cliente> getClientes() {
		//alumnos = null;
		
		return this.clientes;
	}
	
	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public Cliente getCliente(int cedula) {
		for(Cliente cliente : clientes) {
			if (cliente.getPK().equals(cedula))
				return cliente;
		}
		
		return null;
	}
	
        public Cliente buscar(Integer cedula){
		return getCliente(cedula);
	}

	public void eliminar(Integer cedula){
		clientes.remove(getCliente(cedula));
	}

	public void guardar(Cliente cliente){
		clientes.add(cliente);
	}

	public List<Cliente> listar(){
		return this.clientes;

	}
}
