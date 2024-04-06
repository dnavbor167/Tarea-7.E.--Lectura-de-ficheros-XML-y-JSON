/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
        Scanner s = new Scanner(System.in);
        //creamos 50 aplicaciones usando constructor por defecto y lo guardamos
        //en una lista
        List<App> lista = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            lista.add(new App());
        }

        System.out.println("Ejercicio 1\n");
        //Genera en la raíz del proyecto, a partir de la lista de Apps, 
        //un fichero XML con los datos de todas las aplicaciones.
        //primero de todo creamos el fichero en la raíz del proyecto
        readWrite.writeXml("./ficheroxml.xml", lista);

        System.out.println("\nEjercicio 2\n");
        //Genera en la raíz del proyecto, a partir de la lista de Apps, 
        //un fichero JSON con los datos de todas las aplicaciones.
        readWrite.writeJson("./ficherojson.json", lista);

        System.out.println("\nEjercicio 3\n");
        //Genera tantos archivos JSON como aplicaciones 
        //haya en la lista en una carpeta llamada ./aplicacionesJSON. 
        //El nombre de cada archivo será el nombre de la aplicación en cuestión.
        Directorios.crearDirectorio("./aplicacionesJSON");

        for (App app : lista) {
            readWrite.writeJson("./aplicacionesJSON/" + app.getNombre() + ".json", app);
        }

        System.out.println("\nEjercicio 4\n");
        //Realiza una lectura del fichero XML y muestra los datos de todas 
        //las apps por consola.
        readWrite.readXml("./ficheroxml.xml").forEach(System.out::println);
        
        System.out.println("\nEjercicio 5\n");
        //Realiza una lectura del fichero JSON y muestra los datos 
        //de todas las apps por consola
        readWrite.readJson("./ficherojson.json").forEach(System.out::println);
        
        System.out.println("\nEjercicio 6\n");
        //Muestra un listado de los ficheros que hay en ./aplicacionesJSON. 
        File file = new File("./aplicacionesJSON/");
        File[] fileArray = file.listFiles();
        for (File file1 : fileArray) {
            System.out.println(file1.getName());
        }
        
        System.out.println("\nEjercicio 7\n");
        //Pregunta al usuario el nombre del fichero json (de los anteriores) 
        //que quiere leer. Una vez leído muestra los datos de esta app por consola.
        System.out.println("Introduce el nombre de algún fichero.json de los anteriores");
        String respuesta = s.nextLine();
        for (File file1 : fileArray) {
            if (file1.getName().equals(respuesta)) {
                System.out.println(readWrite.readJson2("./aplicacionesJSON/" + respuesta));
                break;
            }
        }
        
        System.out.println("\nEjercicio 8\n");
        //Borra el archivo leído y comprueba que ya no está en el 
        //directorio ./aplicacionesJSON.
        Directorios.borrarElemento("./aplicacionesJSON/" + respuesta);

    }
}
