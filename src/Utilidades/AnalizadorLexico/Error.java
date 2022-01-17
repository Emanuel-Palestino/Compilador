package Utilidades.AnalizadorLexico;

public class Error {
	
	private int linea;
	private String descripcion;

	public Error() {
		linea = 0;
		descripcion = "";
	}

	public Error(int linea, String descripcion) {
		this.linea = linea;
		this.descripcion = descripcion;
	}

	// Getters
	public int getLinea() {
		return linea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	// Setters
	public void setLinea(int linea) {
		this.linea = linea;
	}

	public void setDescripcion(String desc) {
		descripcion = desc;
	}

}
