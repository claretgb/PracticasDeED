package pract06.spit.modelo;

/**
 * Tablero
 * 
 * Implementación de la clase Tablero que representa
 * todos los elementos necesarios para el juego de cartas 
 * "Spit" (dos mazos de descartes, un mazo de cartas extras
 * por jugador y 4 mazos de juego por jugador) y las operaciones
 * permitidas (movimiento de cartas entre mazos o sacar cartas nuevas).
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

import java.awt.Color;
import java.util.*;

public class Tablero {
	
	private Mazo mazoDescartes[] = new Mazo [2];
	private Mazo mazoJuegoJug1[] = new Mazo [4];
	private Mazo mazoJuegoJug2[] = new Mazo [4];
	private Mazo mazoReserva  [] = new Mazo [2];
	
	// Constante de configuracion: baraja de 40 cartas
	private final int NUM_CARTAS = 40; 
	
	/**
	 * Constructor
	 */
	public Tablero () {
		// Creamos todos los mazos necesarios para el juego por tipo
		MazosEnTablero (mazoDescartes);
		MazosEnTablero (mazoJuegoJug1);
		MazosEnTablero (mazoJuegoJug2);
		MazosEnTablero (mazoReserva);
	}
	
	/**
	 * Inicia un nuevo juego en el tablero: crea una baraja, reparte
	 * las cartas entre los distintos mazos y cambia la visibilidad de
	 * la última carta de los mazos correspondientes (descartes y juego).
	 * 
	 */
	public void NuevoJuego () {			
		//  Nueva baraja
	    ArrayList<Carta> baraja = nuevaBaraja(NUM_CARTAS);
		
        // Reparto de cartas
	    repartoCartasEnMazos(baraja);
	    
	    //Primer carta boca arriba de cada mazo del juego
		for (int index = 0; index < mazoJuegoJug1.length; index++) {
			mazoJuegoJug1[index].cartaSuperior().darVuelta();
			mazoJuegoJug2[index].cartaSuperior().darVuelta();
		}
	}

	/**
	 * Realiza un movimiento del juego utilizando la carta
	 * superior del mazo indicado. En esta versión, el movimiento
	 * se realiza sobre la primera opción disponible. 
	 * @param el numero del mazo que inicia el movimiento
	 * @return verdadero si existe jugada con la carta
	 */
	public synchronized boolean realizaMovimiento(int numeroMazo) {
		boolean hayJugada = false;
		int index = 0;
		Mazo destino = null;
		//  El número de mazo distingue el jugador que quiere realizar el movimiento
		Mazo origen  = obtenMazo(numeroMazo);
		
		// Primero comprobamos si es posible realizar un descarte
		while (!hayJugada && index < mazoDescartes.length) {
			destino = mazoDescartes[index];
			hayJugada = ReglasJuego.movimientoPermitidoSecuencia(origen, destino);
			index++;
		}
		
		// Si no hay jugada de descarte, intentamos intercambiar la carta con otro mazo del jugador
		if (!hayJugada) {
			index = 0;
			while (!hayJugada && index < mazoJuegoJug1.length) {
				// No pongo carta sobre el mismo mazo
				if (index != numeroMazo%mazoJuegoJug1.length) {
					if (numeroMazo<mazoJuegoJug1.length)
						destino = obtenMazo(index);
					else destino = obtenMazo(index+mazoJuegoJug1.length);
					hayJugada = ReglasJuego.movimientoPermitidoIguales(origen, destino);
				}
				index++;
			}
		}
		
		// Si existe jugada permitida, hazla efectiva
		if (hayJugada) {
			Carta cartaRemovida = origen.cogeCarta();
			if (!origen.estaVacio())
				origen.cartaSuperior().darVuelta();
			if (!destino.estaVacio())
				destino.cartaSuperior().darVuelta();
			destino.anhadeCarta(cartaRemovida);
		}
		
		return hayJugada;	
	}

	/**
	 * Se solicita una carta nueva de los mazos extra 
	 * a los mazos de descarte
	 * @return verdadero si quedan cartas en el mazo extra
	 */
	public synchronized boolean cartaNueva() {
		Carta cartaRemovida = null;
		Mazo destino = null;
		for (int index = 0; index < mazoReserva.length; index++) {
			if (!mazoReserva[index].estaVacio()) {
				cartaRemovida = mazoReserva[index].cogeCarta();
				destino = mazoDescartes[index];
				cartaRemovida.darVuelta();
				destino.anhadeCarta(cartaRemovida);
			}
			else return false;			
		}
		return true;		
	}

	/**
	 * Indica si el juego ha terminado
	 * @return verdadero si el juego debe continuar
	 */
	public synchronized boolean continuarJuego() {
		boolean victoriaJug1 = true;
		boolean victoriaJug2 = true;
		for (int index = 0; index < mazoJuegoJug1.length; index++) {
			victoriaJug1 = mazoJuegoJug1[index].estaVacio() && victoriaJug1;
			victoriaJug2 = mazoJuegoJug2[index].estaVacio() && victoriaJug2;
		}
		return !(victoriaJug1 || victoriaJug2);
	}
	
	/**
	 * Devuelve el conjunto de mazos de descartes
	 * @return the mazoDescartes
	 */
	public Mazo[] getMazoDescartes() {
		return mazoDescartes;
	}

	/**
	 * Devuelve el conjunto de mazos de juego del jugador 1
	 * @return the mazoJuegoJug1
	 */
	public Mazo[] getMazoJuegoJug1() {
		return mazoJuegoJug1;
	}
	
	/**
	 * Devuelve el conjunto de mazos de juego del juador 2
	 * @return the mazoJuegoJug2
	 */
	public Mazo[] getMazoJuegoJug2() {
		return mazoJuegoJug2;
	}

	/**
	 * Devuelve el conjunto de mazos de cartas de reserva
	 * @return the mazoReserva
	 */
	public Mazo[] getMazoReserva() {
		return mazoReserva;
	}
	
	//  METODOS PRIVADOS
	
	/**
	 * Obten el mazo correspondiente al jugador
	 * (mazos 0-3 para Jugador1, mazos 4-7 para Jugador2) 
	 * @param el numero de mazo absoluto (0-7)
	 * @return el mazo correspondiente al jugador
	 */
	private Mazo obtenMazo(int numeroMazo) {
		
		Mazo returnData = null;
		if (numeroMazo < mazoJuegoJug1.length) // Jugador 1
			returnData = mazoJuegoJug1[numeroMazo%mazoJuegoJug1.length];
		else 
			returnData = mazoJuegoJug2[numeroMazo%mazoJuegoJug2.length];
		
		return returnData;
	}
	
	/**
	 * Reparto de cartas entre los distintos mazos del juego.
	 * Se reparten de acuerdo a la siguiente norma:
	 * Existen 4 mazos de juego por jugador, cada uno con el número 
	 * de cartas de su posición (mazo 1 con 1 carta, mazo 2 con dos cartas, etc)
	 * El resto de cartas se reparten entre los mazos de cartas extra de cada jugador
	 * @param baraja con las cartas a repartir
	 */
	private void repartoCartasEnMazos(ArrayList<Carta> baraja) {
		Iterator<Carta> iterador = baraja.iterator();
		for (int index = 0; index < mazoJuegoJug1.length; index++) {
			for (int cartas = 0; cartas <= index; cartas++) {
				mazoJuegoJug1[index].anhadeCarta(iterador.next());
				mazoJuegoJug2[index].anhadeCarta(iterador.next());
			}
		}

		while (iterador.hasNext()) {
			mazoReserva[0].anhadeCarta(iterador.next());
			mazoReserva[1].anhadeCarta(iterador.next());
		}	
	}
	
	/**
	 * Crea todos los mazos necesarios de un mismo tipo 
	 * @param el conjunto de mazos
	 */
	private void MazosEnTablero (Mazo[] mazo) {
		for (int index = 0; index < mazo.length; index++) {
			mazo[index] = new Mazo (NUM_CARTAS);
		}
	}
	
	/**
	 * Crea una nueva baraja de cartas con la mitad de cartas
	 * rojas y la otra mitad negras. 
	 * @param el número de cartas de la baraja
	 * @return la baraja creada
	 */
	private ArrayList<Carta> nuevaBaraja (int numCartas) {
		//  Nueva baraja
	    ArrayList<Carta> baraja = new ArrayList<Carta> (numCartas);

        for (int i = 0; i < numCartas/2; i++) {
        	baraja.add(new Carta (i%10, Color.RED));   // numCartas/2 cartas rojas
        	baraja.add(new Carta (i%10, Color.BLACK)); // numCartas/2 cartas negras
	    }
        //  Chequear si numCartas es par
        if (numCartas%2!=0) // impar
        	baraja.add(new Carta (numCartas%10, Color.RED));

        //  Barajamos las cartas
        Collections.shuffle (baraja);
        return baraja;
	}
}
