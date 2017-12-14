package pract04;

/**
 * Implementación de una lista generica utilizando celdas doblemente enlazadas
 * con celdas de cabecera y cola.
 * No implementa el iterador.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version oct-2017
 */
public class ListaDobleEnlaceNoIter<E> implements IListaSimple<E> {

	// clase privada que define la celda
	private static class Celda<E> {
		public E contenido;
		public Celda<E> siguiente;
		public Celda<E> anterior;

		public Celda(E cont) {
			contenido=cont;    
		}
	}

	// referencia a la primera celda de la lista (celda de cabecera)
	private Celda<E> principio;
	
	// referencia a la ultima celda de la lista (celda de cola)
	private Celda<E> fin;
	
	// numero de elementos en la lista
	private int numEle;

	/**
	 * Construye una lista vacia
	 */
	/*
	 * La complejidad temporal de este método es de O(1).
	 */
	public ListaDobleEnlaceNoIter() {
		principio = new Celda<E>(null); // crea la celda de cabecera
		fin = new Celda<E>(null); // crea la celda de cola
		
		// coloca las celdas de cabecera y cola apuntandose entre si
		principio.siguiente = fin;
		fin.anterior = principio;
		
		// la lista comienza con 0 elementos
		numEle = 0;
	}
	
	/**
	 * Construye una lista vacia
	 * @param capacidad capacidad inicial de la lista (parametro
	 * no usado).
	 * (Este constructor se incluye para que existan los mismos
	 * constructores que en el caso de ListaArrayNoIter)
	 */
	/*
	 * La complejidad temporal de este método es de O(1).
	 */
	public ListaDobleEnlaceNoIter(int capacidad) {
		this(); // llama al constructor sin parametros ListaDobleEnlaceNoIter()
	}

	@Override
	/**
	 * Inserta el elemento en la posición indicada.
	 * El elemento en la posición de inserción (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posición se incrementa en 1).
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 */
	/*
	 * La complejidad temporal de este método es de O(n).
	 */
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if(pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException();
		}
		Celda<E> nueva = new Celda<E>(null);
		nueva.contenido = e;
		Celda<E> aux = principio;
		for(int i = 0; i < pos; i++) {
			aux = aux.siguiente;
		}
		nueva.siguiente = aux.siguiente;
		nueva.anterior = aux;
		aux.siguiente.anterior = nueva;
		aux.siguiente = nueva;
		numEle++;
	}

	@Override
	/**
	 * Elimina y retorna el elemento en la posición
	 * indicada. El elemento siguiente al eliminado
	 * (si existe) y sucesivos se desplazan a la
	 * izquierda (su posición se decrementa en 1).
	 * @param pos posicion del elemento a eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	/*
	 * La complejidad temporal de este método es de O(n).
	 */
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if(pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		Celda<E> aux = principio;
		for(int i = 0; i <= pos; i++) {
			aux = aux.siguiente;
		}
		E elementoEliminado = aux.contenido;
		aux.anterior.siguiente = aux.siguiente;
		aux.siguiente.anterior = aux.anterior;
		aux.siguiente = null;
		aux.anterior = null;
		numEle--;
		return elementoEliminado;
	}

	@Override
	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	/*
	 * La complejidad temporal de este método es de O(n).
	 */
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if(pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		Celda<E> aux = principio;
		for(int i = 0; i <= pos; i++) {
			aux = aux.siguiente;
		}
		return aux.contenido;
	}

	@Override
	/**
	 * Retorna el tamanho de la lista (numero de elementos)
	 * @return numero de elementos de la lista
	 */
	/*
	 * La complejidad temporal de este método es de O(1).
	 */
	public int tamanho() {
		return numEle;
	}

	@Override
	/**
	* Retorna la posicion de la primera ocurrencia del elemento buscado
	* en la lista.
	* @param e elemento buscado
	* @return posicion de la primera ocurrencia del elemento en la lista
	* o -1 en caso de que el elemento no este en la lista
	*/
	/*
	 * La complejidad temporal de este método es de O(n).
	 */
	public int busca(E e) {
		Celda<E> aux = principio.siguiente;
		for(int i = 0; i < numEle; i++) {
			if(aux.contenido.equals(e)) {
				return i;
			}
			aux = aux.siguiente;
		}
		return -1;
	}

	@Override
	/**
	* Vacia la lista (pasa a tener tamanho 0)
	*/
	/*
	 * La complejidad temporal de este método es de O(1).
	 */
	public void haceVacia() {
		principio.siguiente = fin;
		fin.anterior = principio;
		numEle=0;
	}

}
