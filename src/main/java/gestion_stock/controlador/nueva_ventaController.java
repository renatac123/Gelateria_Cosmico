/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_stock.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class nueva_ventaController {

    Connection conn = null;
    PreparedStatement ps = null;

    // Método para insertar un producto en la tabla Productos
    public void nuevaVenta(String inventario_id, double cantidad, double precio_total, String cliente) {
        conn = conexion.ConnectDB();  // Conectar a la base de datos
        String sql = "INSERT INTO Historial(fecha, inventario_id, cantidad, precio_total, cliente) VALUES(?, ?, ?, ?, ?)";
        
        try {
            ps = conn.prepareStatement(sql);

            // Configurar los valores de la consulta
            System.out.println("Productos "+ inventario_id);
            
            ps.setString(1, inventario_id);
            ps.setDouble(2, cantidad);
            ps.setDouble(3, precio_total);
            ps.setString(3, cliente);

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

