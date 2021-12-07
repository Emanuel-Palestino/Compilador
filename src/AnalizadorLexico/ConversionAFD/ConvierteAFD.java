package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;

import Utilidades.ConjuntoEstados;
import Utilidades.Alfabeto.Alfabeto;
import Utilidades.Automatas.Automata;
import Utilidades.Automatas.AutomataDeterminista;
import Utilidades.Listas.ListaDoblementeEnlazadaD;

public class ConvierteAFD {

	public ArrayList<ListaDoblementeEnlazadaD> convierteAFD(Automata automata) {

		ArrayList<ListaDoblementeEnlazadaD> tranD = new ArrayList<ListaDoblementeEnlazadaD>();
		ArrayList<ConjuntoEstados> estadosD = new ArrayList<ConjuntoEstados>();
		char letra = 'A';

		// Suponiendo que Rugal hara un metodo cerradura para la clase cerraduraEpsilon
		ConjuntoEstados inicio = new ConjuntoEstados();
		inicio.insertarEstado(0);
		estadosD.add(CerraduraEpsilon.doCerraduraEpsilon(inicio, automata));
		estadosD.get(0).setId("A");

		// Comprobar si el primer estado es un estado final
		if (estadosD.get(0).getEstados().contains(automata.getNumEstados() - 1))
			estadosD.get(0).setEstadoFinal(true);

		// recorrer la lista; mientras no haya marcados o llegemos al final de la lista
		for (int i = 0; i < estadosD.size() && estadosD.get(i).getMarcado() == false; i++) {

			estadosD.get(i).setMarcado(true); // Se marca el estado T

			// Obtener transiciones del conjunto de estados
			ArrayList<String> transiciones = new ArrayList<String>();
			for (Integer estado : estadosD.get(i).getEstados()) {
				for (String transicion : automata.getSimbolosTransiciones(estado)) {
					if (!transicion.equals("Ɛ") && !transiciones.contains(transicion))
						transiciones.add(transicion);
				}
			}

			// Comprobar inconsistencias en las trancisiones

			for (int k = 0; k < transiciones.size(); k++) {

				ConjuntoEstados U = new ConjuntoEstados();
				// Suponiendo que Roborto hace un metodo mover para la clase mueve
				U = CerraduraEpsilon.doCerraduraEpsilon(Mueve.mueve(estadosD.get(i), transiciones.get(k), automata),
						automata);
				Boolean bandera = true;
				for (ConjuntoEstados a : estadosD) {
					if (U.getEstados().equals(a.getEstados())) {
						U = a;
						bandera = false;
					}
				}
				if (bandera) { // Index of regresa -1 si U no se encuentra en el Arreglo

					letra++;
					U.setId(letra + "");
					U.setMarcado(false);
					// Lo marca si es un estado final
					if (U.getEstados().contains(automata.getNumEstados() - 1))
						U.setEstadoFinal(true);

					estadosD.add(U);
				}

				if (i == tranD.size()) {
					ListaDoblementeEnlazadaD lista = new ListaDoblementeEnlazadaD();
					lista.insertar(U, transiciones.get(k));
					lista.setEstado(estadosD.get(i));
					tranD.add(lista);
				} else {
					tranD.get(i).insertar(U, transiciones.get(k));
				}

			}
		}

		return tranD;

	}

