package parte_1;

import java.util.Random;

import medidas.Medida;
import medidas.OperacionesConMedidas;


/**
 * Programa para medir tiempos de ejecucion
 * 
 * @author Estructuras de Datos (UC)
 * @version sep-17
 */
public class RealizaMedidas {

	/**
	 * Programa para medir tiempos de ejecucion
	 */
	public static void main(String[] args) {
		final int NUM_REP_MEDIDA = 10; // veces que se realiza cada medida
		long t0, t1, t2; // tiempos (en ms)
		int[] a;
		int iMax1=0, iMax2=0;

		// Tamanho de los ejemplares (valores de n) para los que se van
		// a realizar las medidas de tiempos
		int[] tamanhoEjemplares = {1000, 2000, 4000, 8000, 16000, 32000};

		// Medidas de tiempos. Cada medida contiene el valor de n y el tiempo
		// que ha tardado en ejecutarse
		Medida[] medidas1 = new Medida[tamanhoEjemplares.length-1];
		Medida[] medidas2 = new Medida[tamanhoEjemplares.length-1];

		// realiza las medidas para los valores de n almacenados en el array tamanhoEjemplares
		for(int i=0; i<tamanhoEjemplares.length; i++) {
			// crea un array del tamanho indicado por el valor de n
			a = creaArrayAleatorio(tamanhoEjemplares[i], 100);

			// Mide el tiempo de ejecucion de los dos metodos

			t0 = System.currentTimeMillis(); // instante inicial

			for (int j=0; j<NUM_REP_MEDIDA; j++) {
				iMax1 = OperacionesArraysEnteros.indiceUltimoMaximoParcial(a);
			}

			t1 = System.currentTimeMillis(); // instante intermedio

			for (int j=0; j<NUM_REP_MEDIDA; j++) {
				iMax2 = OperacionesArraysEnteros.indiceUltimoMaximoParcialEficiente(a);
			}

			t2 = System.currentTimeMillis(); // instante final

			// almacena las medidas realizadas (La primera medida se descarta)
			if (i > 0) {
				medidas1[i-1] = 
						new Medida(tamanhoEjemplares[i], (double)(t1-t0)/NUM_REP_MEDIDA);
				medidas2[i-1] = 
						new Medida(tamanhoEjemplares[i], (double)(t2-t1)/NUM_REP_MEDIDA);
			}

			// comprueba que los dos metodos han obtenido el mismo valor
			if (iMax1 != iMax2) {
				System.out.println("Error: iMax1 != iMax2");
			}
		}

		// Muestra las medidas obtenidas en una grafica
		OperacionesConMedidas.dibujaGraficas(medidas1, "Metodo Original",
				medidas2, "Metodo Eficiente");
	}

	/**
	 * Crea un array de numeros enteros del tamanho indicado con numeros
	 * aleatorios de valores comprendidos entre -rango..+rango
	 * @param tamanho tamanho del array a crear
	 * @param rango rango de los numeros aleatorios
	 * @return array generado
	 */
	public static int[] creaArrayAleatorio(int tamanho, int rango) {
		int[] a = new int[tamanho];
		Random rand = new Random();

		for(int i=0; i<tamanho; i++) {
			a[i]=rand.nextInt(rango*2+1)-rango;
		}

		return a;
	}
}
