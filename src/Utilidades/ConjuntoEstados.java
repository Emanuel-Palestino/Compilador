package Utilidades;

import java.util.ArrayList;

public class ConjuntoEstados {

	private Boolean marcado;
	private String id;
	private ArrayList<Integer> estados;
	private Boolean estadoFinal;

	public ConjuntoEstados() {
		marcado = false;
		id = "";
		estados = new ArrayList<>();
		estadoFinal = false;
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

	public void setEstadoFinal(Boolean fin) {
		estadoFinal = fin;
	}

	public void insertarEstado(int estado) {
		estados.add(estado);
	}

	public Boolean esFinal() {
		return estadoFinal;
	}

}
