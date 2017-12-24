package pract12;

import java.util.Arrays;

/**
 * Implementación de una cola de prioridad generica utilizando un array.
 * Se trata de una estructura acotada, con una capacida maxima indicada
 * en el constructor.
 *
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version dic-2017
 */
public class ColaPrioMonticulo<E extends Comparable<E>> implements IColaPrioridad<E>{
	// Atributos de la clase
	private E[] elementos;
	private int numEle;
	
	/**
	 * Crea una cola con la capacidad maxima indicada
	 * @param capacidad capacidad maxima de la cola
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@SuppressWarnings("unchecked")
	public ColaPrioMonticulo(int capacidad) {
		numEle = 0;
		elementos = (E[]) new Comparable[capacidad + 1];
	}
	
	/**
	 * Anhade el elemento dado a la cola en la posicion
	 * correspondiente a su prioridad.
	 * @param e elemento a anhadir
	 * @throws IndexOutOfBoundsException si la cola esta llena
	 */
	/*
	 * Complejidad temporal: O(numEle).
	 */
	@Override
	public void encolaConPrioridad(E e) throws IndexOutOfBoundsException {
		if((numEle + 1) >= elementos.length){
			   throw new IndexOutOfBoundsException();
			  }
		numEle++;
		int posicion = numEle;
		elementos[posicion] = e;
		while(posicion != 1 && elementos[posPadre(posicion)].compareTo(elementos[posicion]) > 0) {
			int posicionDelPadre = posPadre(posicion);
			E auxiliar = elementos[posicionDelPadre];
			elementos[posicionDelPadre] = e;
			e = auxiliar;
			posicion = posicionDelPadre;
		}
	}

	/**
	 * Elimina y retorna el elemento mas prioritario.
	 * @return elemento  mas prioritario o null si la cola
	 * esta vacia
	 */
	/*
	 * Complejidad temporal: O(numEle).
	 */
	@Override
	public E desencolaMasPrioritario() {
		if(numEle == 0) {
			return null;
		}
		E elementoMasPrioritario = masPrioritario();
		E x = elementos[numEle];
		int posicionHueco = 1;
		numEle--;
		boolean verificaMinimalidad = false;
		while(!verificaMinimalidad && posHijoIzq(posicionHueco) <= numEle) {
			int posicionDelHijoMenor;
			if(elementos[posHijoIzq(posicionHueco)].compareTo(elementos[posHijoDer(posicionHueco)]) <= 0) {
				posicionDelHijoMenor = posHijoIzq(posicionHueco);
			} else {
				posicionDelHijoMenor = posHijoDer(posicionHueco);
			}
			if(elementos[posicionDelHijoMenor].compareTo(x) < 0) {
				elementos[posicionHueco] = elementos[posicionDelHijoMenor];
				posicionHueco = posicionDelHijoMenor;
			}
			else {
				verificaMinimalidad = true;
			}
		}
		elementos[posicionHueco] = x;
		elementos[numEle + 1] = null;
		return elementoMasPrioritario;
	}

	/**
	 * RRetorna (pero no elimina) el elemento  mas prioritario.
	 * @return elemento  mas prioritario o null si la cola
	 * esta vacia
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public E masPrioritario() {
		return elementos[1];
	}
	 
	/**
	 * Vacia la cola (pasa a tener tamanho 0)
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public void haceVacia() {
		elementos[1] = null;
		numEle = 0;
		
	}

	/**
	 * Retorna el tamaño de la cola (num. elementos)
	 * @return tamaño de la cola
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public int tamanho() {
		return numEle;
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(elementos, 1, numEle+1));
	}
	
	/**
	 * Retorna la posicion del padre de la posicion dada.
	 * @param pos posicion
	 * @return posicion del padre
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	private int posPadre(int pos) {
		if(pos <= 1) {
			pos = 1;
		}
		return pos/2;
	}
	
	/**
	 * Retorna la posicion del hijo izquierdo de la posicion dada.
	 * @param pos posicion
	 * @return posicion del hijo izquierdo
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	private int posHijoIzq(int pos) {
	
		return pos*2;
	}
	
	/**
	 * Retorna la posicion del hijo derecho de la posicion dada.
	 * @param pos posicion
	 * @return posicion del hijo derecho
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	private int posHijoDer(int pos) {
		return (pos*2) + 1;
	}
}
