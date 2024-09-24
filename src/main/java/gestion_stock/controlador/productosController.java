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
import modelo.producto;


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
        String sql="SELECT id, tipo, descripcion, precio, stock FROM Productos where hora_borrado IS NULL;";
        
        try{
            ps = conn.prepareStatement(sql);
            Object[] datos = new Object[5];
            modelo = (DefaultTableModel) tabla.getModel();

            rs = ps.executeQuery();
            while(rs.next()){ //Esto hace que se ejecute mientras existan filas
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("tipo");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getFloat("precio");
                datos[4] = rs.getInt("stock");
                
                modelo.addRow(datos);
            }
            
            tabla.setModel(modelo); //Esto agrega todos los datos en la tabla???
 
            ps.close(); //cerrar la tabla
            conn.close(); //cerrar la BD
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);//Esto me muestra una ventana con un mensaje de error en caso de falla
        }
    }
    
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

    
    
    public void modificarProducto(producto prod) {
        Connection conn = null;
        PreparedStatement ps2 = null;
        
        
        try {
            conn = conexion.ConnectDB();
            String sqlModificado = "UPDATE Productos SET tipo=?, descripcion=?, precio=? WHERE id=?";
            ps2 = conn.prepareStatement(sqlModificado);


            ps2.setString(1, prod.getTipo());
            ps2.setString(2, prod.getDescripcion());
            ps2.setFloat(3, prod.getPrecio());
            ps2.setInt(4, prod.getId());

            ps2.execute();
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps2 != null) ps2.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
