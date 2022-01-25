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
    }

    public SimboloGramatical(String simbolo){
        simboloGramatical = simbolo;
        traduccion = "";
        valorLexico="";
        temporal="";
    }

    //Constructores de copias
    public SimboloGramatical( SimboloGramatical simboloACopiar){
        this.simboloGramatical = simboloACopiar.simboloGramatical;
        this.traduccion = simboloACopiar.traduccion;
        this.valorLexico = simboloACopiar.valorLexico;
        this.temporal = simboloACopiar.temporal;
    }
    public SimboloGramatical( Token tokenACopiar){
        this.valorLexico = tokenACopiar.getValorLexico();
        this.simboloGramatical = tokenACopiar.getToken();
        this.traduccion = "";
        this.temporal="";
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
    public String getValorLexico(){
        return  valorLexico;
    }
    public String getTemporal(){
        return temporal;
    }


}
