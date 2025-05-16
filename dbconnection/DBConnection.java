package dbconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnection {

  
    public static void main(String[] args) {
        Connection connection = MySQLConnection.getConnection();
        if(connection != null){
            System.out.println("Pueden comenzar las operaciones con las base de datos.");
            
            try {
                Statement stmt = connection.createStatement();
                String sql = "SELECT codigo_cliente, nombre_cliente FROM cliente";
                ResultSet rs = stmt.executeQuery(sql);
                // Procesar los resultados.
                System.out.println("Código\tNombre");
                while(rs.next()){
                    int codigo = rs.getInt("codigo_cliente");
                    String nombre = rs.getString("nombre_cliente");
                    System.out.println(codigo + "\t" + nombre);
                }
            } catch (SQLException ex) {
                System.err.println("Error al realizar la consulta: " + ex.getMessage());
            }
            
            
            
        }
    }
    
}
