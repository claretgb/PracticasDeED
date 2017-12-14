package pract06.spit.modelo;

/**
 * Carta
 * 
 * Implementación de la clase Carta. En esta versión, 
 * una carta está caracterizada por tres atributos: 
 * número, color y si es visible (boca arriba). Sin
 * embargo, se consideran dos cartas iguales cuando 
 * tienen igual número, sin importar el resto de atributos 
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

import java.awt.Color;

public class Carta {

	// Atributos privados: número de carta, color y si está visible en el mazo 
	private boolean bocaArriba;
	private int numero;
	private Color color;

	// Constructor
	public Carta (int numeroCarta, Color colorCarta) 
	{ 
		numero = numeroCarta;
		color = colorCarta;
		bocaArriba = false; 
	}

	/**
	 * Indica el número de la carta
	 * @return un entero con el número de la carta
	 */
	public int numeroCarta () { 
		return numero; 
	}

	/**
	 * Indica si la carta es visible para el usuario
	 * (boca arriba)
	 * @return verdarero si la carta está boca arriba
	 */
	public boolean estaBocaArriba()	{ 
		return bocaArriba; 
	}

	/**
	 * Da la vuelta a la carta, dejandola en el estado
	 * opuesto en el que estaba.
	 * 
	 */
	public void darVuelta() { 
		bocaArriba = !bocaArriba; 
	}

	/**
	 * Devuelve el color de la carta si está boca arriba. 
	 * En caso contrario, devuelve el color del reverso.
	 * @return el color de la carta
	 */
	public Color color() {
		if (estaBocaArriba())
			return color;
		else
			return Color.BLUE; // Color del reverso de la carta
		}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Carta other = (Carta) obj;
		if (numero != other.numero) // Dos cartas son iguales si tienen igual numero
			return false;
		return true;
	}
	
}
