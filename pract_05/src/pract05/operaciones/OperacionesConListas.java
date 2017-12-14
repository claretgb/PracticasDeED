package pract05.operaciones;

import pract05.lista_array.ListaArray;
import pract05.IIteradorSimple;
import pract05.ILista;

/**
 * Clase que implementa dos algoritmos sencillos sobre listas.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version oct-2017
 */
public class OperacionesConListas {

	/**
	 * Duplica en la lista todas las ocurrencias del elemento indicado.
	 * @param lista lista en la que duplicar el elemento indicado
	 * @param aDuplicar elemento a duplicar
	 */
	/*
	 * Complejidad temporal: 
	 * 		Para ListaArray es O(n^2) porque el anhade de ListaArray e O(n).
	 * 		Para ListaDobleEnlace es O(n).
	 */
	public static void duplicaOcurrenciasElemento(ILista<String> lista, String aDuplicar) {
		IIteradorSimple<String> iterador = lista.iterador();
		while(iterador.haySiguiente()) {
			String str = iterador.siguiente(); 
			if(str.equals(aDuplicar)) {
				iterador.anhade(aDuplicar);
			}
		}
	}

	/**
	 * Retorna una nueva lista con los elementos de la lista original que ocupan las
	 * posiciones indicadas. La lista original no se modifica.
	 * @param lista lista original de la que copiar los elementos a la nueva lista
	 * @param posiciones posiciones en la lista original de los elementos a copiar
	 * en la nueva lista
	 * @return una nueva lista con los elementos copiados en el orden en el que 
	 * aparecen en el array posiciones.
	 */
	/*
	 * Complejidad temporal: 
	 * 		Para ListaArray es O(k).
	 * 		Para ListaDobleEnlace es O(k*n).
	 * Si el anhade se ejecuta con un array lleno dejaría de ser O(1) para pasar a ser O(n),
	 * y habría que multiplicarlo por los órdenes mencionados anteriormente, quedando así:
	 * 		Para ListaArray es O(k*n).
	 * 		Para ListaDobleEnlace es O(k*n*n).
	 */
	public static ListaArray<String> sublistaPosiciones(ILista<String> lista,
			int[] posiciones) {
		ListaArray<String> listaSolucion = new ListaArray<String>();
		int punteroResultado = 0;
		for(int i = 0; i < posiciones.length; i++) {
			int posicion = posiciones[i];
			String dato = lista.obtenElemento(posicion);
			listaSolucion.anhade(punteroResultado, dato);
			punteroResultado++;
		}
		return listaSolucion;
	}

}
