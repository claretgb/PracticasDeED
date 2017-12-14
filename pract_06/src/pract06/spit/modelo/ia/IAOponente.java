package pract06.spit.modelo.ia;

import pract06.spit.modelo.Tablero;

/**
 * IAOponente
 * 
 * Implementación de la clase IAOponente que representa
 * la inteligencia artificial del jugador que controla el
 * ordenador.
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

public class IAOponente extends Thread {
	
	//  Tablero dónde se realizan las jugadas
	private Tablero elTablero = null;
	//  Nivel de dificultad como el tiempo que tarda la IA en realizar un movimiento
	private int tiempoEntreJugadas = 0;
	public enum Dificultad { DIFICIL, INTERMEDIO, FACIL }
	
	/**
	 * Constructor
	 */
	public IAOponente (Tablero tablero, Dificultad dificultad) {
		elTablero = tablero;
		switch (dificultad) {
		case DIFICIL:
			tiempoEntreJugadas = 600; // en ms
		case INTERMEDIO:
			tiempoEntreJugadas = 800; // en ms
		case FACIL:
			tiempoEntreJugadas = 1200; // en ms
		}
		this.start(); // iniciamos la actividad del jugador2
	}
	
	// Actividad
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(!Thread.interrupted()){
			siguienteJugada();
			try {sleep(tiempoEntreJugadas);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	/**
	 * Detiene los movimientos del jugador controlado por IA
	 */
	public void termina() {
		this.interrupt();
	}
	
	/**
	 * La IA realiza su siguiente jugada en el juego. En esta version, 
	 * la inteligencia se reduce a escoger un mazo aleatoriamente e intentar
	 * mover su carta superior. 
	 */
	private void siguienteJugada() {
		// Seleccionamos el mazo con el que realizar la jugada. Dado
		// que somos el jugador2, el número estará entre 4 y 7
		int numMazo = (int) (Math.round((Math.random()*3.0)) + 4);
		elTablero.realizaMovimiento (numMazo);			
	}

}
