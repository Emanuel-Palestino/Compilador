package Utilidades.Gramatica;
import java.util.ArrayList;

public class Gramatica {
    private ArrayList<String> noTerminales;
    private ArrayList<String> terminales;
    private ArrayList<ReglaProduccion> reglasProduccion;
    private String simboloInicial;

    /*
    Regla de prodccion es una clase (string:simGram   arraylist<string>:producción    booleano:marcado)*/


    //Constructores
    public Gramatica(){
        noTerminales=null;
        terminales=null;
        reglasProduccion=null;
        simboloInicial=null;
    }
    //pasar gramatica entera
    public Gramatica(Gramatica a){
        noTerminales=a.noTerminales;
        terminales=a.terminales;
        reglasProduccion=a.reglasProduccion;
        simboloInicial=a.simboloInicial;
    }
    //pasar cada uno de los atributos
    public Gramatica(ArrayList<String>ter,ArrayList<String>noTer){
        noTerminales=noTer;
        terminales=ter;
        reglasProduccion=null;
        simboloInicial=null;
    }
    public Gramatica(ReglaProduccion regla){
        noTerminales=null;
        terminales=null;
        reglasProduccion=regla;
        simboloInicial=null;
    }
    public Gramatica(String simbolo){
        noTerminales=null;
        terminales=null;
        reglasProduccion=null;
        simboloInicial=simbolo;
    }

    //getter y setters

    public ArrayList<String> getNoTerminales(){return noTerminales;};
    public ArrayList<String> getTerminales(){return terminales;};
    public ArrayList<ReglaProduccion> getReglasProduccion(){return reglasProduccion;};
    public String getSimboloInicial(){return simboloInicial;}

    public ArrayList<String> setNoTerminales(ArrayList<String> noTerminales){this.noTerminales=noTerminales;}
    public ArrayList<String> setTerminales(ArrayList<String> terminales){this.terminales=terminales;}
    public ArrayList<ReglaProduccion> setReglasProduccion(ArrayList<ReglasProduccion> reglas){this.reglasProduccion=reglas;}
    public String setSimboloInicial(string simboloInicial){this.simboloInicial=simboloInicial;}




    //Métodos
    boolean esTerminal(String entrada1){
        Gramatica aux = new Gramatica();
        return aux.getTerminales().contains(entrada1);
    }
    ArrayList<ReglaProduccion> reglaDeSimbolo(String entrada2){
        //regresar arraylist de las reglas de produccion del string de entrada
        ArrayList<ReglaProduccion> busqueda = this.getReglasProduccion();
        ArrayList<ReglaProduccion> resultado = new ArrayList<ReglaProduccion>();
        for (int i : busqueda) {
            if(busqueda==entrada2){resultado.add(busqueda)}
        }
    }
}
