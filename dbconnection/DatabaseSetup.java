package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    
    public static void main(String[] args) {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            try {
                // Crear la base de datos seguridad_db
                System.out.println("Creando base de datos seguridad_db...");
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS seguridad_db");
                System.out.println("Base de datos creada correctamente.");
                
                // Usar la base de datos
                stmt.executeUpdate("USE seguridad_db");
                
                // Crear la tabla users
                System.out.println("Creando tabla users...");
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50) NOT NULL, password VARCHAR(100) NOT NULL)";
                stmt.executeUpdate(createTableSQL);
                System.out.println("Tabla creada correctamente.");
                
                // Insertar usuarios con PreparedStatement
                System.out.println("Insertando usuarios...");
                String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(insertSQL);
                
                // Insertar primer usuario
                pstmt.setString(1, "admin");
                pstmt.setString(2, "admin123");
                pstmt.executeUpdate();
                
                // Insertar segundo usuario
                pstmt.setString(1, "usuario");
                pstmt.setString(2, "pass123");
                pstmt.executeUpdate();
                
                System.out.println("Usuarios insertados correctamente.");
                
                // Cerrar recursos
                pstmt.close();
                stmt.close();
                connection.close();
                
            } catch (SQLException e) {
                System.err.println("Error al ejecutar consultas SQL: " + e.getMessage());
            }
        }
    }
}