package py.progweb.fpuna.entidades;

import java.io.Serializable; 
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="compra")
public class Compra extends EntidadBase implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Date fecha;
	private Integer totalCompra;
	
	//@ManyToMany
	//private List<Producto> productos = new ArrayList<Producto>();
	
	//@ManyToOne
	//private Proveedor proveedor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Integer totalCompra) {
		this.totalCompra = totalCompra;
	}

	/*public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}*/
	
	public Object getPK(){
		return this.id;
	}
	

}
