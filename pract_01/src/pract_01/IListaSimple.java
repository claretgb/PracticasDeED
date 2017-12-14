package pract_01;

/**
 * TDA Lista Simple.
 * Version muy simplificada del TDA Lista que veremos en el tema 3.
 * Se trata de una lista acotada que soporta un conjunto muy reducido
 * de operaciones.
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-2017
 */
public interface IListaSimple<E> {

	/**
	 * Inserta el elemento en la posición indicada.
	 * El elemento en la posición de inserción (si
	 * existe) y sucesivos se desplazan a la derecha
	 * (su posición se incrementa en 1).
	 * @param pos posicion en la que insertar el elemento
	 * @param e elemento a insertar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos > tamanho)
	 * @throws UnsupportedOperationException no es posible anhadir el elemento
	 * porque se superaria la capacidad maxima de la lista
	 */
	public void anhade(int pos, E e) 
			throws IndexOutOfBoundsException, 
			UnsupportedOperationException;

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
	public E elimina(int pos) throws IndexOutOfBoundsException;

	/**
	 * Retorna el elemento que ocupa la posicion indicada.
	 * @param pos posicion del elemento a retornar
	 * @throws IndexOutOfBoundsException la posicion esta fuera del rango
	 * valido (pos < 0 o pos >= tamanho)
	 */
	public E obtenElemento(int pos) throws IndexOutOfBoundsException;

	/**
	 * Retorna el tamanho de la lista (numero de elementos)
	 * @return numero de elementos de la lista
	 */
	public int tamanho();

}
