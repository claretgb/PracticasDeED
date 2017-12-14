package pract08.arbol_general_ce;

import pract08.arbol_general_ce.ArbolGeneralCE.Nodo;

/**
 * TDA Arbol general.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2017
 */
public interface IArbolGeneral<E> {
	
	/**
	 * Retorna el nodo en la raiz del arbol.
	 * @return el nodo en la raiz del arbol.
	 */
	public INodoArbolGeneral<E> raiz();
	
	/**
	 * Retorna el numero de nodos del arbol.
	 * @return numero de nodos del arbol.
	 */
	public int tamanho();
	
	/**
	 * Vacia el arbol.
	 */
	public void haceVacio();
	
	/**
	 * Anhade el nodo raiz con el contenido indicado a un arbol vacio.
	 * @param contenidoRaiz contenido del nodo raiz.
	 * @throws UnsupportedOperationException si el arbol no está vacio.
	 */
	public void anhadeRaiz(E contenidoRaiz) throws UnsupportedOperationException;
	public void cambiaPadre(Nodo<E> padre);
	public void cambiaHermanoDerecho(Nodo<E> hermanoDerecho);
}
