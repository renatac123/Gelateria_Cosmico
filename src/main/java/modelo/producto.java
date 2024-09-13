package modelo;

/**
 *
 * @author renac
 */
public class producto {
    
    private int id;
    private int precio;
    private String tipo;
    private String descripcion;

    public producto() {
    }

    public producto(int id, int precio, String tipo, String descripcion) {
        this.id = id;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
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
    
    
    
}
