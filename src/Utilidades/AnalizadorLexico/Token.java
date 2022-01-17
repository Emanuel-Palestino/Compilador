package Utilidades.AnalizadorLexico;

public class Token {

	private int linea;
	private String lexema;
	private String token;

	public Token() {
		linea = 0;
		lexema = "";
		token = "";
	}

	public Token(int linea, String lexema, String token) {
		this.linea = linea;
		this.lexema = lexema;
		this.token = token;
	}

	// Getters
	public int getLinea() {
		return linea;
	}

	public String getLexema() {
		return lexema;
	}

	public String getToken() {
		return token;
	}

	// Setters
	public void setLinea(int linea) {
		this.linea = linea;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
