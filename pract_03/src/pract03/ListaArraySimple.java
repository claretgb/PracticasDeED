package pract03;

import pract03.IListaSimple;

/**
 * Implementación de una lista genérica acotada utilizando un array.
 * La lista soporta un conjunto de operaciones reducido y no
 * implementa el iterador.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version oct-2017
 */
public class ListaArraySimple<E> implements IListaSimple<E> {

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
	
	/*
	 * La complejidad temporal del constructor es O(1).
	 */

	/**
	 * Si el array no está lleno, entonces
	 * inserta el elemento en la posición indicada.
	 * El elemento en la posición de inserción (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posición se incrementa en 1).
	 * Si el array está lleno, crea otro array el doble de grande.
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 */
	@Override
	public void anhade(int pos, E e) throws IndexOutOfBoundsException {
		if(pos < 0 || pos > numEle) {
			throw new IndexOutOfBoundsException();
		}
		if(numEle < elementos.length) {
			for(int i = numEle; i>pos; i--) {
				elementos[i] = elementos[i-1];
			}
		} else {
			E[] nuevoArray = (E[]) new Object[elementos.length*2];
			for(int i = 0; i<pos; i++) {
				nuevoArray[i] = elementos[i];
			}
			for(int i = pos; i<numEle; i++) {
				nuevoArray[i+1] = elementos[i];
			}
			elementos = nuevoArray;
		}
		elementos[pos] = e;
		numEle++;
	}
	
	/*
	 * La complejidad temporal del metodo anhade es O(n).
	 */

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
	
	/*
	 * La complejidad temporal del metodo elimina es O(n).
	 */

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	@Override
	public E obtenElemento(int pos) throws IndexOutOfBoundsException {
		if(pos < 0 || pos >= numEle) {
			throw new IndexOutOfBoundsException();
		}
		E elemento = elementos[pos];
		return elemento;
	}
	
	/*
	 * La complejidad temporal del metodo obtenElemento es O(1).
	 */

	/**
	 * Retorna el tamanho de la lista (numero de elementos)
	 * @return numero de elementos de la lista
	 */
	@Override
	public int tamanho() {
		return numEle;
	}
	
	/*
	 * La complejidad temporal del metodo tamanho es O(1).
	 */

	/**
	* Retorna la posicion de la primera ocurrencia del elemento buscado
	* en la lista.
	* @param e elemento buscado
	* @return posicion de la primera ocurrencia del elemento en la lista
	* o -1 en caso de que el elemento no este en la lista
	*/
	@Override
	public int busca(E e) {
		for(int i = 0; i<elementos.length; i++) {
			if(elementos[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * La complejidad temporal del metodo tamanho es O(n).
	 */

	/**
	* Vacia la lista (pasa a tener tamanho 0)
	*/
	@Override
	public void haceVacia() {
		for(int i = tamanho()-1; i >= 0; i--) {
			elementos[i] = null;
			numEle--;
		}
	}
	
	/*
	 * La complejidad temporal del metodo tamanho es O(n).
	 */
}
