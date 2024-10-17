package co.edu.uco.ucobet.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerConnection {

	public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/UcoBetDOO";
        String user = "postgres";
        String password = "musical2";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Conexión exitosa!");
                
                Statement stmt = connection.createStatement();

                String createTableQuery = "CREATE TABLE IF NOT EXISTS clientes ("
                        + "id SERIAL PRIMARY KEY, "
                        + "nombre VARCHAR(100) NOT NULL, "
                        + "email VARCHAR(100), "
                        + "telefono VARCHAR(15))";

                stmt.executeUpdate(createTableQuery);
                System.out.println("Tabla 'clientes' creada exitosamente.");

            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}

