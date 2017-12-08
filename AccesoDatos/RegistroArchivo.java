package AccesoDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroArchivo {
    public static boolean guardar(File archivo, String contenido){
        boolean guardado = false;
        FileWriter escritor = null;
        try{
            escritor = new FileWriter(archivo);
            escritor.write(contenido);
            guardado = true;
        }catch(IOException excepcion){
            
        }finally{
            try{
                escritor.close();
            }catch(IOException excepcion){
                
            }
        }
        return guardado;
    }
    public static String leerLinea(File archivo){
        String idioma = "";
        FileReader lector = null;
        BufferedReader contenidoTotal = null;
        try{
            lector = new FileReader(archivo);
            contenidoTotal = new BufferedReader(lector);
            String linea = "";
            idioma = contenidoTotal.readLine();
        }catch(IOException excepcion){
            
        }finally{
            try{
                lector.close();
                contenidoTotal.close();
            }catch(IOException excepcion){
                
            }
        }
        return idioma;
    }
}
