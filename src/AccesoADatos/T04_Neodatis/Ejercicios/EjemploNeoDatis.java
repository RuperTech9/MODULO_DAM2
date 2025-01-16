package AccesoADatos.T04_Neodatis.Ejercicios;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

// Clase Jugadores
class Jugadores {
    private String nombre;
    private String deporte;
    private String ciudad;
    private int edad;

    public Jugadores() {}

    public Jugadores(String nombre, String deporte, String ciudad, int edad) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }

    public void setDeporte(String deporte) { this.deporte = deporte; }
    public String getDeporte() { return deporte; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getCiudad() { return ciudad; }

    public void setEdad(int edad) { this.edad = edad; }
    public int getEdad() { return edad; }
}

public class EjemploNeoDatis {
    public static void main(String[] args) {
        // Crear instancias para almacenar en BD
        Jugadores j1 = new Jugadores("María", "Voleibol", "Madrid", 14);
        Jugadores j2 = new Jugadores("Miguel", "Tenis", "Madrid", 15);
        Jugadores j3 = new Jugadores("Carlos", "Baloncesto", "Guadalajara", 15);
        Jugadores j4 = new Jugadores("Alicia", "Tenis", "Madrid", 14);

        ODB odb = ODBFactory.open("neodatis.test"); // Abrir BD

        // Almacenar objetos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        odb.close(); // Cerrar BD

        // Recuperar todos los objetos
        odb = ODBFactory.open("neodatis.test");
        Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
        System.out.println("Número de Jugadores: " + objects.size());

        int i = 1;
        while (objects.hasNext()) {
            Jugadores jugador = objects.next();
            System.out.println((i++) + ":\t" + jugador.getNombre() + ", " + jugador.getDeporte() + ", " + jugador.getEdad());
        }

        odb.close(); // Cerrar BD
    }
}