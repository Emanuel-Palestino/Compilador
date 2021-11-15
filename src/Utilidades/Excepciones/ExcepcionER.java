package Utilidades.Excepciones;

public class ExcepcionER extends Exception{

	public ExcepcionER() {
		super();
	}

	@Override
	public String getMessage() {
		return "Error: Expresión Regular mal escrita.";
	}
	
}
