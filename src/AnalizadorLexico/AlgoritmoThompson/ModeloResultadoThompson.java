package AnalizadorLexico.AlgoritmoThompson;

public class ModeloResultadoThompson {

	private String alfabeto;
	private String expresionRegular;
	private String[] encabezado;
	private String[][] datos;

	public ModeloResultadoThompson(String alfab, String expresionR, String[] encabezado, String[][] datos) {
		alfabeto = alfab;
		expresionRegular = expresionR;
		this.encabezado = encabezado;
		this.datos = datos;
	}

	// Getters and Setters
	public String getAlfabeto() {
		return alfabeto;
	}

	public String getExpresionRegular() {
		return expresionRegular;
	}

	public String[] getEncabezado() {
		return encabezado;
	}

	public String[][] getDatos() {
		return datos;
	}
	
}
