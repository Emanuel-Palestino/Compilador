package Pruebas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Utilidades.Archivo;


public class SeleccionArchivo {
    
public static void main(String[] args) throws FileNotFoundException, IOException {
    Archivo archivo = new Archivo();
    ArrayList <String> contenido = new  ArrayList<>();
    String direccionArchivo=archivo.obtenerRutaArchivo();
    
    System.out.println("Ruta del archivo:"+direccionArchivo);
    contenido=archivo.capturaDatosArchivo(direccionArchivo);
    contenido.forEach(System.out::println);
}   
}