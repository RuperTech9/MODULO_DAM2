package AccesoADatos.T02_Conectores.JDBC;

import java.sql.*;
public class EjemploDatabaseMetadata {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            // Establecemos la conexión con la BD
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ejemplo", "ruper", "A1a2A3a4A5");

            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet resul = null;

            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
            System.out.println("====================================");
            System.out.printf("Nombre : %s %n", nombre);
            System.out.printf("Driver : %s %n", driver);
            System.out.printf("URL : %s %n", url);
            System.out.printf("Usuario: %s %n", usuario);


            System.out.println("\nINFORMACIÓN DE LAS TABLAS Y VISTAS QUE HAY:");
            System.out.println("==============================================");
            // Obtener información de las tablas y vistas que hay
            resul = dbmd.getTables(null, "ejemplo", null, null);

            while (resul.next()) {
                String catalogo = resul.getString(1); // columna 1
                String esquema = resul.getString(2); // columna 2
                String tabla = resul.getString(3); // columna 3
                String tipo = resul.getString(4); // columna 4
                System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n",
                        tipo, catalogo, esquema, tabla);
            }

            System.out.println("\nCOLUMNAS TABLA DEPARTAMENTOS:");
            System.out.println("=====================================");
            ResultSet columnas = null;
            columnas = dbmd.getColumns(null, "ejemplo", "departamentos", null);
            while (columnas.next()) {
                String nombCol = columnas.getString("COLUMN_NAME");   // getString(4)
                String tipoCol = columnas.getString("TYPE_NAME");     // getString(6)
                String tamCol  = columnas.getString("COLUMN_SIZE");   // getString(7)
                String nula    = columnas.getString("IS_NULLABLE");   // getString(18)

                System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s, ¿Puede ser Nula?: %s %n",
                        nombCol, tipoCol, tamCol, nula);
            }


            System.out.println("\nCLAVE PRIMARIA DE LA TABLA DEPARTAMENTOS:");
            System.out.println("===========================================");
            ResultSet pk = dbmd.getPrimaryKeys(null, "ejemplo", "departamentos");
            String pkDep = "", separador = "";
            while (pk.next()) {
                pkDep = pkDep + separador + pk.getString("COLUMN_NAME"); // getString(4)
                separador = "+";
            }
            System.out.println("Clave Primaria: " + pkDep);

            System.out.println("\nCLAVES FORÁNEAS QUE REFERENCIAN A LA TABLA DEPARTAMENTOS:");
            System.out.println("========================================================");
            ResultSet fk = dbmd.getExportedKeys(null, "ejemplo", "departamentos");
            while (fk.next()) {
                String fkName = fk.getString("FKCOLUMN_NAME");
                String fkTableName = fk.getString("FKTABLE_NAME");
                String pkTableName = fk.getString("PKTABLE_NAME");
                String pkName = fk.getString("PKCOLUMN_NAME");

                System.out.printf("Tabla PK: %s, Clave Primaria: %s %n", pkTableName, pkName);
                System.out.printf("Tabla FK: %s, Clave Foránea: %s %n", fkTableName, fkName);
            }

            System.out.println("\nCLAVES FORÁNEAS QUE REFERENCIAN OTRAS TABLAS EN DEPARTAMENTOS:");
            System.out.println("===============================================================");
            ResultSet importedKeys = dbmd.getImportedKeys(null, "ejemplo", "departamentos");
            while (importedKeys.next()) {
                String fkName = importedKeys.getString("FKCOLUMN_NAME");
                String fkTableName = importedKeys.getString("FKTABLE_NAME");
                String pkTableName = importedKeys.getString("PKTABLE_NAME");
                String pkName = importedKeys.getString("PKCOLUMN_NAME");

                System.out.printf("Tabla PK: %s, Clave Primaria: %s %n", pkTableName, pkName);
                System.out.printf("Tabla FK: %s, Clave Foránea: %s %n", fkTableName, fkName);
            }

            System.out.println("\nPROCEDIMIENTOS ALMACENADOS:");
            System.out.println("===========================");
            ResultSet procedures = dbmd.getProcedures(null, "ejemplo", null);
            while (procedures.next()) {
                String procedureCatalog = procedures.getString(1);
                String procedureSchema = procedures.getString(2);
                String procedureName = procedures.getString(3);
                String remarks = procedures.getString(7);
                String procedureType = procedures.getString(8);

                System.out.printf("Catálogo: %s, Esquema: %s, Nombre: %s, Tipo: %s, Descripción: %s %n",
                        procedureCatalog, procedureSchema, procedureName, procedureType, remarks);
            }

            // El siguiente ejemplo muestra los procedimientos y funciones que tiene el esquema de nombre ejemplo:
            ResultSet proc = dbmd.getProcedures(null, "ejemplo", null);
            while (proc.next()) {
                String proc_name = proc.getString("PROCEDURE_NAME");
                String proc_type = proc.getString("PROCEDURE_TYPE");
                System.out.printf("Nombre Procedimiento: %s - Tipo: %s %n",
                        proc_name, proc_type);
            }

            conexion.close(); // Cerrar conexión
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}