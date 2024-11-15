package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class CrearVistaTotales {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Construir orden CREATE VIEW
            StringBuilder sql = new StringBuilder();
            sql.append("CREATE OR REPLACE VIEW totales AS ");
            sql.append("SELECT d.dept_no, dnombre, COUNT(emp_no) AS nemp, AVG(salario) AS media ");
            sql.append("FROM departamentos d LEFT JOIN empleados e ");
            sql.append("ON e.dept_no = d.dept_no ");
            sql.append("GROUP BY d.dept_no, dnombre");

            System.out.println(sql);

            // Ejecutar la consulta
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql.toString());
            System.out.printf("Resultado de la ejecución: %d %n", filas);

            // Realizar el SELECT * FROM SalariosMediosEspecialidad
            String consultaVista = "SELECT * FROM totales";
            ResultSet resultado = sentencia.executeQuery(consultaVista);

            // Mostrar los resultados de la vista
            System.out.println("\nResultados de la vista Totales:");
            while (resultado.next()) {
                int deptNo = resultado.getInt("dept_no");
                String dnombre = resultado.getString("dnombre");
                int nemp = resultado.getInt("nemp");
                float media = resultado.getFloat("media");

                System.out.printf("Dept No: %d, Nombre: %s, Empleados: %d, Salario Medio: %.2f%n",
                        deptNo, dnombre, nemp, media);
            }

            // Cerrar recursos
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
