package Utilidades.Gramatica;

import Utilidades.AnalizadorLexico.Token;

public class SimboloGramatical {
    private String simboloGramatical;
    private String traduccion;
    private String valorLexico;
    private String temporal;

    //constructor
    public SimboloGramatical(){
        simboloGramatical = "";
        traduccion = "";
        valorLexico="";
        temporal="";
        temporal="";
    }

    public SimboloGramatical(String simbolo){
        simboloGramatical = simbolo;
        traduccion = "";
        valorLexico="";
        temporal="";
        temporal="";
    }

    //Constructores de copias
    public SimboloGramatical( SimboloGramatical simboloACopiar){
        this.simboloGramatical = simboloACopiar.simboloGramatical;
        this.traduccion = simboloACopiar.traduccion;
    }
    public SimboloGramatical( Token tokenACopiar){
        this.valorLexico = tokenACopiar.getValorLexico();
    }

    //setters
    public void setSimboloGramatical(String simboloGramatical){
        this.simboloGramatical = simboloGramatical;
    }
    public void setTraduccion(String traduccion){
        this.traduccion = traduccion;
    }
    public void setValorLexico(String valor){
        this.valorLexico = valor;
    }
    public void setTemporal(String temporal){
        this.temporal = temporal;
    }

    //getters
    public String getSimboloGramatical(){
        return simboloGramatical;
    }
    public String getTraduccion(){
        return traduccion;
    }
    public String getValorLexico(String valor){
        return  valorLexico;
    }
    public String getTemporal(String temporal){
        return temporal;
    }


}
