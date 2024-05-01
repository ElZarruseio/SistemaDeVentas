package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con;

    public Connection getConnection() {
        // try_catch para capturar las ecxepciones
        try {
            // variable tipo string myBD
            String myBD = "jbdc:mysql://localhost:3306/sistemaventas?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", ""); // se hace conexion con un usur y pass y se usa la avriable myBD 
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }
}
