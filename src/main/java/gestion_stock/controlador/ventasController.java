/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_stock.controlador;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.venta;

/**
 *
 * @author renac
 */
public class ventasController {
    Connection conn = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    PreparedStatement ps5 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs4 = null;
    ResultSet rs5 = null;
    DefaultTableModel modelo;
    
    public ArrayList llenarComboProductos(String tipo){
        conn = conexion.ConnectDB();
        String sql="SELECT descripcion, precio FROM Productos where tipo= ? AND hora_borrado is NULL;";
        ArrayList<Object[]> listaDatos = new ArrayList<>(); // Lista sin genéricos
        
        
        try{
            ps = conn.prepareStatement(sql);
           
            ps.setString(1, tipo);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                Object[] datos = new Object[2]; // Array de 2 elementos
                datos[0] = rs.getString("descripcion");
                datos[1] = rs.getFloat("precio");
                listaDatos.add(datos); // Agrega el array de datos a la lista
            }

 
            ps.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
            
            
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);//Esto me muestra una ventana con un mensaje de error en caso de falla
        }
        
        return listaDatos; // Devuelve la lista
    }
    
    public float traerPrecioUnitario(String tipo, String descripcion){
        conn = conexion.ConnectDB();
        String sql="SELECT precio FROM Productos where tipo= ? AND descripcion=?;";
        float precio = -1; // Valor por defecto para indicar que no se encontró el precio
        
       
        try{
            ps1 = conn.prepareStatement(sql);
           
            ps1.setString(1, tipo);
            ps1.setString(2, descripcion);

            rs1 = ps1.executeQuery();
        
            // Verificamos si hay resultados
            if (rs1.next()) {
                precio = rs1.getFloat("precio"); // Obtener el precio
            }
 
            ps1.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
            
            
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);//Esto me muestra una ventana con un mensaje de error en caso de falla
        }
        
        return precio;
        
    }
    
    public void guardarCompra(venta venta){
        Connection conn = conexion.ConnectDB();  // Conectar a la base de datos
        String sql = "INSERT INTO Historial(producto_id, tipo, cantidad, fecha, precio_unitario, precio_total, cliente) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps2 = conn.prepareStatement(sql);

            
            ps2.setInt(1, venta.getProducto_id());
            ps2.setString(2, venta.getTipo());
            ps2.setInt(3, venta.getCantidad());
            ps2.setString(4, venta.getFecha());
            ps2.setInt(5, (int) venta.getPrecio_unitario());
            ps2.setInt(6, venta.getPrecio_total());
            ps2.setString(7, venta.getCliente());

            // Ejecutar la consulta
            ps2.execute();

            // Cerrar las conexiones
            ps2.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Venta realizada correctamente. Precio Total: "+(venta.getCantidad() * venta.getPrecio_unitario()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);  // Mostrar error en caso de fallo
        }
    }
    
    
    public int buscar_id_producto(String tipo, String producto){
        conn = conexion.ConnectDB();
        String sql="SELECT id FROM Productos where tipo= ? AND descripcion=?;";
        int id = 0;
       
        try{
            PreparedStatement ps3 = conn.prepareStatement(sql);
           
            ps3.setString(1, tipo);
            ps3.setString(2, producto);

            ResultSet rs2 = ps3.executeQuery();
        
            // Verificamos si hay resultados
            if (rs2.next()) {
                id = rs2.getInt("id"); // Obtener el id de producto
            }
 
            ps3.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
            
            
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);//Esto me muestra una ventana con un mensaje de error en caso de falla
        }
        
        return id;
        
    }
    
    public int buscarStock(int id){
        conn = conexion.ConnectDB();
        String sqlStock="SELECT stock FROM Productos where id = ?;";
        int stockDisponible = 0;
       
        try{
            ps4 = conn.prepareStatement(sqlStock);
            
            ps4.setInt(1, id);
            
            rs4 = ps4.executeQuery();
        
            // Verificamos si hay resultados
            if (rs4.next()) {
                stockDisponible = rs4.getInt("stock"); 
            }
            
             ps4.close(); 
            conn.close();
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return stockDisponible;
    }
    
    public void actualizarStock (int nuevoStock, int id){
        conn = conexion.ConnectDB();
        String sqlStockNuevo ="UPDATE Productos SET stock = ? WHERE id = ?;";
        
        try{
            ps5 = conn.prepareStatement(sqlStockNuevo);
            
            ps5.setInt(1, nuevoStock);  // Establecemos el nuevo stock
        ps5.setInt(2, id);  // Establecemos el id del producto

            ps5.execute();
        
  
            
             ps5.close(); 
            conn.close();
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
