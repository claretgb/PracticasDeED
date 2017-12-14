package parte_2;

import java.util.Arrays;
import java.util.Random;

import medidas.Medida;
import medidas.OperacionesConMedidas;

/**
 * Programa para medir el tiempo de ejecucion de algoritmos
 * de ordenacion
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-17
 */
public class RealizaMedidasOrdenacion {

	/**
	 * Programa para medir el tiempo de ejecución de algoritmos
	 * de ordenacion
	 */
	public static void main(String[] args) {
		long tIni, tFin; // tiempos
		int[] a1=null, a2=null; // arrays a ordenar
		
		// Valores de n (tamanho de los ejemplares) para los que se van
		// a realizar las medidas de tiempos
		// (La primera medida se descarta)
		int[] tamanhoEjemplares = {100, 5000, 10000, 20000, 40000, 80000};
		
		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoEjemplares.length-1];
		Medida[] medidas2 = new Medida[tamanhoEjemplares.length-1];
		
		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for(int i=0; i<tamanhoEjemplares.length; i++) {
			// crea un array del tamanho indicado por el valor de n
			a1 = creaArrayDesordenado(tamanhoEjemplares[i], 1);
			
			tIni = System.currentTimeMillis(); // instante inicial
			
			// ejecuta el metodo del que se quiere medir su tiempo de ejecucion
			Ordenacion.insercion(a1);
			
			tFin = System.currentTimeMillis(); // instante final
			
			// alamcena la medida realizada (La primera medida se descarta)
			if (i > 0)
				medidas1[i-1] = new Medida(tamanhoEjemplares[i], tFin-tIni);
		}
		
		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for(int i=0; i<tamanhoEjemplares.length; i++) {
			// crea un array del tamanho indicado por el valor de n
			a2 = creaArrayDesordenado(tamanhoEjemplares[i], 1);
			
			tIni = System.currentTimeMillis(); // instante inicial
			
			// ejecuta el metodo del que se quiere medir su tiempo de ejecucion
			Arrays.sort(a2);
			
			tFin = System.currentTimeMillis(); // instante final
			
			// alamcena la medida realizada (La primera medida se descarta)
			if (i > 0)
				medidas2[i-1] = new Medida(tamanhoEjemplares[i], tFin-tIni);
		}
		
		// comprueba que los dos arrays son iguales
		if (!Arrays.equals(a1, a2)) {
			System.out.println("ERROR! El metodo insercion no ordena correctamente\n");
		}
		
		// Muestra las medidas obtenidas en una grafica
		OperacionesConMedidas.dibujaGraficas(medidas1, "Insercion", medidas2, "QuickSort");
	}

	/**
	 * Crea un arra del tamanho indicado con los elementos aleatorios (numeros
	 * positivos y negativos)
	 * @param tamanho tamanho del array a crear
	 * @param semilla semilla del generador de numeros aleatorios
	 * @return array generado
	 */
	public static int[] creaArrayDesordenado(int tamanho, int semilla) {
		final int rango=tamanho; // valores entre -rango..+rango
		int[] a = new int[tamanho];
		Random rand = new Random(semilla);

		for(int i=0; i<tamanho; i++)
			a[i]=rand.nextInt(rango*2+1)-rango;

		return a;
	}

	/**
	 * Crea un array de la longitud indicada. El array creado contiene
	 * numeros pares ordenados de menor a mayor: [0, 2, 4, 6, ...]
	 * @param longArray longitud del array a crear
	 * @return array [0, 2, 4, 6, ...]
	 */
	public static int[] creaArrayOrdenado(int longArray) {
		int[] a = new int[longArray];

		for(int i=0; i<longArray; i++) {
			a[i] = i * 2;
		}

		return a;
	}

}
