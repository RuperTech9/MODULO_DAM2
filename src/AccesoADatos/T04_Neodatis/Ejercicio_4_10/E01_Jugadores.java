package AccesoADatos.T04_Neodatis.Ejercicio_4_10;

public class E01_Jugadores {

    // Propiedades
    private String nombre;
    private String deporte;
    private String ciudad;
    private int edad;
    private E01_Paises pais;

    // Constructores
    public E01_Jugadores(){};

    public E01_Jugadores(String nombre, String deporte, String ciudad, int edad, E01_Paises pais) {
        super();
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.edad = edad;
        this.pais = pais;
    }

    // M�todos
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDeporte() {
        return deporte;
    }
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int p) {
        this.edad = edad;
    }
    public E01_Paises getPais() {
        return pais;
    }
    public void setPais(E01_Paises pais) {
        this.pais = pais;
    }
} // Fin clase Jugadores