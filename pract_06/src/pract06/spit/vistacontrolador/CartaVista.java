package pract06.spit.vistacontrolador;

/**
 * CartaVista
 * 
 * Implementación de la clase CartaVista, que representa
 * la forma de dibujar una carta en la interfaz grafica 
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

import java.awt.Color;

import pract06.spit.modelo.Carta;
import fundamentos.*;

public class CartaVista {
	
	/**
	 * Dibuja la carta en el panel definido por dib en las coordenadas indicadas 
	 * @param laCarta: carta a dibujar
	 * @param x: posicion en el eje horizontal
	 * @param y: posicion en el eje vertical
	 * @param dib: panel de dibujo
	 */
	public static void dibuja (Carta laCarta, int x, int y, Dibujo dib) {

		if (laCarta.estaBocaArriba()) {
			int numeroCarta = laCarta.numeroCarta() + 1;
			char color;
			if (laCarta.color()==Color.BLACK)
				color='b';
			else color='r';
			dib.dibujaImagen(x,y,"src/img/"+color+numeroCarta+".png");
		}
		else { // Carta boca abajo
			dib.dibujaImagen(x,y,"src/img/bocaAbajo.png");
		}
	}
}
