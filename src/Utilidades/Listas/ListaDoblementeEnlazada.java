package Utilidades.Listas;

public class ListaDoblementeEnlazada {
	private NodoLista inicio;
	private NodoLista fin;

	public ListaDoblementeEnlazada() {
		inicio = null;
		fin = null;
	}

	public ListaDoblementeEnlazada(ListaDoblementeEnlazada l) {
		NodoLista estado;
		estado = l.inicio;
		while (estado != null) {
			this.insertar(estado.getEstadoDestino(), estado.getSimbolo());
			estado = estado.getSiguiente();
		}
	}

	// Getters and Setters
	public NodoLista getInicio() {
		return inicio;
	}

	public NodoLista getFinal() {
		return fin;
	}

	public void setInicio(NodoLista i) {
		inicio = i;
	}

	public void setFinal(NodoLista f) {
		fin = f;
	}

	// Metodos
	public void insertar(int estadoDestino, String simbolo) {
		NodoLista nuevo, actual, anterior;
		nuevo = new NodoLista(estadoDestino, simbolo);
		anterior = null;
		actual = new NodoLista();
		actual = this.inicio;

		// La segunda comparación es para que la lista quede ordeanda.
		while (actual != null && actual.getEstadoDestino() > estadoDestino) {
			anterior = actual;
			actual = actual.getSiguiente();
		}
		if (anterior == null) {
			if (actual == null) {
				this.inicio = nuevo;
				this.fin = nuevo;
			} else {
				nuevo.setSiguiente(actual);
				actual.setAnterior(nuevo);
				this.inicio = nuevo;
			}
		} else {
			if (actual != null) {
				nuevo.setSiguiente(actual);
				actual.setAnterior(nuevo);
				anterior.setSiguiente(nuevo);
				nuevo.setAnterior(anterior);
			} else {
				anterior.setSiguiente(nuevo);
				nuevo.setAnterior(anterior);
				this.fin = nuevo;
			}
		}
	}

	// Metodo eliminar
	// Metodo buscar

	//
	public void incrementarEstadosDestino(int incremento) {
		NodoLista estado;
		estado = this.inicio;
		while (estado != null) {
			estado.setEstadoDestino(estado.getEstadoDestino() + incremento);
			estado = estado.getSiguiente();
		}
	}

}
