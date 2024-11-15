package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class Prepared_InsertarDep {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Definir los valores a insertar
            int dept_no = 60;           // Número de departamento
            String dnombre = "IT";      // Nombre del departamento
            String loc = "MADRID";    // Localidad del departamento

            // Verificar si el departamento ya existe
            String checkSql = "SELECT COUNT(*) FROM departamentos WHERE dept_no = ?";
            PreparedStatement checkStmt = conexion.prepareStatement(checkSql);
            checkStmt.setInt(1, dept_no);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("Error: El departamento con el número " + dept_no + " ya existe.");
            } else {
                // Construir la orden INSERT usando PreparedStatement
                String sql = "INSERT INTO departamentos (dept_no, dnombre, loc) VALUES (?, ?, ?)";
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, dept_no);      // Número de departamento
                sentencia.setString(2, dnombre);   // Nombre del departamento
                sentencia.setString(3, loc);       // Localidad del departamento

                // Ejecutar la inserción
                int filas = sentencia.executeUpdate();
                System.out.printf("Filas afectadas: %d %n", filas);

                // Cerrar el PreparedStatement de inserción
                sentencia.close();
            }
            // Mostrar todos los datos de la tabla departamentos
            System.out.println("\nDatos actuales de la tabla departamentos:");
            Statement stmt = conexion.createStatement();
            ResultSet rsDep = stmt.executeQuery("SELECT * FROM departamentos");

            // Iterar y mostrar cada registro en la tabla departamentos
            while (rsDep.next()) {
                int deptNo = rsDep.getInt("dept_no");
                String nombre = rsDep.getString("dnombre");
                String localidad = rsDep.getString("loc");

                System.out.printf("Dept No: %d, Nombre: %s, Localidad: %s%n", deptNo, nombre, localidad);
            }

            // Cerrar recursos
            rs.close();
            checkStmt.close(); // Cerrar el PreparedStatement de verificación
            rsDep.close();     // Cerrar el ResultSet de la consulta de todos los departamentos
            stmt.close();      // Cerrar el Statement de la consulta
            conexion.close();  // Cerrar conexión
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}