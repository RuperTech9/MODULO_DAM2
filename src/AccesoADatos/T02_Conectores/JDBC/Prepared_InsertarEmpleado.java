package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class Prepared_InsertarEmpleado {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Definir los valores del nuevo empleado
            int emp_no = 1003;              // Número de empleado
            String apellido = "Lopez";     // Apellido
            String oficio = "EMPLEADO";     // Oficio
            int dir = 7698;                 // Número de empleado del director
            float salario = 1500.0f;        // Salario
            Float comision = 200.0f;        // Comisión (puede ser null)
            int dept_no = 20;               // Número de departamento
            Date fecha_alt = new Date(System.currentTimeMillis()); // Fecha de alta actual

            // Verificar si el empleado ya existe
            String checkSql = "SELECT COUNT(*) FROM empleados WHERE emp_no = ?";
            PreparedStatement checkStmt = conexion.prepareStatement(checkSql);
            checkStmt.setInt(1, emp_no);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("Error: El empleado con el número " + emp_no + " ya existe.");
            } else {
                // Construir la orden INSERT usando PreparedStatement
                String sql = "INSERT INTO empleados (emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, emp_no);        // Número de empleado
                sentencia.setString(2, apellido);   // Apellido
                sentencia.setString(3, oficio);     // Oficio
                sentencia.setInt(4, dir);           // Director
                sentencia.setDate(5, fecha_alt);    // Fecha de alta
                sentencia.setFloat(6, salario);     // Salario
                if (comision != null) {
                    sentencia.setFloat(7, comision); // Comisión
                } else {
                    sentencia.setNull(7, Types.FLOAT);
                }
                sentencia.setInt(8, dept_no);       // Número de departamento

                // Ejecutar la inserción
                int filas = sentencia.executeUpdate();
                System.out.printf("Filas afectadas: %d %n", filas);

                // Cerrar el PreparedStatement de inserción
                sentencia.close();
            }

            // Mostrar todos los datos de la tabla empleados
            System.out.println("\nDatos actuales de la tabla empleados:");
            Statement stmt = conexion.createStatement();
            ResultSet rsEmp = stmt.executeQuery("SELECT * FROM empleados");

            // Iterar y mostrar cada registro en la tabla empleados
            while (rsEmp.next()) {
                int empleadoNo = rsEmp.getInt("emp_no");
                String empApellido = rsEmp.getString("apellido");
                String empOficio = rsEmp.getString("oficio");
                int empDir = rsEmp.getInt("dir");
                Date empFechaAlt = rsEmp.getDate("fecha_alt");
                float empSalario = rsEmp.getFloat("salario");
                Float empComision = rsEmp.getFloat("comision");
                int empDeptNo = rsEmp.getInt("dept_no");

                System.out.printf("Empleado No: %d, Apellido: %s, Oficio: %s, Director: %d, Fecha Alta: %s, Salario: %.2f, Comisión: %.2f, Departamento: %d%n",
                        empleadoNo, empApellido, empOficio, empDir, empFechaAlt, empSalario,
                        empComision != 0 ? empComision : null, empDeptNo);
            }

            // Cerrar recursos
            rs.close();
            checkStmt.close(); // Cerrar el PreparedStatement de verificación
            rsEmp.close();     // Cerrar el ResultSet de la consulta de todos los empleados
            stmt.close();      // Cerrar el Statement de la consulta
            conexion.close();  // Cerrar conexión
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}