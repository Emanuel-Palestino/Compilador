package AnalizadorLexico.Final;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class IniciarFinal{

    String prueba = "C:/Descargas/AnalizadorLexico/archivo.txt";

    public IniciarFinal() throws FileNotFoundException,IOException{
        new VentanaFinal(null,false,prueba);

        /*Unir codigo de pale*/
    }
    
    public static void main(String[] args) {
        try {
            new IniciarFinal();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


