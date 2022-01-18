package Utilidades.Gramatica;

public class SimboloGramatical {
    private String simboloGramatical;
    private String almacenadorTraduccion;

    //constructor
    public SimboloGramatical(String simbolo){
        simboloGramatical = simbolo;
    }

    //setters
    public void setSimboloGramatical(String simbolo){
        this.simboloGramatical = simbolo;
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
