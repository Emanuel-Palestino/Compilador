package Utilidades.Excepciones;

public class ExcepcionLexico extends Exception{
	public int posicionCadena;
	public String simbolo;

	public ExcepcionLexico(int posicion, String simbolo) {
		super();
		posicionCadena = posicion;
		this.simbolo = simbolo;
	}
}
