package pract08.arbol_general_ce;

/**
 * Nodo de un arbol general (forma parte del TDA Arbol General).
 * 
 * @param <E> tipo de los elementos almacenados en el arbol.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2017
 */
public interface INodoArbolGeneral<E> {
	
	/**
	 * Retorna el contenido del nodo.
	 * @return el contenido del nodo.
	 */
	public E contenido();

	/**
	 * Cambia el contenido del nodo.
	 * @param nuevoContenido nuevo contenido del nodo.
	 */
	public void cambiaContenido(E nuevoContenido);
	
	/**
	 * Retorna el padre del nodo.
	 * @return el padre del nodo o null si el nodo actual es la raiz.
	 */
	public INodoArbolGeneral<E> padre();

	/**
	 * Retorna el primer hijo del nodo.
	 * @return el primer hijo del nodo o null si el nodo actual
	 * no tiene hijos.
	 */
	public INodoArbolGeneral<E> primerHijo();
	
	/**
	 * Retorna el hermano derecho del nodo.
	 * @return el hermano derecho del nodo o null si el nodo actual
	 * es el ultimo hermano.
	 */
	public INodoArbolGeneral<E> hermanoDcho();

	/**
	 * Anhade el primer hijo al nodo.
	 * Su antiguo primer hijo (si le hubiera) pasa a
	 * ser el hermano derecho del recien anhadido.
	 * @param contenido contenido del nodo a anhadir.
	 */
	public void insertaPrimerHijo(E contenido);
	
	/**
	 * Anhade el hermano derecho al nodo.
	 * Su antiguo hermano derecho (si le hubiera) pasa a
	 * ser el hermano derecho del recien anhadido.
	 * @param contenido contenido del nodo a anhadir.
	 * @throws UnsupportedOperationException si el nodo es la raiz.
	 */
	public void insertaHermanoDcho(E contenido) 
			throws UnsupportedOperationException;
		
	/**
	 * Anhade la raiz de la rama como primer hijo del nodo actual.
	 * Su antiguo primer hijo (si le hubiera) pasa a ser el
	 * hermano derecho del nodo que era la raíz de la rama.
	 * Despues de esta operacion el arbol "rama" queda vacio.
	 * @param rama rama que anhadir como primer hijo.
	 */
	public void insertaRamaPrimerHijo(IArbolGeneral<E> rama);
	
	/**
	 * Anhade la raiz de la rama como hermano derecho del nodo actual.
	 * Su antiguo hermano derecho (si le hubiera) pasa a ser el
	 * hermano derecho del nodo que era la raíz de la rama.
	 * Despues de esta operacion el arbol "rama" queda vacio.
	 * @param rama rama que anhadir como primer hijo.
	 * @throws UnsupportedOperationException si el nodo es la raiz.
	 */
	public void insertaRamaHermanoDcho(IArbolGeneral<E> rama) 
			throws UnsupportedOperationException;
	
	/**
	 * Elimina la rama cuya raiz es en nodo actual.
	 * @return arbol cuya raiz es el nodo actual.
	 */
	public IArbolGeneral<E> cortaRama();
}
