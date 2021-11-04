package Utilidades;

import java.util.ArrayList;

public class ConjuntoEstados {

	private Boolean marcado;
	private String id;
	private ArrayList<Integer> estados;

	public ConjuntoEstados() {
		marcado = false;
		id = "";
		estados = new ArrayList<>();
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
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
