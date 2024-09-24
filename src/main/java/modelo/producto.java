package modelo;

/**
 *
 * @author renac
 */
public class producto {
    
    private int id;
    private float precio;
    private String tipo;
    private String descripcion;
    private int stock;

    public producto() {
    }

    public producto(int id, float precio, String tipo, String descripcion, int stock) {
        this.id = id;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
