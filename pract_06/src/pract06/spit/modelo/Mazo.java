package pract06.spit.modelo;

import pract06.IPila;
import pract06.PilaArrayAcotada;

/**
 * Mazo
 * 
 * Implementación de la clase mazo que representa un conjunto
 * acotado de cartas en la que sólo es accesible la última 
 * carta introducida.
 * 
 * @author Estructuras de Datos y <Clara Torre García-Barredo>
 * @version oct-2017
 */

public class Mazo {
	
	//  Conjunto de cartas del mazo
	private IPila<Carta> elMazo;

		
	/**
	 * Constructor
	 * @param numeroCartas del mazo
	 */
	public Mazo (int numeroCartas) { 
		elMazo = new PilaArrayAcotada<Carta>(numeroCartas);
	}

	
	/**
	 * Devuelve la carta superior pero sin
	 * sacarla del mazo.
	 * @return la carta situada en la parte superior
	 */
	public Carta cartaSuperior() { 
		return elMazo.cima();
	}

	
	/**
	 * Indica si el mazo contiene cartas
	 * @return verdadero si NO existen cartas en el mazo
	 */
	public boolean estaVacio() { 
		if(elMazo.tamanho() == 0) {
			return true;
		}
		return false;
	}

	
	/**
	 * Devuelve la carta superior y la saca
	 * del mazo.
	 * @return la carta situada en la parte superior
	 */
	public Carta cogeCarta() {
		return elMazo.desapila();
	}

	
	/**
	 * Anhade una carta en la parte superior de mazo.
	 * @param Carta que se desea añadir 
	 */
	public void anhadeCarta (Carta laCarta) { 
		elMazo.apila(laCarta);
	}
	
	/*
	 * ¿Cual sería la implementación de pila más apropiada para esta aplicación desde el
	 * punto de vista de eficiencia temporal? ¿Y desde la perspectiva de eficiencia espacial? 
	 * Razona brevemente tu respuesta.
	 * 
	 * Desde el punto de vista de eficiencia temporal sería mejor ListaArrayAcotada,
	 * porque a pesar de que las dos son O(1), las de array son más rápidas.
	 * Desde el punto de vista de eficiencia espacial sería mejor ListaSimpleEnlace, 
	 * porque tienes 12 mazos y 40 cartas, y con arrays defines todos los mazos a 40 cartas, 
	 * algo que sería difícil que pasase y que en general desaprovecha memoria.
	*/
	
	
}