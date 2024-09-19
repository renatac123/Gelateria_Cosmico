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


/**
 *
 * @author renac
 */
public class productosController {
 
    
    Connection conn = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    DefaultTableModel modelo;
    
    public void consultarLista(JTable tabla){
        conn = conexion.ConnectDB();
        String sql="SELECT id, tipo, descripcion, precio FROM Productos where hora_borrado IS NULL;";
        
        try{
            ps = conn.prepareStatement(sql);
            Object[] datos = new Object[4];
            modelo = (DefaultTableModel) tabla.getModel();

            rs = ps.executeQuery();
            while(rs.next()){ //Esto hace que se ejecute mientras existan filas
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("tipo");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getFloat("precio");
                
                modelo.addRow(datos);
            }
            
            tabla.setModel(modelo); //Esto agrega todos los datos en la tabla???
 
            ps.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);//Esto me muestra una ventana con un mensaje de error en caso de falla
        }
    }
    
    
    /*
    public void borrarProducto(String id){ //throws ParseException
        conn = conexion.ConnectDB();
        String sqlBorrado ="UPDATE Productos SET hora_borrado=? WHERE id=?";

    
        try{
            ps1 = conn.prepareStatement(sqlBorrado);
               
           
            //Averiguo la fecha actual
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); //Esto es para definir el formato que quiero
            String fecha_actual  = dtf.format(LocalDateTime.now()); //Aca convierto la fecha en string, ademas de darle el formato a la fecha actual
            
            //EN LA BD LAS FECHAS SON STRING, ASI QUE LAS CONVIERTO EN DATE PARA REALIZAR OPERACIONES
            //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//DEFINO EL FORMATO EN QUE SE GUARDARAN LAS FECHAS
            //Date baja_logica = formato.parse(fecha_actual);
            
            System.out.println("fecha "+fecha_actual.getClass().getName());
            System.out.println("id "+id.getClass().getName());
            
            ps1.setString(1, fecha_actual);
            ps1.setString(2, id);
            
            System.out.println("flag1");
            
            ps1.executeUpdate();
            System.out.println("flag1");
            //rs1 = ps1.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto borrado correctamente");
            ps1.close();
            conn.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            // Cerrar recursos en un bloque finally para garantizar que se liberen
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
    public void borrarProducto(String id) {
        Connection conn = null;
        PreparedStatement ps1 = null;

        try {
            conn = conexion.ConnectDB();
            String sqlBorrado = "UPDATE Productos SET hora_borrado=? WHERE id=?";
            ps1 = conn.prepareStatement(sqlBorrado);

            // Obtener la fecha actual
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            String fecha_actual = dtf.format(LocalDateTime.now());

            ps1.setString(1, fecha_actual);
            ps1.setString(2, id);

            ps1.execute();
            JOptionPane.showMessageDialog(null, "Producto borrado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps1 != null) ps1.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    
    
    public void modificarProducto(String id) {
        Connection conn = null;
        PreparedStatement ps2 = null;
        
        /*
        try {
            conn = conexion.ConnectDB();
            String sqlBorrado = "UPDATE Productos SET hora_borrado=? WHERE id=?";
            ps1 = conn.prepareStatement(sqlBorrado);

            // Obtener la fecha actual
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            String fecha_actual = dtf.format(LocalDateTime.now());

            ps1.setString(1, fecha_actual);
            ps1.setString(2, id);

            ps1.execute();
            JOptionPane.showMessageDialog(null, "Producto borrado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps1 != null) ps1.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }*/
    }


    
}


/*
 public void consultarTodo(JTable tabla){
        conn = conexion.ConnectDB();
        String sql="SELECT v.id_vehiculo, v.patente, v.tipo_vehiculo, v.propietario, v_e.id_estacionamiento, v_e.hora_entrada, v_e.hora_salida, v_e.precio_pagado FROM vehiculo2 AS v INNER JOIN vehiculo_estacionamiento as v_e ON v.id_vehiculo=v_e.id_vehiculo;";
        
        try{
            ps = conn.prepareStatement(sql);
            Object[] datos = new Object[8];
            modelo = (DefaultTableModel) tabla.getModel();

            rs = ps.executeQuery();
            while(rs.next()){ //Esto hace que se ejecute mientras existan filas
                datos[0] = rs.getInt("id_vehiculo");
                datos[1] = rs.getString("patente");
                datos[2] = rs.getString("tipo_vehiculo");
                datos[3] = rs.getString("propietario");
                datos[4] = rs.getInt("id_estacionamiento");
                datos[5] = rs.getString("hora_entrada");
                if(rs.getString("hora_salida") == null && rs.getString("precio_pagado") == null){
                    datos[6] = "Aun en parking";
                    datos[7] = "Aun en parking";
                }else{
                    datos[6] = rs.getString("hora_salida");
                    datos[7] = "$"+rs.getString("precio_pagado");
                }
                
                modelo.addRow(datos);
            }
            
            tabla.setModel(modelo); //Esto agrega todos los datos en la tabla???
                        
            ps.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
*/