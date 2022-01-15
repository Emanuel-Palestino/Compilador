package Utilidades.Gramatica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utilidades.Archivo;

public class Gramatica {
    private ArrayList<String> noTerminales;
    private ArrayList<String> terminales;
    private ArrayList<ReglaProduccion> reglasProduccion;
    private String simboloInicial;
    private boolean banderaEpsilon;
    
    // Constructores
    public Gramatica() {
        noTerminales = new ArrayList<String>();
        terminales = new ArrayList<String>();
        reglasProduccion = new ArrayList<ReglaProduccion>();
        banderaEpsilon = false;
        simboloInicial = null;
    }

    // pasar cada uno de los atributos
    public Gramatica(ArrayList<String> ter, ArrayList<String> noTer) {
        noTerminales = noTer;
        terminales = ter;
        reglasProduccion = new ArrayList<ReglaProduccion>();
        simboloInicial = null;
        banderaEpsilon = false;
    }

    public Gramatica(ArrayList<ReglaProduccion> reglas) {
        noTerminales = new ArrayList<String>();
        terminales = new ArrayList<String>();
        reglasProduccion = reglas;
        simboloInicial = null;
        banderaEpsilon = false;
    }

    // Cargar gramatica desde archivo
    public Gramatica(String rutaArchivo) {
        noTerminales = new ArrayList<String>();
        terminales = new ArrayList<String>();
        reglasProduccion = new ArrayList<ReglaProduccion>();
        try {
            // Obtener las lineas del archivo
            ArrayList<String> lineasArchivo = Archivo.capturaDatosArchivo(rutaArchivo);

            String[] aux;

            // Obtener los símbolos no terminales
            aux = lineasArchivo.get(0).split(" ");
            for (String simbolo : aux)
                noTerminales.add(simbolo);

            // Obtener los símbolos terminales
            aux = lineasArchivo.get(1).split(" ");
            for (String simbolo : aux)
                terminales.add(simbolo);

            // Obtener el símbolo inicial
            simboloInicial = lineasArchivo.get(2).trim(); // trim es para quitar espacios

            // obtener solo las relgas de producción
            List<String> reglasProd = lineasArchivo.subList(3, lineasArchivo.size());

            for (String regla : reglasProd) {
                // dividir la regla
                String[] partesRegla = regla.split("»");
                String simbolo = partesRegla[0].trim();
                String produccion = partesRegla[1].trim();

                // Se crea una nueva Regla de Produccion pasandole el simbolo y el List de simbolos producidos
                String[] aux2 = produccion.split(" ");
                ArrayList<String> simbolosProduccion = new ArrayList<String>();
                for (String simbol : aux2)
                    simbolosProduccion.add(simbol);
                ReglaProduccion nuevaRegla = new ReglaProduccion(simbolo, simbolosProduccion);

                // Se agrega la nueva regla
                reglasProduccion.add(nuevaRegla);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gramatica.java - Error al cargar el archivo!!!");
        }
    }

    public int indiceEpsilon(String Simbolo){
        int indice = -33, i = 1;
        for(ReglaProduccion recorreProduccion : this.reglasProduccion){
            if(recorreProduccion.getProduccion().size() == 1){
                String string = recorreProduccion.getProduccion().get(0);
                if(string.equals("Ɛ") && recorreProduccion.getSimboloGramatical().contentEquals(Simbolo)){
                    indice = i;
                }
            }
            i++;
        }
        return indice;
    }

    // getter y setters
    public ArrayList<String> getNoTerminales() {
        return noTerminales;
    }

    public ArrayList<String> getTerminales() {
        return terminales;
    }

    public ArrayList<ReglaProduccion> getReglasProduccion() {
        return reglasProduccion;
    }

    public String getSimboloInicial() {
        return simboloInicial;
    }

    public boolean getBanderaEpsilon(){
        return banderaEpsilon;
    }

    public void setNoTerminales(ArrayList<String> noTerminales) {
        this.noTerminales = noTerminales;
    }

    public void setTerminales(ArrayList<String> terminales) {
        this.terminales = terminales;
    }

    public void setReglasProduccion(ArrayList<ReglaProduccion> reglas) {
        this.reglasProduccion = reglas;
    }

    public void setSimboloInicial(String simboloInicial) {
        this.simboloInicial = simboloInicial;
    }

    public void setBanderaEpsilon(boolean nuevoValor){
        this.banderaEpsilon = nuevoValor;
    }

    // Métodos
    public boolean esTerminal(String aVerificar) {
        return getTerminales().contains(aVerificar);
    }

    public ArrayList<ReglaProduccion> reglasDeSimbolo(String simbolo) {
        // regresar arraylist de las reglas de produccion del string de entrada
        ArrayList<ReglaProduccion> resultado = new ArrayList<ReglaProduccion>();
        for (ReglaProduccion buscando : reglasProduccion) {
            if (buscando.getSimboloGramatical().equals(simbolo)) {
                resultado.add(buscando);
            }
        }
        return resultado;
    }

    public String stringSimbolosNoTerminales() {
        String res = "";

        for (String simbolo : noTerminales) {
			res += simbolo + " ";
		}

        return res;
    }

    public String stringSimbolosTerminales() {
         String res = "";

        for (String simbolo : terminales) {
			res += simbolo + " ";
		}

        return res;       
    }

    public String stringGramatica() {
        String res = "";
		ArrayList<String> aux = new ArrayList<String>();
        for (ReglaProduccion regla : reglasProduccion) {
			res += regla.getSimboloGramatical() + " -> ";
			aux = regla.getProduccion();
			for (String simbolo : aux)
				res += simbolo + " ";
			res += "\n";
		}

        return res;
    }
    
    public int indiceReglaProduccion(ReglaProduccion temporalRP) {
      
        for (int j = 0; j < reglasProduccion.size(); j++) {
          ReglaProduccion conjuntoReglaProduccion=reglasProduccion.get(j);
          
          String simboloActual = conjuntoReglaProduccion.getSimboloGramatical();
          String simboloNuevo = temporalRP.getSimboloGramatical();
          ArrayList<String> produccionActual = conjuntoReglaProduccion.getProduccion();
          ArrayList<String> produccionNuevo = temporalRP.getProduccion();
          Boolean bandera2 = true;
    
          // Comparar si tienen el mismo largo
          if (produccionActual.size() == produccionNuevo.size()) {
            for (int i = 0; i < produccionActual.size(); i++) {
              // Comprobar elemento a elemento
              if (!(produccionActual.get(i).equals(produccionNuevo.get(i)) && simboloActual.equals(simboloNuevo))) {
                bandera2 = false;
                break;
              }
            }
            if (bandera2) { // El I actual es el mismo que el parametro y se termina el ciclo
                    // principal
              return j+1;
            }
          }
        }
        return -1; 
      }
    
}
