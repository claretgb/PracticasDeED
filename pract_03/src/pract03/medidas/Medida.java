package pract03.medidas;

/**
 * Clase sencilla que permite almacenar una medida de tiempo: tamanho del
 * ejemplar y tiempo que ha tardado en realizarse el algoritmo medido para
 * ese tamanho.
 * 
 * @author Estructuras de Datos
 * @version sep-14
 */
public class Medida {
	public final int n;   // tamanho del ejemplar
	public final double ms; // milisegundos de la medida
	
	/**
	 * Construye una medida con el tamanho y el tiempo pasados como 
	 * parametros
	 * @param n tamanho del ejemplar al que corresponde la medida
	 * @param ms tiempo en milisegundos de la medida
	 */
	public Medida(int n, double ms) {
		this.n = n;
		this.ms = ms;
	}
}
