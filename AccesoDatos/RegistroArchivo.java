package AccesoDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroArchivo {
    /**
     * metodo estatico de tipo booleano que permite guardar un archivo con contenido establecido
     * @param archivo parametro del tipo File escribe un archivo
     * @param contenido valor de tipo String que se almacenará en el archivo
     * @return guardado indetificador de tipo booleano que permite la validacion del correcto almacenamiento 
     */
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
    /**
     * metodo estatico de tipo String que permite leer un archivo y configurar un idioma a partir del resultado
     * @param archivo variable del tipo File que contiene los datos de lectura
     * @return idioma permite obtener el dato leido del archivo para modificacion de salida o interfaz de usuario
     */
    public static String leerLinea(File archivo){
        String idioma = "";
        FileReader lector = null;
        BufferedReader contenidoTotal = null;
        try{
            lector = new FileReader(archivo);
            contenidoTotal = new BufferedReader(lector);
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
