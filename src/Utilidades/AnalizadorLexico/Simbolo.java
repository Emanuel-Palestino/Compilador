package Utilidades.AnalizadorLexico;

public class Simbolo {

	private String id;
	private String valor;
	private String funcion;

	public Simbolo() {
		id = "";
		valor = "";
		funcion = "";
	}

	public Simbolo(String id, String valor, String funcion) {
		this.id = id;
		this.valor = valor;
		this.funcion = funcion;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getValor() {
		return valor;
	}

	public String getFuncion() {
		return funcion;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	
}
