package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginValidator {
    
    public static void main(String[] args) {
        // Solicitar credenciales al usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce tu nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Introduce tu contrase?a: ");
        String password = scanner.nextLine();
        
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            try {
                // Asegurarse de usar la base de datos correcta
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("USE seguridad_db");
                
                // Crear la consulta para validar usuario
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                
                // Mostrar la consulta como se pide en el enunciado
                System.out.println("Consulta SQL a ejecutar: SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
                
                // Preparar y ejecutar la consulta con parámetros
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                
                ResultSet rs = pstmt.executeQuery();
                
                // Validar resultado
                if (rs.next()) {
                    System.out.println("Inicio de sesión correcto.");
                } else {
                    System.out.println("Usuario o contrase?a incorrectos.");
                }
                
                // Cerrar recursos
                rs.close();
                pstmt.close();
                stmt.close();
                connection.close();
                scanner.close();
                
            } catch (SQLException e) {
                System.err.println("Error al ejecutar consulta: " + e.getMessage());
            }
        }
    }
}