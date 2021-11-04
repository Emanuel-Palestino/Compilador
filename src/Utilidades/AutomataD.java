package Utilidades;

import Utilidades.Listas.ListaDoblementeEnlazadaD;

public class AutomataD {
  
  private ListaDoblementeEnlazadaD[] adyacencia;


  public AutomataD() {
		adyacencia = null;
	}	


	// Getters and Setters



	public ListaDoblementeEnlazadaD getTransiciones(int estado) {
		return adyacencia[estado];
	}

}
