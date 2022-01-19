package Utilidades;

import Utilidades.AnalizadorLexico.Simbolo;

public class AccionSemantica {

	private String accion;

	public AccionSemantica() {
		accion = "";
	}

	public AccionSemantica(String accion) {
		this.accion = accion;
	}

	public AccionSemantica(AccionSemantica copia) {
		this.accion = copia.accion;
	}

	// Get
	public String getAccion() {
		return accion;
	}

	// Set
	public void setAccion(String accion) {
		this.accion = accion;
	}

	// Metodos


	private void imprimir(String[] arr, String nombre) {
		System.out.println(nombre + ":");
		for(int i = 0; i < arr.length; i++) {
			System.out.println("[" + i + "] = " + arr[i]);
		}
		System.out.println();
	}

	public String evaluar(/* Simbolo[] simbolos */) {

		String traduccion = "";

		// Obtener if's si es que hay
		String[] secciones = accion.split("\\{|\\}");
		// Ejecutar cada seccion
		for (String seccion : secciones) {

			// Obtener las partes de las condiciones si es que hay
			String[] condiciones = seccion.split("if|else");
			
			// Saber si hay condiciones o no
			if (condiciones.length == 1) {		// no hay condiciones

				// Obtener la asignacion
				String[] partesAsignacion = obtenerAsignacion(condiciones[0]);

				// Obtener el id y el valor
				String id = partesAsignacion[0];
				String valor = partesAsignacion[1];


			}
		}

		return traduccion;
	}

	private String[] obtenerAsignacion(String cadena) {
		return cadena.split(":=");
	}

	private String[] obtenerConcatenaciones(String cadena) {
		return cadena.split("||");
	}

	private Simbolo simboloATrabajar(String cadena/* , Simbolo[] simbolos */) {

		Simbolo[] simbolos = new Simbolo[3];

		// Obtener el nombre del simbolo gramatical
		String simbolo = cadena.split(".")[0];

		// Saber si es único o tiene parecidos en la regla de produccion
		if (!Character.isDigit(simbolo.charAt(simbolo.length()))) {		// Es único

			// Buscar en los simbolos
			for (Simbolo simbol : simbolos) {
				if (simbol.getId().equals(simbolo))
					return simbol;
			}
		} else {		// No es único
			// Obtener el digito del simbolo
			int numeroSimbolo = Character.getNumericValue(simbolo.charAt(simbolo.length() - 1));
			// Quitar el digito del id
			//simbolo = simbolo.substring(0, simbolo.length() - 1);
			simbolo.replaceAll("\\d", "");

			// Buscar el simbolo que corresponda
			int indexSimbolo = 0;
			for (Simbolo simbol : simbolos) {
				if (simbol.getId().equals(simbolo)) {
					if (indexSimbolo == numeroSimbolo) {
						return simbol;
					}
					indexSimbolo++;
				}
			}
		}

		
		return new Simbolo();
	}

	public static void main(String[] args) {
		AccionSemantica accion = new AccionSemantica("{ if V.trad == '' V.temp := 'array' || V'.trad else V.temp := V'.trad } S.trad := 'var' || V.trad || ';'");
		accion.evaluar();
	}
	
}
