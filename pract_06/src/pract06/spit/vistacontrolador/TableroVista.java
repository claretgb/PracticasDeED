package pract06.spit.vistacontrolador;

/**
 * TableroVista
 * 
 * Implementación de la clase TableroVista, que representa
 * la forma de dibujar un tablero en la interfaz grafica 
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

import pract06.spit.modelo.Mazo;
import pract06.spit.modelo.Tablero;
import fundamentos.Dibujo;

public class TableroVista {
	
	private MazoVista vistaMazoDescartes[] = new MazoVista [2];
	private MazoVista vistaMazoJuegoJug1[] = new MazoVista [4];
	private MazoVista vistaMazoJuegoJug2[] = new MazoVista [4];
	private MazoVista vistaMazoExtra[]     = new MazoVista [2];
	
	/**
	 * Constructor
	 * @param tablero: el tablero sobre el que se realizan las operaciones
	 */
	public TableroVista (Tablero tablero) {
		
		//  Posiciones de los mazos fija y constantes
		final int DESCARTES_POS_X = 110;
		final int DESCARTES_POS_Y = 200;
		final int JUEGO_POS_X = 10;
		final int JUEGO_JUG1_POS_Y = 0;
		final int JUEGO_JUG2_POS_Y = 400;
		final int RESERVA_JUG1_POS_X = 10;
		final int RESERVA_JUG1_POS_Y = 200;
		final int RESERVA_JUG2_POS_X = 310;
		final int RESERVA_JUG2_POS_Y = 200;
		
		//  Posicionamos mazos en la interfaz grafica
		MazosEnTablero (DESCARTES_POS_X, DESCARTES_POS_Y, tablero.getMazoDescartes(), vistaMazoDescartes);
		MazosEnTablero (JUEGO_POS_X, JUEGO_JUG1_POS_Y, tablero.getMazoJuegoJug1(), vistaMazoJuegoJug1);
		MazosEnTablero (JUEGO_POS_X, JUEGO_JUG2_POS_Y, tablero.getMazoJuegoJug2(), vistaMazoJuegoJug2);
		
		vistaMazoExtra[0] = MazoEnTablero (RESERVA_JUG1_POS_X, RESERVA_JUG1_POS_Y, tablero.getMazoReserva()[0]);
		vistaMazoExtra[1] = MazoEnTablero (RESERVA_JUG2_POS_X, RESERVA_JUG2_POS_Y, tablero.getMazoReserva()[1]);
	}
	
	/**
	 * Dibuja el tablero en el panel definido por dib
	 * @param dib: panel de dibujo
	 */
	public void dibuja (Dibujo dib) 
	{
		for (int index = 0; index < vistaMazoExtra.length; index++) {
			vistaMazoExtra[index].dibuja(dib);
		}
		
		for (int index = 0; index < vistaMazoDescartes.length; index++) {
			vistaMazoDescartes[index].dibuja(dib);
		}
		for (int index = 0; index < vistaMazoJuegoJug1.length; index++) {
			vistaMazoJuegoJug1[index].dibuja(dib);
		}
		for (int index = 0; index < vistaMazoJuegoJug2.length; index++) {
			vistaMazoJuegoJug2[index].dibuja(dib);
		}

	}
	
	//  METODOS PRIVADOS
	
	/**
	 * Establece la ubicacion de todos los mazos de un mismo tipo en el tablero
	 * @param coorX: posicion en el eje horizontal
	 * @param coorY: posicion en el eje vertical
	 * @param mazo : conjunto de mazos "reales" (sobre los que se realizan las operaciones)
	 * @param mazoVista: vista gráfica asociada a los mazos "reales"
	 */
	private void MazosEnTablero (int coorX, int coorY, Mazo[] mazo, MazoVista[] mazoVista) {
		for (int index = 0; index < mazoVista.length; index++) {
			mazoVista[index] = MazoEnTablero (coorX,coorY, mazo[index]);
			coorX = coorX + 100;
		}
	}
	
	/**
	 * Establece la ubicacion del mazo en el tablero
	 * @param coorX: posicion en el eje horizontal
	 * @param coorY: posicion en el eje vertical
	 * @param mazo : mazo "real" (sobre el que se realizan las operaciones)
	 * @return variable con la vista gráfica del mazo "real"
	 */
	private MazoVista MazoEnTablero (int coorX, int coorY, Mazo mazo) {
		return new MazoVista (coorX,coorY, mazo);
	}
}
