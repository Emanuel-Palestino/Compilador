package AnalizadorLexico.ConversionAFD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import AnalizadorLexico.AlgoritmoThompson.Thompson;
import Utilidades.Archivo;
import Utilidades.Automata;
import Utilidades.Listas.ListaDoblementeEnlazada;
import Utilidades.Listas.ListaDoblementeEnlazadaD;
import Utilidades.Listas.NodoLista;
import Utilidades.Listas.NodoListaD;

public class IniciarConjuntos{
    public IniciarConjuntos() throws FileNotFoundException, IOException {
        //Codigo del pale para unir la ventana

        Thompson thomp = new Thompson();
        Archivo file = new Archivo();
        ArrayList<String> expr = file.capturaDatosArchivo("src/ArchivosExtra/expresion.txt");
        Automata AFN = thomp.evaluarER(expr.get(1), expr.get(0));
        ConvierteAFD conversion = new ConvierteAFD();
        ArrayList<ListaDoblementeEnlazadaD> AFD = conversion.convierteAFD(AFN);

        String[] encabezado = new String[expr.get(0).length() + 1];
        encabezado[0] = "Estado";
        for (int i = 0; i < expr.get(0).length(); i++)
            encabezado[i + 1] = "" + expr.get(0).charAt(i);

        // Rellenar matriz con datos
        String[][] datos = new String[AFD.size()][encabezado.length];
        for(ListaDoblementeEnlazadaD transiciones : AFD) {
            int i = AFD.indexOf(transiciones);
            datos[i][0] = transiciones.getEstado().getId();


            // Recorrer lista
            NodoListaD tran = null;
            try {
                tran = transiciones.getInicio();
            } catch (NullPointerException e) {
                System.out.println("Sin transiciones:");
            }
            while (tran != null) {
                String simbolo = tran.getTransicion();
                int pos = -1;

                // Encontrar el indice de la transicion que concuerde
                for (int a = 1; a < encabezado.length; a++) {
                    if (simbolo.equals(encabezado[a])) {
                        pos = a;
                        break;
                    }
                }


                datos[i][pos] = tran.getEstados().getId();

                tran = tran.getSiguiente();
            }

        }


        new VentanaConjuntos(null,false, expr.get(0), expr.get(1), encabezado, datos);   
        
    }
}