package pract06;

/**
 * TDA Pila.
 * 
 * @param <E> tipo de los elementos almacenados en la pila
 * 
 * @author Estructuras de Datos (UC)
 * @version oct-2017
 */
public interface IPila<E> {
	
	/**
	 * Añade el elemento en la cima de la pila.
	 *
	 * @param e elemento a añadir.
	 * @throws IndexOutOfBoundsException si no hay capacidad para
	 * apilar más elementos
	 */
	public void apila(E e) throws IndexOutOfBoundsException;

	
	/**
	 * Elimina y retorna el elemento que ocupaba la
	 * la cima. La cima pasa al siguiente elemento
	 * @return elemento que ocupaba la cima o null si la
	 * pila estaba vacía
	 */
	public E desapila();

	
	/**
	 * Retorna (pero no elimina) el elemento que
	/* ocupa la cima. La cima no cambia.
	 * @return elemento que ocupa la cima o null si la
	 * pila está vacía
	 */
	public E cima();

	
	/**
	 * Retorna el tamaño de la pila (num. elementos)
	 * @return tamaño de la pila
	 */
	public int tamanho();
	
	
	/**
	 * Vacía la pila (pasa a tener tamaño 0)
	 */
	public void haceVacia();

}
