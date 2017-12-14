package pract03.parte2;

import java.util.Random;

import pract03.*;
import pract03.medidas.*;

/**
 * Programa para medir tiempos de ejecucion
 * 
 * @author Estructuras de Datos y <Clara Torre García-Barredo>
 * @version oct-17
 */
public class RealizaMedidasAnhadeSublista {
	
	/**
	 * Anhade a la lista todos los elementos de la sublista en la posicion
	 * pos. 
	 * @param lista, lista a la que se anhaden los elementos.
	 * @param sublista, lista con los elemento a anhadir.
	 * @param pos, posicion en la que se anhade la sublista.
	 * @throws IndexOutOfBoundsException si la posicion esta fuera del rango 
	 */
	private static <E> void anhadeSublista (IListaSimple<E> lista, IListaSimple<E> sublista, int pos)
			throws IndexOutOfBoundsException {
		if(pos < 0 || pos > lista.tamanho())	{
			throw new IndexOutOfBoundsException();
		}
		int j = 0;
		for(int i = pos; i<sublista.tamanho(); i++) {
			lista.anhade(i, sublista.obtenElemento(j));
			j++;
		}
	}
	
	/*
	 * La complejidad temporal del metodo anhadeSublista es O(n²).
	 * 
	 * El algoritmo desarrollado podría implementarse desplazando todos los elementos 
	 * por detras de pos a la vez para introducir todos los elementos de la sublista 
	 * a la vez. De esta manera se conseguiria una complejidad temporal de O(n).
	 */
	
	
	/**
	 * Programa para medir tiempos de ejecucion
	 */
	public static void main(String[] args) {
		// Variables de configuracion
		final int FACTOR_ESCALA  = 1;              	// Factor de escala. Modificar segun la eficiencia del algoritmo
		
		// Otras variables
		final int RANGO_NUMEROS  = 10; 				// los valores de la lista estarán entre [-rango,rango]
		int[] a;									// Array auxiliar de almancenamiento de elementos
		double medidaActual;						// Variable auxiliar para almacenar medidas de tiempo
		
		IListaSimple<Integer> lista, sublista;
		
		// Tamanho de los ejemplares(valores de n) para los que se van
		// a realizar las medidas de tiempos
		int[] tamanhoLista    = {1000*FACTOR_ESCALA, 2000*FACTOR_ESCALA, 4000*FACTOR_ESCALA, 8000*FACTOR_ESCALA};
		int[] tamanhoSublista = {1000*FACTOR_ESCALA, 2000*FACTOR_ESCALA, 4000*FACTOR_ESCALA, 8000*FACTOR_ESCALA};
		
		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoLista.length];
	
		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for(int i=0; i<tamanhoLista.length; i++) {
			// crea un array del tamanho variable
			a = creaArrayAleatorio(tamanhoSublista[i], RANGO_NUMEROS);
			// Crea sublista a insertar
			sublista = creaListaDesdeArrayEnteros(a, tamanhoSublista[i]);
			
			// crea un array de tamanho variable 
			a = creaArrayAleatorio(tamanhoLista[i]/FACTOR_ESCALA, RANGO_NUMEROS);		
			// crea la lista y los rellena con el array
			lista = creaListaDesdeArrayEnteros(a, tamanhoLista[i]);		
			// Ejecuta y mide el tiempo del método
			medidaActual = mideTiemposMetodo (lista, sublista);
			// almacena las medidas realizadas
			medidas1[i] = new Medida(tamanhoLista[i], medidaActual);
		}
		
		// Dibuja la gráfica
		OperacionesConMedidas.dibujaGrafica(medidas1);
	}
	
	/**
	 * Crea un array de numeros enteros del tamanho indicado con numeros
	 * aleatorios de valores comprendidos entre -rango..+rango
	 * @param tamanho tamanho del array a crear
	 * @param rango rango de los numeros aleatorios
	 * @return array generado
	 */
	private static int[] creaArrayAleatorio(int tamanho, int rango) {
		int[] a = new int[tamanho];
		Random rand = new Random();

		for(int i=0; i<tamanho; i++) {
			a[i]=rand.nextInt(rango*2+1)-rango;
		}

		return a;
	}
	
	/**
	 * Crea una lista de Integers a partir de un array de ints
	 * @param a array de valores a introducir en la lista
	 * @param capacidadLista tamanho total de la lista a crear
	 * @return retorna la lista creada
	 */
	private static IListaSimple<Integer> creaListaDesdeArrayEnteros(int[] a, int capacidadLista) {
		IListaSimple<Integer> l = new ListaArraySimple<Integer>(capacidadLista);

		for (int i =0; i<a.length; i++) 
			l.anhade(i, new Integer(a[i]));

		return l;
	}
	
	/**
	 * Mide el tiempo de ejecucion del metodo bajo test
	 * @param lista lista de Integers sobre el que operar
	 * @param sublista lista de Integers a insertar
	 */
	private static double mideTiemposMetodo (IListaSimple<Integer> lista, IListaSimple<Integer> sublista) {
		
		final Integer POSICION_A_INSERTAR = 5;	// posicion a insertar en la lista (por sencillez, fija para la prueba)
		final int NUM_REP_MEDIDA = 10; 			// veces que se realiza cada medida
		long t1, t2; 						    // tiempos (en ms)
		double medida = 0;
							
		t1 = System.currentTimeMillis(); // instante inicial		
		for (int j=0; j<NUM_REP_MEDIDA; j++) {
			anhadeSublista(lista, sublista, POSICION_A_INSERTAR);
		}	
		t2 = System.currentTimeMillis(); // instante final
		
		medida = (double) (t2-t1)/NUM_REP_MEDIDA;
		return medida;		
	}
	
}
