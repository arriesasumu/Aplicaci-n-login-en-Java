package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection getConnection(){
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "root";
        
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa  a las base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e){
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
       return connection;
    }
}
