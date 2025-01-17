package AccesoADatos.T04_Neodatis.Ejercicio_4_10;

public class E01_Paises {

    private int idPais;
    private String nombrePais;

    public E01_Paises() {};

    public E01_Paises(int id, String nombre) {
        super();
        idPais = id;
        nombrePais = nombre;
    }

    //GETTERS Y SETTERS
    public int getIdPais() {
        return idPais;
    }
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
    public String getNombrePais() {
        return nombrePais;
    }
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}