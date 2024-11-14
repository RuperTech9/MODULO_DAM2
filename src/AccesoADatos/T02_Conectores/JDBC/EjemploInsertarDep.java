package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class EjemploInsertarDep {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Recuperar argumentos de main
            String dept_no = args[0];      // num. departamento
            String dnombre = args[1];  // nombre
            String loc = args[2];      // localidad

            // Construir orden INSERT
            String sql = String.format(
                    "INSERT INTO departamentos VALUES (%s, '%s', '%s')",
                    dept_no, dnombre, loc
            );

            System.out.println(sql);

            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql);
            System.out.printf("Filas afectadas: %d %n", filas);

            sentencia.close(); // Cerrar Statement
            conexion.close();  // Cerrar conexión
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}