package lista_doble_enlace;

import java.util.NoSuchElementException;


/**
 * Implementación de una lista genérica utilizando un array.
 * Implementa el iterador.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version oct-2017
 */
public class ListaArray<E> implements ILista<E>{

	private E[] elementos;	 // array que almacena los elementos de la lista
	private int numEle = 0;	 // numero de elementos almacenados en la lista

	/**
	 * Construye una lista vacia con la capacidad incial indicada.
	 * 
	 * @param capacidad capacidad inicial de la lista 
	 */
	// Complejidad temporal: O(numEle) (en Java los elementos de un array se inicializan a null)
	@SuppressWarnings("unchecked")
	public ListaArray(int capacidad) { 
		elementos = (E[]) new Object[capacidad]; // el compilador pone un "warning", pero esta bien
	}

	/**
	 * Construye una lista vacia.
	 */
	// Complejidad temporal: O(1)
	@SuppressWarnings("unchecked")
	public ListaArray() { 
		elementos = (E[]) new Object[8]; // el compilador pone un "warning", pero esta bien
	}

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
	// Complejidad temporal: O(numEle)
	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException{
		if (pos < 0 || pos > tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}
		
		if (numEle < elementos.length) {
			// el elemento cabe en el array

			// desplaza elementos hacia delante para hacer hueco
			for (int i=numEle-1; i>=pos; i--) {
				elementos[i+1] = elementos[i];
			}

		} else {
			// se ha alcanzado el numero maximo de elementos, luego hay que redimensionar el array.
	        // Crea un nuevo array con la nueva capacidad
			@SuppressWarnings("unchecked")
			E[] nuevo = (E[]) new Object[elementos.length*2]; // el compilador pone un "warning", pero esta bien

			// copia los elementos hasta la posicion a anhadir al array nuevo en la misma
			// posicion que ocupaban en el viejo
			for(int i=0; i<pos; i++) {
				nuevo[i] = elementos[i];
			}

			// copia los elementos a partir de la posicion a anhadir al array nuevo
			// en la posicion siguiente a la que ocupaban en el viejo
			for(int i=pos; i<numEle; i++) {
				nuevo[i+1] = elementos[i];
			}
			
			// sustituye el array viejo por el nuevo
			elementos = nuevo;
		} 

		// añade el elemento en la posicion deseada
		elementos[pos] = e;
		numEle++;
	}

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
	// Complejidad temporal: O(numEle)
	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		// guarda el elemento a eliminar
		E temp = elementos[pos];

		// desplaza elementos hacia detrás para cubrir el hueco del
		// elemento eliminado
		for (int i=pos; i<numEle-1; i++)
			elementos[i] = elementos[i+1];

		// posicion no usada: la pone a null para permitir liberacion de memoria
		// por parte del "recolector de basura"
		elementos[numEle-1]=null;

		numEle--;
		return temp;
	}

	/**
	 * Retorna el elemento que ocupa la posicion indicada
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	// Complejidad temporal: O(1)
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		return elementos[pos]; 
	}

	/**
	 * Retorna el tamanho de la lista (numero de elementos)
	 * @return numero de elementos de la lista
	 */
	// Complejidad temporal: O(1)
	@Override
	public int tamanho() {
		return numEle;
	}
	
	/**
	 * Retorna la posicion de la primera ocurrencia
	 * del elemento buscado en la lista.
	 * Utiliza el método equals del elemento.
	 * @param e elemento buscado
	 * @return posicion de la primera ocurrencia
	 * del elemento en la lista o -1 en caso de que 
	 * el elemento no este en la lista
	 */
	// Complejidad temporal: O(numEle)
	@Override
	public int busca(E e) {
		for(int i=0; i<numEle; i++) {
			if (e.equals(elementos[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Vacia la lista (pasa a tener tamanho 0)
	 */
	// Complejidad temporal: O(numEle)
	@Override
	public void haceVacia() {
		// pone los punteros a null para permitir liberacion de memoria
		// por parte del "recolector de basura"
		for(int i=0; i<numEle; i++) {
			elementos[i] = null;
		}

		numEle = 0;
	}
	
	/**
	 * Clase iteradora 
	 */
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaArray<E> lista; // lista a ser iterada
		private int siguiente;

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento)
		 * @param lista lista a ser iterada
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		public IteradorLista(ListaArray<E> lista) {
			this.lista = lista;
			siguiente = 0;
		}

		/**
		 * Indica si hay más elementos (no se ha llegado al final de la lista)
		 * @return true si todavia no se ha llegado al final de la lista
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public boolean haySiguiente() {
				return siguiente < lista.numEle;
		}

		/**
		 * Retorna el siguiente elemento en la iteracion y avanza el iterador
		 * @return el siguiente elemento
		 * @throws NoSuchElementException si se ha llegado al final de la lista
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public E siguiente() 
			throws NoSuchElementException {
			IIteradorSimple<E> iter = lista.iterador();
			E elemento = null;
			if(iter.haySiguiente()) {
				elemento = lista.elementos[siguiente];
				siguiente++;
			} else {
				throw new NoSuchElementException();
			}
			return elemento;
		}

		/**
		 * Inserta un elemento en la posición del iterador
		 * @param e elemento a insertar
		 */
		/*
		 * Complejidad temporal: O(numEle).
		 */
		@Override
		public void anhade(E e) {
			lista.anhade(siguiente, e);
			siguiente++;
		}

		/**
		 * Reemplaza el último elemento retornado por el iterador
		 * @param e elemento utilizado para reemplazar
 		 * @throws NoSuchElementException si el iterador no ha retornado ningún elemento previamente
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void asigna(E e) 
			throws NoSuchElementException {
			if(siguiente == 0) {
				throw new NoSuchElementException();
			}
			lista.elementos[siguiente-1] = e;
		}

		@Override
		public E siguientes(int n) {
			// TODO Auto-generated method stub
			return null;
		}			
	}
	
	/**
	 * Retorna un iterador que permite recorrer los elementos en el orden que
	 * ocupan en la lista
	 * @return iterador que permite recorrer los elementos de la lista
	 */
	// Complejidad temporal: O(1)
	@Override
	public IIteradorSimple<E> iterador() {
		// XXX: nuevo metodo incluido para dar soporte al iterador
		return new IteradorLista<E>(this);
	}
}
