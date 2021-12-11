package Pruebas;

public class PruebaMatchCadena {
	
	public static void main(String[] args) {
		String cadena = "\"algo feo muy feooo\"";
		if (cadena.matches("(\"(.*)\")|('(.*)')")) {
			System.out.println("si");
		} else {
			System.out.println("no");
		}
	}
	
}
