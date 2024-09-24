/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_stock.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.venta;

/**
 *
 * @author Caro
 */
public class historialController {
    Connection conn = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    DefaultTableModel modelo;
    
    public void mostrarHistorial(JTable tabla){
        conn = conexion.ConnectDB();
        String sql="SELECT id, producto_id, tipo, cantidad, fecha, cliente, precio_total FROM Historial;";
        String sqlBuscarProd = "Select descripcion FROM Productos WHERE id=?";
        
        try{
            ps = conn.prepareStatement(sql);
            Object[] datos = new Object[7];
            modelo = (DefaultTableModel) tabla.getModel();

            rs = ps.executeQuery();
            while(rs.next()){ //Esto hace que se ejecute mientras existan filas
                //datos[0] = rs.getString("id");

                ps1 = conn.prepareStatement(sqlBuscarProd);
                //Busco la descripcion del producto, ya que solo cuento con el id de la tabla Productos
                String producto_id = rs.getString("producto_id");
                
                ps1.setString(1,producto_id);
                
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    datos[0] = rs1.getString("descripcion");
                }
                
                datos[1] = rs.getString("tipo");
                datos[2] = rs.getString("fecha");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("cliente");
                datos[5] = rs.getString("precio_total");
                
                modelo.addRow(datos);
            }
            
            tabla.setModel(modelo); //Esto agrega todos los datos en la tabla???
 
            ps.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);//Esto me muestra una ventana con un mensaje de error en caso de falla
        }
    }
    

}
