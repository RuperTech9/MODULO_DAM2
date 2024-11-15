package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class EjemploModificarSalario {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Definir los valores directamente en variables
            int dep = 20;          // Número de departamento
            float subida = 500.0f; // Cantidad de subida de salario

            // Construir orden UPDATE usando PreparedStatement
            String sql = "UPDATE empleados SET salario = salario + ? WHERE dept_no = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setFloat(1, subida); // Pasar la cantidad de subida
            pstmt.setInt(2, dep);      // Pasar el número de departamento

            int filas = pstmt.executeUpdate();
            System.out.printf("Empleados modificados: %d %n", filas);

            pstmt.close();    // Cerrar PreparedStatement
            conexion.close(); // Cerrar conexión
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}