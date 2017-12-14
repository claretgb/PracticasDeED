package pract06.spit.vistacontrolador;

import fundamentos.*;

/**
 * GUI
 * 
 * Implementación de la clase GUI, que representa
 * la interfaz grafica del juego "Spit" 
 * 
 * @author Estructuras de Datos
 * @version oct-2015
 */

public class GUI extends Thread {
	
	//  Panel de dibujo
	private Dibujo dib;
	//  Menu de interaccion con el usuario
	private Menu menu;
	//  Vista gráfica asociada al tablero del juego
	private TableroVista ventanas;
	//  Intervalo entre actualizaciones de la interfaz gráfica
	private final int PERIOD_IN_MS = 200;
	
	// Constructor
	public GUI (TableroVista vistaTablero) 
	{
		dib  = new Dibujo("Spit",400,580);
		menu = new Menu("Movimientos del usuario (Estructuras de Datos - UNICAN)");
		ventanas = vistaTablero;
		
		menu.println("Elige una opcion de movimiento");
		menu.insertaOpcion("Usa carta del mazo 1",1);
		menu.insertaOpcion("Usa carta del mazo 2",2);
		menu.insertaOpcion("Usa carta del mazo 3",3);
		menu.insertaOpcion("Usa carta del mazo 4",4);
		menu.insertaOpcion("Usa carta nueva",5);
		
		this.start(); // Comienza la actividad de la GUI
	}
	
	/**
	 * Devuelve el boton pulsado por el usuario
	 * @return numero de botón pulsado
	 */
	public int obtenMovimiento () {
		return menu.leeOpcion();
	}
	
	/**
	 * Crea una ventana con mensaje por pantalla
	 * @param frase a escribir
	 */
	public void VentanaMensaje (String frase) {		
	      Mensaje men = new Mensaje();
	      men.escribe(frase);
	}
	
	/**
	 * Escribe un mensaje por pantalla
	 * @param frase a escribir
	 */
	public void PintaMensaje (String frase) {		
	      menu.println(frase);
	}
	
	// Actividad de la GUI
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(!Thread.interrupted()){
			actualizaGUI();
			try {sleep(PERIOD_IN_MS);
			} catch (InterruptedException e) {
				break;
			}
		}
		actualizaGUI();
		menu.cierra();
		//  Fundamentos no permite cerrar el panel de dibujo
	}
	
	/**
	 * Termina la interfaz grafica
	 */
	public void termina() {
		this.interrupt();
	}

	/**
	 * Actualiza los elementos contenidos en la interfaz gráfica
	 */
	private void actualizaGUI () 
	{
		ventanas.dibuja(dib);
		dib.pinta();
	}

}
