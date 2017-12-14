package pract05.lista_doble_enlace;

import java.util.NoSuchElementException;

import pract05.ILista;
import pract05.IIteradorSimple;
import pract05.lista_array.ListaArray;

/**
 * Implementación de una lista generica utilizando celdas doblemente enlazadas
 * con celdas de cabecera y cola.
 * Implementa un iterador simple.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version oct-2017
 */
public class ListaDobleEnlace<E> implements ILista<E> {


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
	public ListaDobleEnlace() {
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
	public ListaDobleEnlace(int capacidad) {
		this(); // llama al constructor sin parametros ListaDobleEnlaceNoIter()
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
	// Eficiencia: O(numEle) 
	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}
		Celda<E> nuevaCelda = new Celda<E>(e);
		Celda<E> posAnt = buscaPos(pos-1);
		nuevaCelda.siguiente = posAnt.siguiente;
		nuevaCelda.anterior = posAnt;
		posAnt.siguiente.anterior = nuevaCelda;
		posAnt.siguiente = nuevaCelda;
		numEle++;
	}

	/**
	 * Retorna una referencia a la celda en la posicion pos
	 * {Pre: pos >= -1 y pos<numEle}
	 * @param pos posicion de la celda buscada
	 * @return referencia a la celda en la posicion pos
	 */
	// Eficiencia: O(numEle) 
	private Celda<E> buscaPos(int pos) {
		Celda<E> aux = principio;
		if (pos < tamanho() / 2) {
			aux = principio;
			for(int i=0; i<=pos; i++) {
				aux = aux.siguiente;
			}
		} else {
			aux = fin;
			for(int i=tamanho()-1; i>=pos; i--) {
				aux = aux.anterior;
			}
		}
		return aux;
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
	// Eficiencia: O(numEle)
	@Override
	public E elimina(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		Celda<E> posElim = buscaPos(pos);
		posElim.anterior.siguiente = posElim.siguiente;
		posElim.siguiente.anterior = posElim.anterior;
		numEle--;
		
		return posElim.contenido;
	}

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	// Eficiencia: O(numEle)
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos >= tamanho()) {
			throw new IndexOutOfBoundsException(); // posicion incorrecta
		}

		return buscaPos(pos).contenido;
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
	// Eficiencia: O(numEle)
	@Override
	public int busca(E e) {
		Celda<E> aux = principio.siguiente;
		int pos = 0;
		while (aux != null) {
			if (aux.contenido.equals(e)) {
				return pos;  // encontrado
			}
			aux = aux.siguiente;
			pos++;
		}
		return -1; // no encontrado
	}

	/**
	 * Vacia la lista (pasa a tener tamanho 0)
	 */
	// Eficiencia: O(1)
	@Override
	public void haceVacia() {
		principio.siguiente = fin;
		fin.anterior = principio;
		numEle = 0;
	}

	/**
	 * Retorna el tamanho de la lista (numero de elementos)
	 * @return numero de elementos de la lista
	 */
	// Eficiencia: O(1)
	@Override
	public int tamanho() {
		return numEle;
	}
	
	private static class IteradorLista<E> implements IIteradorSimple<E> {
		private ListaDobleEnlace<E> lista; // lista a ser iterada
		private Celda<E> puntero;

		/**
		 * Constructor del iterador. El iterador comienza al principio
		 * de la lista (justo antes del primer elemento)
		 * @param lista lista a ser iterada
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		public IteradorLista(ListaDobleEnlace<E> lista) {
			this.lista = lista;
			puntero = lista.principio;
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
				return puntero.siguiente.siguiente != null;
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
			if(haySiguiente()) {
				puntero = puntero.siguiente;
			} else {
				throw new NoSuchElementException();
			}
			return puntero.contenido;
		}

		/**
		 * Inserta un elemento en la posición del iterador
		 * @param e elemento a insertar
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void anhade(E e) {
			Celda<E> aux = new Celda<E>(e);
			aux.siguiente = puntero.siguiente;
			aux.anterior = puntero;
			puntero.siguiente = aux;
			aux.siguiente.anterior = aux;
			puntero = puntero.siguiente;
			lista.numEle++;
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
			if(puntero == lista.principio) {
				throw new NoSuchElementException();
			}
			puntero.contenido = e;
		}			
	}

	/**
	 * Retorna un iterador que permite recorrer los elementos en el orden que
	 * ocupan en la lista.  El iterador comienza situado al principio de la lista.
	 * @return iterador que permite recorrer los elementos de la lista
	 */
	@Override
	public IIteradorSimple<E> iterador() {
		return new IteradorLista(this);
	}
}
