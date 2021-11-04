package Utilidades.Listas;

import Utilidades.ConjuntoEstados;

public class ListaDoblementeEnlazadaD {
  
  private NodoListaD inicio;
  private NodoListaD fin;


  public ListaDoblementeEnlazadaD() {
    inicio = null;
    fin = null;
  }

	// Getters and Setters
	public NodoListaD getInicio() {
		return inicio;
	}

	public NodoListaD getFinal() {
		return fin;
	}

	public void setInicio(NodoListaD i) {
		inicio = i;
	}

	public void setFinal(NodoListaD f) {
		fin = f;
	}

  public void insertar(ConjuntoEstados estadoDestino, String simbolo) {
		NodoListaD nuevo, actual, anterior;
		nuevo = new NodoListaD(estadoDestino, simbolo);
		anterior = null;
		actual = new NodoListaD();
		actual = this.inicio;

		if (anterior == null) {
			if (actual == null) {
				this.inicio = nuevo;
				this.fin = nuevo;
			} else {
				nuevo.setSiguiente(actual);
				actual.setAnterior(nuevo);
				this.inicio = nuevo;
			}
		}
	}

}
