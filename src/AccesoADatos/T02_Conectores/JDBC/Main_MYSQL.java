package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class Main_MYSQL {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
             * A partir de MySQL Connector/J versión 8.x, el controlador recomendado es com.mysql.cj.jdbc.Driver en lugar de com.mysql.jdbc.Driver.
             */

            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/ejemplo", "ruper", "A1a2A3a4A5"
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