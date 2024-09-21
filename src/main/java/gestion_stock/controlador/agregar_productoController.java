/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_stock.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import modelo.producto;

public class agregar_productoController {

    Connection conn = null;
    PreparedStatement ps = null;
    producto prod = new producto();

    // Método para insertar un producto en la tabla Productos
    public void insertarProducto(producto prod) {
        conn = conexion.ConnectDB();  // Conectar a la base de datos
        String sql = "INSERT INTO Productos(tipo, descripcion, precio) VALUES(?, ?, ?)";
        
        try {
            ps = conn.prepareStatement(sql);

            // Configurar los valores de la consulta
            System.out.println("tipo "+prod.getTipo());
            
            ps.setString(1, prod.getTipo());
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecio());

            // Ejecutar la consulta
            ps.execute();

            // Cerrar las conexiones
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);  // Mostrar error en caso de fallo
        }
    }
}

