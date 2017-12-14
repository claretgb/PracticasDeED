package pract06;

import java.lang.reflect.Array;


/**
 * Clase que implementa la TDA Pila.
 * 
 * @param <E> tipo de los elementos almacenados en la pila
 * 
 * @author Clara Torre García-Barredo
 * @version oct-2017
 */
public class PilaArrayAcotada<E> implements IPila<E> {

	private int cima;
	private E elementos[];
	
	@SuppressWarnings("unchecked")
	/*
	 * Complejidad temporal: O(1).
	 */
	public PilaArrayAcotada(int maximo) {
		cima = -1;
		elementos = (E[]) new Object[maximo];
	}
	
	@Override
	/**
	 * Añade el elemento en la cima de la pila.
	 *
	 * @param e elemento a añadir.
	 * @throws IndexOutOfBoundsException si no hay capacidad para
	 * apilar más elementos
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public void apila(E e) throws IndexOutOfBoundsException {
		if(tamanho() == elementos.length) {
			throw new IndexOutOfBoundsException();
		}
		cima++;
		elementos[cima] = e;
	}

	@Override
	/**
	 * Elimina y retorna el elemento que ocupaba la
	 * la cima. La cima pasa al siguiente elemento
	 * @return elemento que ocupaba la cima o null si la
	 * pila estaba vacía
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public E desapila() {
		if(tamanho() == 0) {
			return null;
		}
		E elemento = elementos[cima];
		elementos[cima] = null;
		cima--;
		return elemento;
	}

	@Override
	/**
	 * Retorna (pero no elimina) el elemento que
	/* ocupa la cima. La cima no cambia.
	 * @return elemento que ocupa la cima o null si la
	 * pila está vacía
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public E cima() {
		if(tamanho() == 0) {
			return null;
		}
		return elementos[cima];
	}

	@Override
	/**
	 * Retorna el tamaño de la pila (num. elementos)
	 * @return tamaño de la pila
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public int tamanho() {
		return cima + 1;
	}

	@Override
	/**
	 * Vacía la pila (pasa a tener tamaño 0)
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public void haceVacia() {
		for(int i = 0; i<cima; i++) {
			elementos[i] = null;
		}
		cima = -1;
	}

}
