package AnalizadorLexico.Final;

import AnalizadorLexico.AlgoritmoThompson.Thompson;
import AnalizadorLexico.ConversionAFD.ConvierteAFD;
import AnalizadorLexico.ConversionAFD.Mueve;
import Utilidades.Automata;
import Utilidades.AutomataDeterminista;
import Utilidades.ConjuntoEstados;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Excepciones.ExcepcionLexico;

public class EvaluarAFD {

	public static boolean evaluar(AutomataDeterminista AFD, String cadena) throws ExcepcionLexico {
		ConjuntoEstados estado = AFD.getTransiciones(0).getEstado();
		String caracter;
		for (int i = 0; i < cadena.length(); i++) {
			caracter = cadena.charAt(i) + "";
			estado = Mueve.mueve(estado, caracter, AFD);

			// comprobar si es un símbolo valido
			if (estado.getEstados().size() == 0)
				throw new ExcepcionLexico(i, caracter);

		}
		if (estado.esFinal())
			return true;
		return false;
	}

	public static void main(String[] args) throws ExcepcionLexico, ExcepcionER {
		Thompson thomp = new Thompson();
		Automata afn = thomp.evaluarER("┌ a ı b ┐ × ┼ a ┼ b ┼ b", "a b");
		AutomataDeterminista AFD = ConvierteAFD.convierte(afn);
		try {
			if (EvaluarAFD.evaluar(AFD, "bbbabb"))
				System.out.println("simon");
			else
				System.out.println("no");
		} catch (ExcepcionLexico e) {
			System.out.println("Simbolo imválido [" + e.simbolo + "] en posicion " + e.posicionCadena);
		}

	}

}
