package parte_2;

/**
 * Clase que contiene el algoritmo de ordenacion por insercion
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-17
 */
public class Ordenacion {

	/**
	 * Ordenacion de un array de menor a mayor utilizando
	 * el algoritmo de ordenacion por insercion
	 * @param a array a ordenar
	 */
	public static void insercion(int a[]) {
		/*
		 * La respuesta a la cuestión 1 del apartado 2 es
		 * O(n²).
		 */
		for(int i = 1; i < (a.length); i++) {
			int j = i;
			while(j > 0 && a[j-1] > a[j]) {
				int intercambio = a[j-1];
				a[j-1] = a[j];
				a[j] = intercambio;
				j--;
			}
		}
	}


}
