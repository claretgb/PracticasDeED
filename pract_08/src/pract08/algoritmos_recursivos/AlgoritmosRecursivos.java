package pract08.algoritmos_recursivos;

import pract08.arbol_general_ce.IArbolGeneral;
import pract08.arbol_general_ce.INodoArbolGeneral;

/**
 * Algoritmos recursivos sobre arboles generales.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version nov-2017
 */
public class AlgoritmosRecursivos {

	/**
	 * Retorna el numero de veces que se encuentra el elemento
	 * buscado en el arbol.
	 * @param arbol arbol en el que contar el numero de ocurrencias.
	 * @param buscado elemento buscado.
	 * @return numero de ocurrencias del elemento en el arbol.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public static <E> int cuentaOcurrenciasElemento(
			IArbolGeneral<E> arbol,
			E buscado)
	{
		return cuentaOcurrenciasElementoRec(arbol.raiz(), buscado);
	}

	/**
	 * Metodo auxiliar recursivo que retorna el numero de veces
	 * que se encuentra el elemento buscado en el subarbol cuya
	 * raiz es el nodo indicado.
	 * @param nodo raiz del subarbol en el que buscar.
	 * @param buscado elemento buscado.
	 * @return numero de ocurrencias del elemento en el subarbol.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	private static <E> int cuentaOcurrenciasElementoRec(
			INodoArbolGeneral<E> nodo,
			E buscado)
	{
		int i = 0;
		if(nodo == null) {
			return 0;
		}
		if(nodo.contenido().equals(buscado)) {
			i++;
		}
		nodo = nodo.primerHijo();
		while(nodo != null) {
			i = i + cuentaOcurrenciasElementoRec(nodo, buscado);
			nodo = nodo.hermanoDcho();
		}
		return i;
	}

	/**
	 * Retorna el contenido de un nodo del arbol que sea igual al
	 * contenido de su primer hijo.
	 * @param <E> elementos almacenados en el arbol.
	 * @param arbol arbol en el que buscar.
	 * @return el contenido de un nodo del arbol que sea igual a su
	 * primer hijo o null en caso de que no lo encuentre.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public static <E> E buscaElemIgualAPrimerHijo(IArbolGeneral<E> arbol)
	{
		if(arbol.raiz() == null) {
			return null;
		}
		return buscaElementoRecursivo(arbol.raiz());
	}
	
	/**
	 * Método auxiliar que sirve para buscar el elemento recursivo. 
	 * @param INodoArbolGeneral<E> raiz: la raiz del arbol donde se quiere buscar.
	 * @return E solucion: el elemento recursivo.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	private static <E> E buscaElementoRecursivo (INodoArbolGeneral<E> raiz) {
		if(raiz.primerHijo() != null) 
		{
			if(raiz.contenido().equals(raiz.primerHijo().contenido())) 
			{
				return raiz.contenido();
			}
			
			INodoArbolGeneral<E> punteroAuxiliar = raiz.primerHijo();
			while(punteroAuxiliar != null) 
			{
				E solucion = buscaElementoRecursivo(punteroAuxiliar);
				if(solucion != null) 
				{
					return solucion;
				}
				punteroAuxiliar = punteroAuxiliar.hermanoDcho();
			}
			return null;
			
		}
		else 
		{
			return null;
		}
	}

}
