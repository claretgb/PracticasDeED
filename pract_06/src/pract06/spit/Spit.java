package pract06.spit;

/**
 * Spit
 * 
 * Programa que implementa el juego "Spit" 
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

import pract06.spit.modelo.*;
import pract06.spit.modelo.ia.*;
import pract06.spit.modelo.ia.IAOponente.Dificultad;
import pract06.spit.vistacontrolador.*;

public class Spit {

	/**
	 * Clase principal que crea los elementos correspondientes al código de negocio y
	 * a la vista gráfica del desarrollo del juego
	 * @param args
	 */
	public static void main(String[] args) {

		Dificultad dificultad = Dificultad.INTERMEDIO; // Grado de dificultad (dificil, intermedio, fácil);
		Tablero tablero = new Tablero ();
		TableroVista vistaTablero = new TableroVista (tablero);
		
		// Iniciamos una nueva partida
		tablero.NuevoJuego();
		
		// Interfaz gráfica
		GUI hmi = new GUI (vistaTablero);
		
		// AI oponente
		IAOponente jugador2 = new IAOponente (tablero, dificultad);
		
		// Main loop
		boolean continuar = true;
		while (continuar) {
			int numBoton = hmi.obtenMovimiento();
			if (!tablero.continuarJuego()) 	// Compruebo estado del juego despues de la llamada bloqueante anterior
				numBoton = 0; 				// Caso especial: el juego ha terminado
			switch (numBoton) {
			case 1: case 2: case 3: case 4: // Un boton de mazo del juego ha sido pulsado
				if (!tablero.realizaMovimiento (numBoton-1)) //  El numero de mazo empieza en 0
					hmi.PintaMensaje("Mover la carta del mazo "+(numBoton)+" no es posible");
				break;
			case 5:  						// Un boton de mazo de cartas extras ha sido pulsado
				continuar = tablero.cartaNueva();
				break;
			default:
				break;
			}
			if (continuar) 					// Compruebo estado del juego despues del movimiento de cartas
				continuar = tablero.continuarJuego();
		}
		//  El juego ha terminado (siempre después de que se pulse un boton)
		jugador2.termina();
		hmi.VentanaMensaje("¡¡JUEGO TERMINADO!!");
		hmi.termina();
	}

}
