package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;

public class EjercicioInsertarEmpleado {
    public static void main(String[] args) {
        try {
            // CONEXIÓN A MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            // Datos del nuevo empleado
            int empNo = 1002;             // Número de empleado
            String apellido = "Ruperez";   // Apellido
            String oficio = "VENDEDOR";   // Oficio
            int dir = 7369;               // Número de empleado del director
            float salario = 1500.0f;      // Salario
            Float comision = 200.0f;      // Comisión (puede ser null)
            int deptNo = 20;              // Número de departamento

            // Fecha actual para la fecha de alta
            Date fechaAlta = new Date(System.currentTimeMillis());

            // Verificar que el departamento existe
            PreparedStatement psDept = conexion.prepareStatement("SELECT COUNT(*) FROM departamentos WHERE dept_no = ?");
            psDept.setInt(1, deptNo);
            ResultSet rsDept = psDept.executeQuery();
            rsDept.next();
            if (rsDept.getInt(1) == 0) {
                System.out.println("Error: El departamento no existe.");
                return;
            }
            rsDept.close();
            psDept.close();

            // Verificar que el número de empleado no existe
            PreparedStatement psEmpNo = conexion.prepareStatement("SELECT COUNT(*) FROM empleados WHERE emp_no = ?");
            psEmpNo.setInt(1, empNo);
            ResultSet rsEmpNo = psEmpNo.executeQuery();
            rsEmpNo.next();
            if (rsEmpNo.getInt(1) > 0) {
                System.out.println("Error: El número de empleado ya existe.");
                return;
            }
            rsEmpNo.close();
            psEmpNo.close();

            // Verificar que el salario sea > 0
            if (salario <= 0) {
                System.out.println("Error: El salario debe ser mayor que 0.");
                return;
            }

            // Verificar que el director existe en empleados (si dir no es 0)
            if (dir != 0) {
                PreparedStatement psDir = conexion.prepareStatement("SELECT COUNT(*) FROM empleados WHERE emp_no = ?");
                psDir.setInt(1, dir);
                ResultSet rsDir = psDir.executeQuery();
                rsDir.next();
                if (rsDir.getInt(1) == 0) {
                    System.out.println("Error: El director no existe.");
                    return;
                }
                rsDir.close();
                psDir.close();
            }

            // Verificar que APELLIDO y OFICIO no son nulos
            if (apellido == null || apellido.isEmpty() || oficio == null || oficio.isEmpty()) {
                System.out.println("Error: El apellido y el oficio no pueden ser nulos.");
                return;
            }

            // Insertar el nuevo empleado
            String sql = "INSERT INTO empleados (emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, empNo);
            pstmt.setString(2, apellido);
            pstmt.setString(3, oficio);
            pstmt.setInt(4, dir);
            pstmt.setDate(5, fechaAlta);
            pstmt.setFloat(6, salario);
            if (comision != null) {
                pstmt.setFloat(7, comision);
            } else {
                pstmt.setNull(7, Types.FLOAT);
            }
            pstmt.setInt(8, deptNo);

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado insertado correctamente.");
            } else {
                System.out.println("Error: No se pudo insertar el empleado.");
            }
            // Cerrar recursos
            pstmt.close();

            // Mostrar todos los datos de la tabla empleados
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");

            System.out.println("\nDatos de la tabla empleados:");
            while (rs.next()) {
                int empleadoNo = rs.getInt("emp_no");
                String empApellido = rs.getString("apellido");
                String empOficio = rs.getString("oficio");
                int empDir = rs.getInt("dir");
                Date empFechaAlt = rs.getDate("fecha_alt");
                float empSalario = rs.getFloat("salario");
                Float empComision = rs.getFloat("comision");
                int empDeptNo = rs.getInt("dept_no");

                System.out.printf("Empleado No: %d, Apellido: %s, Oficio: %s, Director: %d, Fecha Alta: %s, Salario: %.2f, Comisión: %.2f, Departamento: %d%n",
                        empleadoNo, empApellido, empOficio, empDir, empFechaAlt, empSalario,
                        empComision != 0 ? empComision : null, empDeptNo);
            }

            // Cerrar ResultSet y Statement
            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
