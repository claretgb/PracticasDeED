package pract06.spit.modelo;

/**
 * ReglasJuego
 * 
 * Implementación de la clase ReglasJuego que representa
 * las reglas del juego de cartas "Spit". Este juego tiene dos movimientos
 * permitidos: movimiento entre cartas consecutivas y movimiento entre 
 * cartas iguales.
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

public class ReglasJuego {
	
	/**
	 * Indica si es posible realizar un movimiento secuencial 
	 * entre las cartas superiores de los mazos indicados. Un movimiento 
	 * consecutivo se puede realizar entre dos cartas seguidas (por ejemplo, 
	 * un As sería consecutiva con un Rey o con un Dos (K->A->2)
	 * @param origen: el mazo del que queremos sacar la carta
	 * @param destino: el mazo sobre el que queremos anhadir la carta
	 * @return verdadero si el movimiento esta permitido
	 */
	public static boolean movimientoPermitidoSecuencia (Mazo origen, Mazo destino) {
		boolean returnData = false;
		if (!mazoVacio (origen, destino))
			returnData = cartasEnSecuencia (origen.cartaSuperior(), destino.cartaSuperior());
		return returnData;	
	}
	
	/**
	 * Indica si es posible realizar un movimiento de igualdad 
	 * entre las cartas superiores de los mazos indicados. Un movimiento 
	 * de igualdad se puede realizar entre dos cartas con igual número
	 * @param origen: el mazo del que queremos sacar la carta
	 * @param destino: el mazo sobre el que queremos anhadir la carta
	 * @return verdadero si el movimiento esta permitido
	 */
	public static boolean movimientoPermitidoIguales (Mazo origen, Mazo destino) {
		boolean returnData = false;
		if (!mazoVacio (origen, destino))
			returnData = origen.cartaSuperior().equals(destino.cartaSuperior());
		return returnData;
	}
	
	// METODOS PRIVADOS
	
	/**
	 * Comprueba si alguno de los mazos esta vacio antes de coger la carta
	 * @param mazo1: primer mazo sobre el que coger la carta 
	 * @param mazo2: segundo mazo sobre el que coger la carta
	 * @return verdadero si alguno de los dos mazos esta vacio
	 */
	private static boolean mazoVacio (Mazo mazo1, Mazo mazo2) {
		return mazo1.estaVacio() || mazo2.estaVacio();
	}

	/**
	 * Indica si se puede realizar un movimiento consecutivo. Este movimiento
	 * se puede realizar entre dos cartas seguidas (por ejemplo, 
	 * un As sería consecutivo con un Rey o con un Dos (K->A->2)
	 * @param cartaOrigen
	 * @param cartaDestino
	 * @return
	 */
	private static boolean cartasEnSecuencia(Carta cartaOrigen, Carta cartaDestino) {
		int numDestino = cartaDestino.numeroCarta();
		//  Debemos realizar la operación modulo para contemplar los casos de los extremos
		//  (Rey->As) y (As<-Rey)  
		return ((cartaOrigen.numeroCarta()==Modulo((numDestino-1),10)) ||
	            (cartaOrigen.numeroCarta()==Modulo((numDestino+1),10)));
	}
		
	/**
	 * Devuelve el entero menor de la operación módulo de dos números
	 * @param num: primer entero
	 * @param num2: segundo entero
	 * @return entero con el resultado de la operación
	 */
	private static int Modulo (int x, int m) {
	    //  Java 8 ya proporciona el método Math.floorMod que implementa esta funcionalidad
	    return (x%m + m)%m;
	}
	
}
