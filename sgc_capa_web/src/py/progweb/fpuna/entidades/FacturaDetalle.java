package py.progweb.fpuna.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the factura_detalle database table.
 * 
 */
@Entity
@Table(name="factura_detalle")
public class FacturaDetalle extends EntidadBase  implements Serializable{
	private static final long serialVersionUID = 1L;

	/*@TableGenerator(name="secuencia_fact_det", table="secuencia",
            pkColumnName="secuencia_tabla", valueColumnName="secuencia_numero",
            pkColumnValue="fact_det",
            initialValue=0,
            allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="secuencia_fact_det")
	@Column(name="id_fact_det")*/
	private Integer id;

	private Integer cantidad;

	@Column(name="precio_venta")
	private Double precioVenta;

	//bi-directional many-to-one association to Factura
   /* @ManyToOne
	@JoinColumn(name="id_factura")
	private Factura factura;*/

	//bi-directional many-to-one association to Producto
    /*@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;*/

    
    @Transient
    private Integer id_producto;
    
    public FacturaDetalle() {
    }

	public Integer getIdFactDet() {
		return this.id;
	}

	public void setIdFactDet(Integer idFactDet) {
		this.id = idFactDet;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	/*public Factura getFactura() {
		return this.factura;
	}*/

	/*public void setFactura(Factura factura) {
		this.factura = factura;
	}
	*/
	/*public Producto getProducto() {
		return this.producto;
	}*/

	/*public void setProducto(Producto producto) {
		this.producto = producto;
	}*/

	@Override
	public Object getPK() {
		// TODO Auto-generated method stub
		return this.getIdFactDet();
	}

	/*public FacturaDetalle(Factura factura) {
		//super();
		this.factura = factura;
	}*/

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getId_producto() {
		return id_producto;
	}
	
}