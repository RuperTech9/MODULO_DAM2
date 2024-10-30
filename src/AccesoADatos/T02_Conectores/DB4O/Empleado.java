package AccesoADatos.T02_Conectores.DB4O;

public class Empleado {
    private String nombre;
    private String puesto;
    private Departamento departamento;

    public Empleado(String nombre, String puesto, Departamento departamento) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.departamento = departamento;
    }

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}