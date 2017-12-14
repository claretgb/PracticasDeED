package pract_01;

/**
 * Implementación de una lista genérica acotada utilizando un array.
 * La lista soporta un conjunto de operaciones reducido y no
 * implementa el iterador.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version sep-2017
 */
public class ListaArraySimple<E> implements IListaSimple<E> {
	// TODO: haz que la clase implemente la interfaz IListaSimple e implementa
	// las operaciones de la citada interfaz.

	private E[] elementos;	 // array que almacena los elementos de la lista
	private int numEle = 0;	 // numero de elementos almacenados en la lista

	/**
	 * Construye una lista vacia con la capacidad indicada.
	 * 
	 * @param capacidad maximo numero de elementos que podra almacenar
	 * la lista 
	 */
	public ListaArraySimple(int capacidad) { 
		elementos = (E[]) new Object[capacidad];
		// el compilador pone un "warning", pero esta bien
	}

	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException,
			UnsupportedOperationException {
		if(pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException();
		}
		if(numEle==elementos.length) {
			throw new UnsupportedOperationException();
		}
		for(int i= numEle; i>pos; i--) {
			elementos[i] = elementos[i-1];
		}
		elementos[pos] = e;
		numEle++;
	}

	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if(pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		E elemento = elementos[pos];
		for(int i = pos; i<(numEle-1); i++) {
			elementos[i] = elementos[i+1];
		}
		numEle--;
		return elemento;
	}

	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if(pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		E elemento = elementos[pos];
		return elemento;
	}

	@Override
	public int tamanho() {
		return numEle;
	}
}
