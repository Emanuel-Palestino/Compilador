package Utilidades;

public class ResultadoAnalisisLexico {

	private String[][] tokens;
	private String[][] simbolos;
	private String[][] errores;


	// Getters and Setters
	public String[][] getTokens() {
		return tokens;
	}

	public String[][] getSimbolos() {
		return simbolos;
	}

	public String[][] getErrores() {
		return errores;
	}

	public void setTokens(String[][] tiraTokens) {
		tokens = tiraTokens;
	}

	public void setSimbolos(String[][] tablaSimbolos) {
		simbolos = tablaSimbolos;
	}

	public void setErrores(String[][] tablaErrores) {
		errores = tablaErrores;
	}
	
}
