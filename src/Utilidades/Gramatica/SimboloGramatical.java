package Utilidades.Gramatica;

public class SimboloGramatical {
    private String simboloGramatical;
    private String traduccion;

    //constructor
    public SimboloGramatical(){
        simboloGramatical = "";
        traduccion = "";
    }

    public SimboloGramatical(String simbolo){
        simboloGramatical = simbolo;
    }

    public SimboloGramatical( SimboloGramatical simboloACopiar){
        this.simboloGramatical = simboloACopiar.simboloGramatical;
        this.traduccion = simboloACopiar.traduccion;
    }

    //setters
    public void setSimboloGramatical(String simboloGramatical){
        this.simboloGramatical = simboloGramatical;
    }
    public void setTraduccion(String traduccion){
        this.traduccion = traduccion;
    }

    //getters
    public String getSimboloGramatical(){
        return simboloGramatical;
    }
    public String getTraduccion(){
        return traduccion;
    }


}
