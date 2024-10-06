package AccesoADatos.T01_Ficheros.TareaFicheroObjetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    // Sobrescribir el método writeStreamHeader para evitar escribir la cabecera varias veces
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}