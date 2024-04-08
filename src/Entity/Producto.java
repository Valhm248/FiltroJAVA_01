package Entity;

public class Producto {

    private Integer id;

    private String nombre;

    private Double precio;

    private Integer idTienda;


    public Producto(){}

    public Producto(Integer id, String nombre, Double precio, Integer idTienda) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idTienda = idTienda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", idTienda=" + idTienda +
                '}';
    }
}
