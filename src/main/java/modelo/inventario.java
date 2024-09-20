package modelo;

/**
 *
 * @author renac
 */
public class inventario {
    
    private int id;
    private int precio;
    private String producto_id;
    private String descripcion;

    public inventario() {
    }

    public inventario(int id, int precio, String producto_id, String descripcion) {
        this.id = id;
        this.precio = precio;
        this.producto_id = producto_id;
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

    public String getProductoId() {
        return producto_id;
    }

    public void setProductoId(String tipo) {
        this.producto_id = producto_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