	public static AutomataDeterminista convierte(Automata AFN) {
		AutomataDeterminista resultado = new AutomataDeterminista();

		// Copiar alfabeto al nuevo automata
		resultado.setAlfabeto(new Alfabeto(AFN.getAlfabeto()));

		ArrayList<ListaDoblementeEnlazadaD> adyacencia = new ArrayList<ListaDoblementeEnlazadaD>();
		ArrayList<ConjuntoEstados> estadosD = new ArrayList<ConjuntoEstados>();

		// Variable para ir nombrando a los estados
		Character letra = 'A';

		// Iniciar Estados Deterministas
		ConjuntoEstados inicio = new ConjuntoEstados();
		inicio.insertarEstado(0);
		estadosD.add(CerraduraEpsilon.doCerraduraEpsilon(inicio, AFN));

		// Poner id al primer estado
		inicio = estadosD.get(0);
		inicio.setId(letra.toString());

		// Comprobar si el primer estado es un estado final
		if (inicio.getEstados().contains(AFN.getNumEstados() - 1))
			inicio.setEstadoFinal(true);

		ListaDoblementeEnlazadaD ini = new ListaDoblementeEnlazadaD();
		ini.setEstado(inicio);
		adyacencia.add(ini);

		// Obtener Alfabeto del AFD
		Alfabeto alfabeto = resultado.getAlfabeto();

		// Recorrer todos los estados no marcados
		for (int i = 0; i < estadosD.size(); i++) {
			// Se marca el estado que estamos visitando
			estadosD.get(i).setMarcado(true);

			// Obtener transiciones del conjunto de estados
			ArrayList<String> transiciones = new ArrayList<String>();
			for (int estado : estadosD.get(i).getEstados()) {
				for (String transicion : AFN.getSimbolosTransiciones(estado)) {
					if (!transicion.equals("Ɛ") && !transiciones.contains(transicion))
						transiciones.add(transicion);
				}
			}

			// Comprobar inconsistencias en las transiciones
			// Digitos
			if (transiciones.contains("digito")) {
				// obtener todos los digitos validos
				ArrayList<Character> digitos = new ArrayList<Character>();
				for (String digito : transiciones) {
					if (digito.length() == 1 && alfabeto.digitoValido(digito) && !digitos.contains(digito.charAt(0)))
						digitos.add(digito.charAt(0));
				}

				// Crear nuevos tipos de transiciones
				int indexDigito = transiciones.indexOf("digito");
				for (Character digito : digitos) {
					String tran = "digito-" + digito;
					transiciones.add(indexDigito++, tran);
					if (!alfabeto.getLista().contains(tran))
						alfabeto.getLista().add(tran);
				}

				// Eliminar transicion "digito"
				if (digitos.size() > 0)
					transiciones.remove("digito");
			}
			// Letras
			if (transiciones.contains("letra")) {
				// obtener todas las letras validas
				ArrayList<Character> letras = new ArrayList<Character>();
				for (String letter : transiciones) {
					if (letter.length() == 1 && alfabeto.letraValido(letter) && !letras.contains(letter.charAt(0)))
						letras.add(letter.charAt(0));
				}

				// Crear nuevos tipos de transiciones
				int indexLetra = transiciones.indexOf("letra");
				for (Character letter : letras) {
					String tran = "letra-" + letter;
					transiciones.add(indexLetra++, tran);
					if (!alfabeto.getLista().contains(tran))
						alfabeto.getLista().add(tran);
				}

				// Elimina transicion "letra"
				if (letras.size() > 0)
					transiciones.remove("letra");
			}
			// Todo
			if (transiciones.contains("todo")) {
				// obtener todo valido
				ArrayList<String> todo = new ArrayList<String>();
				for (String letter : transiciones) {
					if (!letter.equals("todo") && !todo.contains(letter))
						todo.add(letter);
				}

				// Crear nuevos tipos de transiciones
				int indexTodo = transiciones.indexOf("todo");
				for (String algo : todo) {
					String tran = "todo-" + algo;
					transiciones.add(indexTodo++, tran);
					if (!alfabeto.getLista().contains(tran))
						alfabeto.getLista().add(tran);
				}

				// Elimina transicion "letra"
				if (todo.size() > 0)
					transiciones.remove("todo");
			}

			// Recorrer todas las transiciones
			for (String transicion : transiciones) {
				ConjuntoEstados U = new ConjuntoEstados();
				U = CerraduraEpsilon.doCerraduraEpsilon(Mueve.mueve(estadosD.get(i), transicion, AFN), AFN);

				// Comprobar si U está en estadosDeterministas
				Boolean bandera = false;
				for (ConjuntoEstados a : estadosD) {
					if (U.getEstados().equals(a.getEstados())) {
						U = a;
						bandera = true;
						break;
					}
				}
				if (!bandera) { // Si U no está en estadosDeterministas
					letra++;
					U.setId(letra.toString());
					U.setMarcado(false);

					// Comprueba si es un estado final
					if (U.getEstados().contains(AFN.getNumEstados() - 1))
						U.setEstadoFinal(true);

					estadosD.add(U);

					ListaDoblementeEnlazadaD lista = new ListaDoblementeEnlazadaD();
					lista.setEstado(U);
					adyacencia.add(lista);
					System.out.println("Añadir U a estdos D no marcado");
				}

				ListaDoblementeEnlazadaD adya = null;
				for (ListaDoblementeEnlazadaD T : adyacencia) {
					if (T.getEstado().equals(estadosD.get(i))) {
						adya = T;
						break;
					}
				}
				adya.insertar(U, transicion);

			}

		}

		// Insertar adyacencia en el automata
		resultado.insertarAdyacencia(adyacencia.toArray(new ListaDoblementeEnlazadaD[0]));
		resultado.setTotalEstados(adyacencia.size());

		return resultado;
	}
}
