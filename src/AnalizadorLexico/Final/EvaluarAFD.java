package AnalizadorLexico.Final;

import java.util.ArrayList;

import AnalizadorLexico.AlgoritmoThompson.Thompson;
import AnalizadorLexico.ConversionAFD.ConvierteAFD;
import AnalizadorLexico.ConversionAFD.Mueve;
import Utilidades.Automata;
import Utilidades.ConjuntoEstados;
import Utilidades.Excepciones.ExcepcionLexico;
import Utilidades.Listas.ListaDoblementeEnlazadaD;

public class EvaluarAFD {

	public static boolean evaluar(ArrayList<ListaDoblementeEnlazadaD> AFD, String cadena) throws ExcepcionLexico {
		ConjuntoEstados estado = AFD.get(0).getEstado();
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

	public static void main(String[] args) throws ExcepcionLexico {
		Thompson thomp = new Thompson();
		Automata afn = thomp.evaluarER("(a|b)*ªaªbªb", "ab");
		ConvierteAFD convierte = new ConvierteAFD();
		ArrayList<ListaDoblementeEnlazadaD> AFD = convierte.convierteAFD(afn);
		try {
			if (EvaluarAFD.evaluar(AFD, "bbbdab"))
				System.out.println("simon");
			else
				System.out.println("no");
		} catch (ExcepcionLexico e) {
			System.out.println("Simbolo imválido [" + e.simbolo + "] en posicion " + e.posicionCadena);
		}

	}

}
