package AccesoADatos.T02_Conectores.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class CrearBBDD_1 {
    static String BDPer = "./src//AccesoADatos//T02_Conectores//DB4O//DBPersonas.yap";

    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

        // Se crean objetos Persona
        Persona p1 = new Persona("Juan", "Guadalajara");
        Persona p2 = new Persona("Ana", "Madrid");
        Persona p3 = new Persona("Luis", "Granada");
        Persona p4 = new Persona("Pedro", "Asturias");

        // Almacenar objetos Persona en la base de datos
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);

        System.out.println("Base de Datos creada: " + db);

        db.close(); // cerrar base de datos
    } // fin metodo main
} // fin de la clase CrearBBDD_1