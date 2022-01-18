package Utilidades.Gramatica;

public class SimboloGramatical {
    private String simboloGramatical;
    private String almacenadorTraduccion;

    //constructor
    public SimboloGramatical(){
        simboloGramatical = new String();
        almacenadorTraduccion = new String();
    }

    public SimboloGramatical(String simbolo){
        simboloGramatical = simbolo;
    }

    public SimboloGramatical( SimboloGramatical simboloACopiar){
        this.simboloGramatical = simboloACopiar.simboloGramatical;
        this.almacenadorTraduccion = simboloACopiar.almacenadorTraduccion;
    }

    //setters
    public void setSimboloGramatical(String simboloGramatical){
        this.simboloGramatical = simboloGramatical;
    }
    public void setTraducir(String traduccion){
        this.almacenadorTraduccion = traduccion;
    }

    //getters
    public String getSimboloGramatical(){
        return simboloGramatical;
    }
    public String getTraducir(){
        return almacenadorTraduccion;
    }


}
