package AccesoADatos.T02_Conectores.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
public class Main2 {
    static String BDName = "./src//AccesoADatos//T02_Conectores//DB4O//EMPLEDEP.YAP";

    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDName);

        // Crear objetos Departamento
        Departamento d1 = new Departamento(1, "Contabilidad");
        Departamento d2 = new Departamento(2, "Ventas");

        // Almacenar objetos Departamento
        db.store(d1);
        db.store(d2);

        // Crear objetos Empleado y asignarlos a los departamentos
        Empleado e1 = new Empleado("Juan", "Contador", d1);
        Empleado e2 = new Empleado("Ana", "Vendedor", d2);
        Empleado e3 = new Empleado("Luis", "Vendedor", d2);

        // Almacenar objetos Empleado
        db.store(e1);
        db.store(e2);
        db.store(e3);

        // Consultar todos los empleados de un departamento específico (por ejemplo, Ventas)
        Departamento deptConsulta = new Departamento();
        deptConsulta.setNombre("Ventas");

        ObjectSet<Departamento> resultadoDept = db.queryByExample(deptConsulta);

        if (resultadoDept.size() > 0) {
            Departamento dept = resultadoDept.next();
            System.out.println("Departamento: " + dept.getNombre());

            Empleado empConsulta = new Empleado();
            empConsulta.setDepartamento(dept);

            ObjectSet<Empleado> resultadoEmp = db.queryByExample(empConsulta);

            if (resultadoEmp.size() > 0) {
                System.out.printf("Empleados en el departamento %s:%n", dept.getNombre());
                while (resultadoEmp.hasNext()) {
                    Empleado emp = resultadoEmp.next();
                    System.out.printf("Nombre: %s, Puesto: %s%n", emp.getNombre(), emp.getPuesto());
                }
            } else {
                System.out.println("No hay empleados en este departamento.");
            }
        } else {
            System.out.println("No existe el departamento especificado.");
        }

        db.close();
    }
}