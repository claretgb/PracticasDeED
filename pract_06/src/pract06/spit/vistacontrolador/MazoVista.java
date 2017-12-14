package pract06.spit.vistacontrolador;

/**
 * MazoVista
 * 
 * Implementación de la clase MazoVista, que representa
 * la forma de dibujar un mazo en la interfaz grafica 
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

import pract06.spit.modelo.Mazo;
import fundamentos.Dibujo;

public class MazoVista {

	private Mazo elMazo = null;
	
	// coordenadas del mazo en el tablero
	private int x;
	private int y;	
	
	// Constructor
	public MazoVista (int x, int y, Mazo mazo) {
		this.elMazo = mazo;
		this.x = x; 
		this.y = y;
	}
	
	/**
	 * Dibuja el mazo en el panel definido por dib
	 * @param dib: panel de dibujo
	 */
	public void dibuja (Dibujo dib)
	{
		if (!elMazo.estaVacio()) {
			CartaVista.dibuja(elMazo.cartaSuperior(), x, y, dib);
		}
		else
			dib.dibujaImagen(x,y,"src/img/sinCarta.png");
	}	
}
