package py.progweb.fpuna.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the compra_detalle database table.
 * 
 */
@Entity
@Table(name="compra_detalle")
public class CompraDetalle extends EntidadBase implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@TableGenerator(name="secuencia_comp_det", table="secuencia",
            pkColumnName="secuencia_tabla", valueColumnName="secuencia_numero",
            pkColumnValue="comp_det",
            initialValue=0,
            allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="secuencia_comp_det")
	@Column(name="id_comp_det")*/
	private Integer idCompDet;

	private Integer cantidad;

	@Column(name="precio_compra")
	private Double precioCompra;

	//bi-directional many-to-one association to Compra
   /* @ManyToOne
	@JoinColumn(name="id_compra", nullable=false)
	private Compra compra;*/

	//bi-directional many-to-one association to Producto
    /*@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;*/
    
    @Transient
    private Integer id_producto;
    
    public CompraDetalle() {
    
    }
    /*public CompraDetalle(Compra compra) {
    	this.compra = compra;
    }*/

	public Integer getIdCompDet() {
		return this.idCompDet;
	}

	public void setIdCompDet(Integer idCompDet) {
		this.idCompDet = idCompDet;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	/*public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}*/
	
	/*public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}*/

	@Override
	public Object getPK() {
		// TODO Auto-generated method stub
		return this.getIdCompDet();
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	
}