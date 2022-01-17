package Utilidades.AnalizadorLexico;

import java.util.ArrayList;

public class ResultadoAnalisisLexico {

	private ArrayList<Token> tokens;
	private ArrayList<Simbolo> simbolos;
	private ArrayList<Error> errores;

	// Getters and Setters
	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public ArrayList<Simbolo> getSimbolos() {
		return simbolos;
	}

	public ArrayList<Error> getErrores() {
		return errores;
	}

	public void setTokens(ArrayList<Token> tiraTokens) {
		tokens = tiraTokens;
	}

	public void setSimbolos(ArrayList<Simbolo> tablaSimbolos) {
		simbolos = tablaSimbolos;
	}

	public void setErrores(ArrayList<Error> tablaErrores) {
		errores = tablaErrores;
	}

	public ArrayList<String> getTiraTokens() {
		ArrayList<String> resultado = new ArrayList<String>();
		for (Token token : tokens)
			resultado.add(token.getToken());
		return resultado;
	}

	// Metodos
	public String[][] getTokensTabla() {
		String[][] res = new String[tokens.size()][3];
		for (int i = 0; i < tokens.size(); i++) {
			res[i][0] = String.valueOf(tokens.get(i).getLinea());
			res[i][1] = tokens.get(i).getLexema();
			res[i][2] = tokens.get(i).getToken();
		}

		return res;
	}

	public String[][] getSimbolosTabla() {
		String[][] res = new String[simbolos.size()][3];
		for (int i = 0; i < simbolos.size(); i++) {
			res[i][0] = simbolos.get(i).getId();
			res[i][1] = simbolos.get(i).getValor();
			res[i][2] = simbolos.get(i).getFuncion();
		}

		return res;
	}

	public String[][] getErroresTabla() {
		String[][] res = new String[errores.size()][2];
		for (int i = 0; i < errores.size(); i++) {
			res[i][0] = String.valueOf(errores.get(i).getLinea());
			res[i][1] = errores.get(i).getDescripcion();
		}

		return res;
	}

}
