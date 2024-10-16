package AccesoADatos.T01_Ficheros.Objetos;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonas {
    private List<Persona> lista = new ArrayList<Persona>();

    public ListaPersonas() {
        // Constructor vacío
    }

    public void add(Persona per) {
        lista.add(per);
    }

    public List<Persona> getListaPersonas() {
        return lista;
    }
}