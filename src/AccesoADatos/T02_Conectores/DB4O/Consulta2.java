package AccesoADatos.T02_Conectores.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Consulta2 {
    static String BDPer = "./src//AccesoADatos//T02_Conectores//DB4O//DBPersonas.yap";

    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

        // Consulta de objetos Persona cuyo nombre es "Juan"
        Persona per = new Persona("Juan", null);
        ObjectSet<Persona> result = db.queryByExample(per);

        if (result.size() == 0) {
            System.out.println("No existe Juan...");
        } else {
            Persona existe = result.next();
            existe.setCiudad("Toledo"); // Modificar ciudad de Juan a Toledo
            db.store(existe); // Almacenar el objeto modificado

            // Consultar de nuevo para verificar el cambio
            result = db.queryByExample(new Persona("Juan", null));
            existe = result.next();
            System.out.printf("Nombre: %s, Nueva Ciudad: %s %n", existe.getNombre(), existe.getCiudad());
        }

        // Eliminar todos los objetos Persona cuyo nombre es "Juan"
        result = db.queryByExample(new Persona("Juan", null));

        if (result.size() == 0) {
            System.out.println("No existe Juan...");
        } else {
            System.out.printf("Registros a borrar: %d %n", result.size());
            while (result.hasNext()) {
                Persona p = result.next();
                db.delete(p); // Eliminar el objeto
                System.out.println("Borrado...");
            }
        }

        db.close(); // Cerrar la base de datos
    }
}