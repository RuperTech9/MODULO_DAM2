package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class Main_SQLite {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("org.sqlite.JDBC");

            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection(
                    "jdbc:sqlite:C:/Users/Ruper/Documents/sqlite/ejemplo.db"
            );

            // Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM departamentos";
            ResultSet resul = sentencia.executeQuery(sql);

            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van mostrando
            while (resul.next()) {
                System.out.printf("%d, %s, %s %n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3)
                );
            }

            resul.close(); // Cerrar ResultSet
            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}