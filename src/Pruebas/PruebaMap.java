package Pruebas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PruebaMap {

	public static void main(String[] args) {

		ArrayList<Map<String, String>> acciones = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, String> aux = new HashMap<String, String>();
			aux.put("id", "");
			aux.put("+", "");
			aux.put("(", "");
			aux.put(")", "");
			aux.put("$", "");
			acciones.add(aux);
		}

		acciones.get(0).replace("id", "nuevo");
		System.out.println("");

	}

}
