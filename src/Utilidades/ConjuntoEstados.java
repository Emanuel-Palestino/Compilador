package Utilidades;

import java.util.ArrayList;

public class ConjuntoEstados {

	private String id;
	private ArrayList<Integer> estados;

	public ConjuntoEstados() {
		id = "";
		estados = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public ArrayList<Integer> getEstados() {
		return estados;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void insertarEstado(int estado) {
		estados.add(estado);
	}
	
}
