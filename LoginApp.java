package loginapp;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;

public class LoginApp {

    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/seguridad_db";
        String dbUser = "root";
        String dbPassword = "arries2016";
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        Statement stmt = null;
        String sql = "";
        ResultSet rs;
        boolean succesful = false;

        try {
            // Solicitar datos al usuario.
            System.out.print("Ingrese su nombre de usuario: ");
            username = scanner.nextLine();
            
            System.out.print("Ingrese su contraseña: ");
            password = scanner.nextLine();

            // Cargar el driver de MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Conectarse al SGBD (MySQL).
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("Conexión realizada al SGBD(MySQL).");

            // Consulta SQL para validar al usuario.
            stmt = connection.createStatement();
            sql = "select * from users where username = '" + username + "' " +
                  "and password = '" + password + "'";
            System.out.println("Consulta ejecutada:\n" + sql);
            
            rs = stmt.executeQuery(sql);
            succesful = false;
            // Mostrar resultados. 
            while (rs.next()) {
                succesful = true;
                int rowId = rs.getInt("id");
                String rowUsername = rs.getString("username");
                String rowPassword = rs.getString("password");
                // Imprimir los resultados
                System.out.println(rowId + "\t" + rowUsername + "\t" + rowPassword);
            }            
            // Mostrar resultado del login. 
            if (succesful) {
                System.out.println("Inicio de sesión correcto.");
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: "
                    + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos o al ejecutar una sentencia SQL: "
                    + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}