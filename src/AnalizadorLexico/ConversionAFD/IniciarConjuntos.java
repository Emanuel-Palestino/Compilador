package AnalizadorLexico.ConversionAFD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import AnalizadorLexico.AlgoritmoThompson.Thompson;
import Utilidades.Archivo;
import Utilidades.Automata;
import Utilidades.AutomataDeterminista;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Listas.ListaDoblementeEnlazadaD;
import Utilidades.Listas.NodoListaD;

public class IniciarConjuntos {

    public static void main(String[] args) throws FileNotFoundException, IOException, ExcepcionER {
        new IniciarConjuntos();
    }

    public IniciarConjuntos() throws FileNotFoundException, IOException, ExcepcionER {
        Thompson thomp = new Thompson();
        Archivo file = new Archivo();
        ArrayList<String> expr = file.capturaDatosArchivo("src/ArchivosExtra/ExpresionRegular.txt");
        Automata AFN = thomp.evaluarER(expr.get(1), expr.get(0));
        AutomataDeterminista AFD = ConvierteAFD.convierte(AFN);

        String[] encabezado = new String[AFD.getAlfabeto().getLista().size() + 1];
        encabezado[0] = "Estado";
        for (int i = 0; i < AFD.getAlfabeto().getLista().size(); i++)
            encabezado[i + 1] = AFD.getAlfabeto().getLista().get(i);

        // Rellenar matriz con datos
        String[][] datos = new String[AFD.getTotalEstados()][encabezado.length];

        for (int i = 0; i < AFD.getTotalEstados(); i++) {
            ListaDoblementeEnlazadaD transiciones = AFD.getTransiciones(i);
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

        new VentanaConjuntos(null, false, expr.get(0), expr.get(1), encabezado, datos);

    }
}