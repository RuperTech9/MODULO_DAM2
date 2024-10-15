package AccesoADatos.T01_Ficheros.TareaFicheroObjetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class E07_MiObjectOutputStream extends ObjectOutputStream {

    public E07_MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected E07_MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    // Sobrescribir el método writeStreamHeader para evitar escribir la cabecera varias veces
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}