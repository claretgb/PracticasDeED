package pract12;

/**
 * TDA Cola de Prioridad
 * 
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-2017
 */
public interface IColaPrioridad<E> {
	
	/**
	 * Anhade el elemento dado a la cola en la posicion
	 * correspondiente a su prioridad.
	 * @param e elemento a anhadir
	 * @throws IndexOutOfBoundsException si la cola esta llena
	 */
	public void encolaConPrioridad(E e) throws IndexOutOfBoundsException;

	/**
	 * Elimina y retorna el elemento mas prioritario.
	 * @return elemento  mas prioritario o null si la cola
	 * esta vacia
	 */
	public E desencolaMasPrioritario();

	/**
	 * Retorna (pero no elimina) el elemento  mas prioritario.
	 * @return elemento  mas prioritario o null si la cola
	 * esta vacia
	 */
	public E masPrioritario();
	 
	/**
	 * Vacia la cola (pasa a tener tamanho 0)
	 */
	public void haceVacia();

	/**
	 * Retorna el tamaño de la cola (num. elementos)
	 * @return tamaño de la cola
	 */
	public int tamanho();

}
