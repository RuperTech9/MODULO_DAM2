package AccesoADatos.T04_Neodatis.Ejercicios;

public class Paises {
    private int id;
    private String nombrepais;

    // Constructor
    public Paises(int id, String nombrepais) {
        this.id = id;
        this.nombrepais = nombrepais;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrepais() {
        return nombrepais;
    }

    public void setNombrepais(String nombrepais) {
        this.nombrepais = nombrepais;
    }

    // Método toString
    @Override
    public String toString() {
        return nombrepais;
    }
}