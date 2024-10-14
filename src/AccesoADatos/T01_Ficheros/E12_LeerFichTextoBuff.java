package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E12_LeerFichTextoBuff {
    public static void main(String[] args) throws IOException {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros//E05_LeerFichTexto.java");
            BufferedReader br = new BufferedReader(new FileReader(fichero));

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();

        }catch (FileNotFoundException e){
            System.out.println("No se encuentra el fichero");
        } catch (IOException io){
            System.out.println("Error de E/S ");
        }
    }
}
