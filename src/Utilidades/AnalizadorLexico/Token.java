package Utilidades.AnalizadorLexico;

public class Token {

	private int linea;
	private String lexema;
	private String token;
	private String valorLexico;

	public Token() {
		linea = 0;
		lexema = "";
		token = "";
		valorLexico = "";
	}

	public Token(int linea, String lexema, String token, String valorLexico) {
		this.linea = linea;
		this.lexema = lexema;
		this.token = token;
		this.valorLexico = valorLexico;
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

	public String getValorLexico(){
		return valorLexico;
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

	public void setValorLexico(String valorLexico){
		this.valorLexico = valorLexico;
	}
	
}
