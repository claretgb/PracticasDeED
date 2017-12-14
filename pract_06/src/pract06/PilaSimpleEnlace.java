package pract06;

/**
 * Clase que implementa la TDA Pila.
 * 
 * @param <E> tipo de los elementos almacenados en la pila
 * 
 * @author Clara Torre García-Barredo
 * @version oct-2017
 */
public class PilaSimpleEnlace<E> implements IPila<E> {

	private static class Celda<E>{
		public E e;
		public Celda<E> siguiente;
		
		public Celda (E e) {
			this.e = e;
		}
	}
	
	public Celda<E> cima;
	private int numEle;
	
	/*
	 * Complejidad temporal: O(1).
	 */
	public PilaSimpleEnlace() {
		cima = null;
		numEle = 0;
		
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
		Celda<E> nueva = new Celda<E>(e);
		nueva.siguiente = cima;
		cima = nueva;
		numEle++;
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
		E elemento = cima.e;
		cima = cima.siguiente;
		numEle--;
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
		return cima.e;
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
		return numEle;
	}

	@Override
	/**
	 * Vacía la pila (pasa a tener tamaño 0)
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public void haceVacia() {
		numEle = 0;
		cima = null;
	}

}
