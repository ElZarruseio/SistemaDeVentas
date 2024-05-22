package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProveedorDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProveedor(Proveedor pr) {
        // Definimos la sentencia SQL para insertar un nuevo registro en la tabla proveedor
        String sql = "INSERT INTO proveedor (ruc, nombre, telefono, direccion, razon) VALUES (?, ?, ?, ?, ?)";

        try {
            // Reemplazamos los placeholders en la sentencia SQL con los valores del objeto Proveedor
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());

            // Ejecutamos la sentencia SQL para insertar el nuevo registro
            ps.execute();
            //se devuelve el true para confirmar que la operacion fue exitosa
            return true;
        } catch (SQLException e) {
            // Si ocurre una excepción, mostramos un mensaje de error y devolvemos false
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            // Cerramos la conexión con la base de datos, incluso si ocurrió una excepción
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarProveedor() {
        // Crea una nueva lista de objetos Proveedor
        List<Proveedor> ListaPr = new ArrayList();

        // Define la consulta SQL para seleccionar todos los registros de la tabla proveedor
        String sql = "SELECT * FROM proveedor ";

        // Intenta establecer una conexión a la base de datos y ejecutar la consulta SQL
        try {
            // Establece una conexión a la base de datos
            con = cn.getConnection();

            // Prepara la consulta SQL para su ejecución
            ps = con.prepareStatement(sql);

            // Ejecuta la consulta SQL y almacena el resultado en el objeto rs
            rs = ps.executeQuery();

            // Recorre cada registro devuelto por la consulta SQL
            while (rs.next()) {
                // Crea un nuevo objeto Proveedor
                Proveedor pr = new Proveedor();

                // Establece las propiedades del objeto Proveedor utilizando los valores de los campos correspondientes en el registro actual
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getInt("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));

                // Agrega el objeto Proveedor a la lista ListaPr
                ListaPr.add(pr);

            }
        } catch (SQLException e) {

            // Imprime un mensaje de error si se produce una excepción durante la ejecución del código
            System.out.println(e.toString());
        }

        // Devuelve la lista de objetos Proveedor
        return ListaPr;
    }
}
