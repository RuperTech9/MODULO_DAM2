package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class Prepared_ModificarSalario {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Definir el número de departamento y la subida de salario
            int dept_no = 20;          // Número de departamento
            float subida = 500.0f;     // Cantidad a incrementar en el salario

            // Construir orden UPDATE usando PreparedStatement
            String sql = "UPDATE empleados SET salario = salario + ? WHERE dept_no = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setFloat(1, subida);       // Asignar la cantidad de subida
            sentencia.setInt(2, dept_no);        // Asignar el número de departamento

            // Ejecutar la actualización
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas afectadas: %d %n", filas);

            // Mostrar los datos de la tabla empleados para verificar la actualización
            System.out.println("\nDatos actuales de la tabla empleados:");
            Statement stmt = conexion.createStatement();
            ResultSet rsEmp = stmt.executeQuery("SELECT * FROM empleados");

            // Iterar y mostrar cada registro en la tabla empleados
            while (rsEmp.next()) {
                int empNo = rsEmp.getInt("emp_no");
                String apellido = rsEmp.getString("apellido");
                String oficio = rsEmp.getString("oficio");
                int dir = rsEmp.getInt("dir");
                Date fechaAlt = rsEmp.getDate("fecha_alt");
                float salario = rsEmp.getFloat("salario");
                Float comision = rsEmp.getFloat("comision");
                int empDeptNo = rsEmp.getInt("dept_no");

                System.out.printf("Empleado No: %d, Apellido: %s, Oficio: %s, Director: %d, Fecha Alta: %s, Salario: %.2f, Comisión: %.2f, Departamento: %d%n",
                        empNo, apellido, oficio, dir, fechaAlt, salario,
                        comision != 0 ? comision : null, empDeptNo);
            }

            // Cerrar recursos
            rsEmp.close();     // Cerrar el ResultSet de la consulta de todos los empleados
            stmt.close();      // Cerrar el Statement de la consulta
            sentencia.close(); // Cerrar el PreparedStatement de actualización
            conexion.close();  // Cerrar conexión
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}