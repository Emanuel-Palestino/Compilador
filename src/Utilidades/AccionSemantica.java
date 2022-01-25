package Utilidades;

import Utilidades.Gramatica.SimboloGramatical;

enum TipoDato {
	CADENA, ATRIBUTO
}

enum Atributo {
	TRADUCCION, TEMPORAL, DESCONOCIDO, VALORLEXICO
}

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

	public SimboloGramatical evaluar(SimboloGramatical[] simbolos) {
		// Obtener if's si es que hay
		String[] secciones = accion.split("\\{|\\}");
		// Ejecutar cada seccion
		for (String seccion : secciones) {

			if (seccion.isEmpty())
				continue;

			// Obtener las partes de las condiciones si es que hay
			String[] condiciones = seccion.split("if|else");

			// Saber si hay condiciones o no
			if (condiciones.length == 1) { // no hay condiciones

				// Obtener la asignacion
				realizarAsignacion(condiciones[0], simbolos);


			} else { // Si hay condiciones
				// Obtener la condicion IF
				String[] condicionIf = condiciones[1].trim().split(" ", 4);

				// Validar condicion
				if (validarCondicion(obtenerSimboloGramatical(condicionIf[0], simbolos), condicionIf[0], condicionIf[1],
						condicionIf[2])) {
					// Obtener la asignacion
					realizarAsignacion(condicionIf[3], simbolos);
				} else {
					// Obtener la asignacion ELSE
					realizarAsignacion(condiciones[2].trim(), simbolos);
				}

			}
		}

		return simbolos[0];
	}

	private String[] obtenerAsignacion(String cadena) {
		return cadena.split(":=");
	}

	private String[] obtenerConcatenaciones(String cadena) {
		return cadena.split("\\|\\|");
	}

	private SimboloGramatical obtenerSimboloGramatical(String cadena, SimboloGramatical[] simbolos) {
		// Obtener el nombre del simbolo gramatical
		String simbolo = cadena.trim().split("\\.")[0];

		// Saber si es único o tiene parecidos en la regla de produccion
		if (!Character.isDigit(simbolo.charAt(simbolo.length() - 1))) { // Es único

			// Buscar en los simbolos
			for (SimboloGramatical simbol : simbolos) {
				if (simbol.getSimboloGramatical().equals(simbolo))
					return simbol;
			}
		} else { // No es único
			// Obtener el digito del simbolo
			int numeroSimboloGramatical = Character.getNumericValue(simbolo.charAt(simbolo.length() - 1));
			// Quitar el digito del id
			// simbolo = simbolo.substring(0, simbolo.length() - 1);
			simbolo.replaceAll("\\d", "");

			// Buscar el simbolo que corresponda
			int indexSimboloGramatical = 0;
			for (SimboloGramatical simbol : simbolos) {
				if (simbol.getSimboloGramatical().equals(simbolo)) {
					if (indexSimboloGramatical == numeroSimboloGramatical) {
						return simbol;
					}
					indexSimboloGramatical++;
				}
			}
		}

		return new SimboloGramatical();
	}

	private TipoDato obtenerTipo(String cadena) {
		String dato = cadena.trim();
		String[] cadenaa = dato.split("'");

		if ((cadenaa.length == 2 && cadenaa[0].equals("")) || cadenaa.length == 0) {
			return TipoDato.CADENA;
		} else {
			return TipoDato.ATRIBUTO;
		}
	}

	private Atributo obtenerAtributo(String cadena) {
		String aux = cadena.trim().split("\\.")[1];
		Atributo resultado = Atributo.DESCONOCIDO;
		switch (aux) {
			case "traduccion":
				resultado = Atributo.TRADUCCION;
				break;
			case "temporal":
				resultado = Atributo.TEMPORAL;
				break;
			case "valorLexico":
				resultado = Atributo.VALORLEXICO;
		}

		return resultado;
	}

	private boolean validarCondicion(SimboloGramatical simbolo, String operando1, String operador, String operando2) {
		boolean resultado = false;

		// Obtener el valor del atributo
		String valorOperando1 = "";
		// Actuar según el atributo
		switch (obtenerAtributo(operando1)) {
			case TRADUCCION:
				valorOperando1 = simbolo.getTraduccion();
				break;
			case TEMPORAL:
				valorOperando1 = simbolo.getTemporal();
				break;
			case VALORLEXICO:
				valorOperando1 = simbolo.getValorLexico();
				break;
			case DESCONOCIDO:
				System.out.println("Error: AccionSemantica:167");
		}

		// Obtener el valor del segundo operando
		String valorOperando2 = operando2.trim().split("'")[1];

		// Realizar la comparacion
		switch (operador) {
			case "==":
				if (valorOperando1.equals(valorOperando2))
					resultado = true;
				break;
			case "!=":
				if (!valorOperando1.equals(valorOperando2))
					resultado = true;
				break;
			default:
				System.out.println("error:validarCondicion");
		}

		return resultado;
	}

	private void realizarAsignacion(String asignacion, SimboloGramatical[] simbolos) {
		// Obtener la asignacion
		String[] partesAsignacion = obtenerAsignacion(asignacion);

		// Obtener el id y el valor
		String id = partesAsignacion[0];
		String valor = partesAsignacion[1];

		// Obtener el símbolo con el que se trabajará
		SimboloGramatical actual = obtenerSimboloGramatical(id, simbolos);

		// Obtener todas las concatenaciones que haya
		String[] concatenaciones = obtenerConcatenaciones(valor);

		// Recorrer todas las concatenaciones
		String valorAsignar = "";
		for (String parte : concatenaciones) {

			// Actuar según su tipo
			switch (obtenerTipo(parte)) {
				case CADENA:
					valorAsignar += parte.trim().split("'").length == 0 ? "" : parte.split("'")[1];
					break;

				case ATRIBUTO:
					SimboloGramatical simboloAtributo = obtenerSimboloGramatical(parte, simbolos);

					// Actuar según el atributo correcto
					switch (obtenerAtributo(parte)) {
						case TRADUCCION:
							valorAsignar += simboloAtributo.getTraduccion();
							break;
						case TEMPORAL:
							valorAsignar += simboloAtributo.getTemporal();
							break;
						case VALORLEXICO:
							valorAsignar += simboloAtributo.getValorLexico();
						case DESCONOCIDO:
							System.out.println("Error: AccionSemantica:228");
							break;
					}

					break;

				default:
					System.out.println("Tipo Desconocido");
					break;
			}

		}

		// Se le asigna al simbolo su traduccion dependiendo del atributo
		switch (obtenerAtributo(id)) {
			case TRADUCCION:
				actual.setTraduccion(valorAsignar);
				break;
			case TEMPORAL:
				actual.setTemporal(valorAsignar);
				break;
			case VALORLEXICO:
				actual.setValorLexico(valorAsignar);
				break;
			case DESCONOCIDO:
				System.out.println("Error: AccionSemantica: 253");
				break;
		}

	}

	public static void main(String[] args) {
		// Variables auxiliares

		SimboloGramatical simboloS = new SimboloGramatical("S");
		SimboloGramatical simboloV = new SimboloGramatical("V");
		simboloV.setTraduccion("traduccionV");

		SimboloGramatical[] simbolos = new SimboloGramatical[2];
		simbolos[0] = simboloS;
		simbolos[1] = simboloV;

		AccionSemantica accion = new AccionSemantica(
				"{ if V.traduccion != 'x' S.traduccion := V.traduccion || 'iDfentro' else S.traduccion := V.traduccion || 'elsDentro' } S.traduccion := S.traduccion || 'var ' || V.traduccion");
		accion.evaluar(simbolos);

		// Pruebas individuales
		String algo = " V'.traduccion ";
		System.out.println(accion.obtenerAtributo(algo));
	}

}
