package AccesoADatos.T01_Ficheros;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class EjercicioProfe4_MiObjectOutputStream extends ObjectOutputStream
{
    public EjercicioProfe4_MiObjectOutputStream(OutputStream out) throws IOException
        {  super(out);  }
        protected EjercicioProfe4_MiObjectOutputStream()
            throws IOException, SecurityException
        { super();}
        //Redefinició del método de escribir la cabecera
        // para que no haga nada
        protected void writeStreamHeader() throws IOException
        { }


}//Fin class
