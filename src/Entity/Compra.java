package Entity;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Compra {

    private Integer id;

    private Integer idCliente;

    private Integer idProducto;

    private String fechaCompra;

    private Integer cantidad;

    public Compra(){}

    public Compra(Integer id, Integer idCliente, Integer idProducto, String fechaCompra, Integer cantidad) {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
    }

    public Integer setIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idProducto=" + idProducto +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }


}
