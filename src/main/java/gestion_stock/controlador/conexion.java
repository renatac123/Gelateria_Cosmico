/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_stock.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author renac
 */

public class conexion {
    
    Connection conn = null;
    public static Connection ConnectDB(){
        try {
            //cargar el driver
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/renac/OneDrive/Documentos/gestion_stock.db");

            if (conn != null){
                    System.out.println("Conexión a base de datos ... Ok");
                    return conn;
            }else{
                System.out.println("Conexión a base de datos: problemas!");
                return null;
            }
        }
        catch (SQLException a){
           System.out.println(a);
           return null;
        }
        catch (ClassNotFoundException ex){
           System.out.println(ex);
           return null;
        }
        catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    
}