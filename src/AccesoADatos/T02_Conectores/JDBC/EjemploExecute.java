package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;
public class EjemploExecute {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // CONEXIÓN A MYSQL
        Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
        Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

        String sql = "SELECT * FROM departamentos";
        Statement sentencia = conexion.createStatement();
        boolean valor = sentencia.execute(sql);

        if (valor) {
            ResultSet rs = sentencia.getResultSet();
            while (rs.next()) {
                System.out.printf("%d, %s, %s %n",
                        rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
        } else {
            int f = sentencia.getUpdateCount();
            System.out.printf("Filas afectadas: %d %n", f);
        }

        sentencia.close();
        conexion.close();
    }
}