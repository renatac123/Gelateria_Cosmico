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

/**
 *
 * @author renac
 */
public class ventasController {
    Connection conn = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    DefaultTableModel modelo;
    
    public ArrayList llenarComboProductos(String tipo){
        conn = conexion.ConnectDB();
        String sql="SELECT descripcion, precio FROM Productos where tipo= ?;";
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
}
