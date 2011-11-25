package py.progweb.fpuna.entidades;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Rol extends EntidadBase implements Serializable {
	 private static final long serialVersionUID = 1L; 
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 
	 private String nombre;
	 
	 //@ManyToMany(mappedBy="roles")
	 //private List<Usuario> usuarios = new ArrayList<Usuario>();
	 

	 public Integer getId() {
		return id;
	}
	/*public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}*/
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Object getPK(){
		return this.id;
	}

}
