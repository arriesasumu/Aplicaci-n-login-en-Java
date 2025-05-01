package db_creation;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DB_Creation {
    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "arries2016";

    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        String sql;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente.");

            // Conectar a la base de datos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexión correcta.");

            // Crear el statement ahora que connection no es null
            stmt = connection.createStatement();

            // Eliminar la base de datos si existe
            sql = "DROP DATABASE IF EXISTS seguridad_db";
            stmt.executeUpdate(sql);
            System.out.println("Base de datos seguridad_db eliminada.");

            // Crear la base de datos
            sql = "CREATE DATABASE seguridad_db";
            stmt.executeUpdate(sql);
            System.out.println("Base de datos creada.");

            // Seleccionar la base de datos
            sql = "USE seguridad_db";
            stmt.executeUpdate(sql);
            System.out.println("Base de datos seguridad_db seleccionada.");

            // Crear la tabla users
            sql = "CREATE TABLE users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50) NOT NULL, "
                + "password VARCHAR(100) NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Tabla users creada.");

            // Insertar registros
            sql = "INSERT INTO users (username, password) "
                + "VALUES ('admin', 'admin123'), "
                + "('usuario', 'pass123')";
            stmt.executeUpdate(sql);
            System.out.println("Registros insertados.");

        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar un SQL: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }
}



   
            
        
    

