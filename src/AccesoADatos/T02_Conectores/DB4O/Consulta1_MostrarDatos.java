package AccesoADatos.T02_Conectores.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Consulta1_MostrarDatos {
    static String BDPer = "./src//AccesoADatos//T02_Conectores//DB4O//DBPersonas.yap";
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

        Persona per = new Persona(null,null);
        ObjectSet<Persona> result = db.queryByExample(per);

        if (result.size() == 0) {
            System.out.println("No existen registros de personas..");
        } else {
            System.out.printf("Número de registros: %d %n", result.size());
            while (result.hasNext()) {
                Persona p = result.next();
                System.out.printf("Nombre: %s, Ciudad: %s %n", p.getNombre(), p.getCiudad());
            }
        }
        db.close();
    }
}
