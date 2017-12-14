package parte_1;

/**
 * Operaciones sobre arrays de enteros para el estudio de sus
 * tiempos de ejecucion
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version sep-17
 */
public class OperacionesArraysEnteros {
	
	/**
	 * Retorna el mayor valor del indice del array (i) para el que se verifica
	 * que el elemento en dicha posicion (a[i]) es mayor que cada uno de los
	 * elementos en las posiciones anteriores (a[0] .. a[i-1]).
	 * XXX Utiliza un algoritmo poco eficiente.
	 * @param a array en el que buscar la posicion del ultimo maximo parcial.
	 * @return posicion del ultimo maximo parcial o -1 si el array tiene 0 elementos.
	 */
	public static int indiceUltimoMaximoParcial(int[] a) {
		int indiceUltimoMax = -1;
		
		// recorre todos los elementos del array
		for(int i=0; i<a.length; i++) {
			// compara el elemento i-esimo con todos los anteriores
			boolean todosMenores = true;
			for(int j=0; j<i; j++) {
				if (a[j] >= a[i]) { 
					/* Como respuesta a la cuestión 1 del apartado a) podemos decir que 
					 * se ejecuta (n*(n-1))/2 de veces.
					 * Debido a que se puede ver como n²/2 - n/2, en n suficientemente 
					 * grandes se considera n².
					 */
					/* Como respuesta a la cuestión 2 del apartado a) podemos decir que 
					 * la complejidad temporal es de O(n²).
					 */
					
					// encontrado uno que es mayor o igual
					todosMenores = false;
				}
			}
			
			// si todos eran menores, actualiza el indice del ultimo mayor
			if (todosMenores) {
				indiceUltimoMax = i;
			}
		}
		
		// retorna la posicion del ultimo maximo parcial
		return indiceUltimoMax;
	}
	
	/**
	 * Retorna el mayor valor del indice del array (i) para el que se verifica
	 * que el elemento en dicha posicion (a[i]) es mayor que cada uno de los
	 * elementos en las posiciones anteriores (a[0] .. a[i-1]).
	 * @param a array en el que buscar la posicion del ultimo maximo parcial.
	 * @return posicion del ultimo maximo parcial o -1 si el array tiene 0 elementos.
	 */
	public static int indiceUltimoMaximoParcialEficiente(int[] a) {
		int mayor = 0; //dato para guardar el índice del mayor valor del array.
		for(int i = 0; i < a.length; i++) { //búsqueda en el array
			if(a[i] > a[mayor]) { //si es mayor que el mayor actual
				mayor = i; //se guarda el indice
			}
		}
		return mayor;		
	}
	
}
