package AccesoADatos.T01_Ficheros;

import java.io.*;

public class E13_EscribirFichTextoBuff {
    public static void main(String[] args) throws IOException {
        try {
            File fichero = new File("./src//AccesoADatos//T01_Ficheros//FichTextoBuff.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            for (int i=1; i<11; i++){
                bw.write("Fila número: " +i);
                bw.newLine();
            }
            bw.close();

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
