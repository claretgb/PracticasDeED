package pract07.gui;

import java.util.Collection;
import java.util.List;

import pract07.modelo.CentroSalud;
import pract07.modelo.EntradaHistorial;
import pract07.modelo.Paciente;
import pract07.modelo.CentroSalud.EspecialidadIncorrecta;
import pract07.modelo.CentroSalud.TarjetaIncorrecta;
import fundamentos.*;

/**
 * Interfaz Grafica de Usuario (GUI) de la aplicacion de
 * gestion de un centro de salud.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version nov-2017
 */
public class GUICentroSalud {

	public static void main(String[] args) {
		// opciones del menu
		final int LLEGA_PACIENTE=0, SIGUIENTE_PACIENTE=1, ACTUALIZA_HISTORIAL=2,
				CONSULTA_HISTORIAL=4;

		// variables auxiliares
		Lectura lect;
		int especialidad;
		String codTarjeta;

		// crea el centro de salud con 4 especialidades
		CentroSalud centroSalud = new CentroSalud(4);

		// crea el menu
		Menu menu = new Menu("Gestion Centro Salud");
		menu.insertaOpcion("Llega paciente", LLEGA_PACIENTE);
		menu.insertaOpcion("Siguiente paciente", SIGUIENTE_PACIENTE);
		menu.insertaOpcion("Actualiza historial", ACTUALIZA_HISTORIAL);
		menu.insertaOpcion("Consulta historial", CONSULTA_HISTORIAL);
		int opcion = 0;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case LLEGA_PACIENTE:
				lect = new Lectura("Llega paciente");
				lect.creaEntrada("Cod. tarjeta", "");
				lect.creaEntrada("Especialidad", "");
				lect.esperaYCierra();
				codTarjeta = lect.leeString("Cod. tarjeta");
				especialidad = lect.leeInt("Especialidad");
				
				if (!centroSalud.existeTarjeta(codTarjeta)) {
					// Es un paciente nuevo
					
					// Pide sus datos
					Lectura lect2 = new Lectura("Datos paciente");
					lect2.creaEntrada("Nombre", "");
					lect2.creaEntrada("Direccion", "");
					lect2.esperaYCierra();
					String nombre = lect2.leeString("Nombre");
					String direccion = lect2.leeString("Direccion");
					
					// registra el nuevo paciente
					centroSalud.registraNuevoPaciente(
							new Paciente(codTarjeta, nombre, direccion));
				}

				// pone el paciente a la espera
				try {
					centroSalud.pacienteEnEspera(codTarjeta, especialidad);
				} catch (CentroSalud.EspecialidadIncorrecta e) {
					mensaje("Error", "Num. especialiad incorrecto");
				}
				break;

			case SIGUIENTE_PACIENTE:
				lect = new Lectura("Siguiente paciente");
				lect.creaEntrada("Especialidad", "");
				lect.esperaYCierra();
				especialidad = lect.leeInt("Especialidad");
				
				try {
					Paciente paciente = centroSalud.siguientePaciente(especialidad);
					if (paciente == null) {
						mensaje("Siguiente paciente (esp. " + especialidad + ")",
								"No hay pacientes a la espera");
					} else {
						mensaje("Siguiente paciente (esp. " + especialidad + ")",
								"Nombre:" + paciente.nombre() +
								"\nCod. tarjeta:" + paciente.codTarjeta());
					}
				} catch (CentroSalud.EspecialidadIncorrecta e) {
					mensaje("Error", "Num. especialiad incorrecto");
				}
				break;

			case ACTUALIZA_HISTORIAL:
				lect = new Lectura("Nueva entrada en el historial");
				lect.creaEntrada("Cod. tarjeta", "");
				lect.creaEntrada("Observaciones", "");
				lect.creaEntrada("Especialidad", "");
				lect.esperaYCierra();
				codTarjeta = lect.leeString("Cod. tarjeta");
				String observaciones = lect.leeString("Observaciones");
				especialidad = lect.leeInt("Especialidad");
				
				try {
					centroSalud.actualizaHistorialPaciente(codTarjeta,
							new EntradaHistorial(observaciones, especialidad));
					
				} catch (CentroSalud.EspecialidadIncorrecta e) {
					mensaje("Error", "Num. especialiad incorrecto");
				} catch (CentroSalud.TarjetaIncorrecta e) {
					mensaje("Error", "Cod. tarjeta incorrecto");
				}
				
				break;

			case CONSULTA_HISTORIAL:
				lect = new Lectura("Consulta historial");
				lect.creaEntrada("Cod. tarjeta", "");
				lect.creaEntrada("Especialidad", "");
				lect.esperaYCierra();
				codTarjeta = lect.leeString("Cod. tarjeta");
				especialidad = lect.leeInt("Especialidad");
				
				try {
					List<EntradaHistorial> listHistorial =
							centroSalud.historialEspecialidad(codTarjeta, especialidad);
					muestraColeccion("Cod. tarjeta:" + codTarjeta + " Esp.:" + especialidad,
							listHistorial);
					
				} catch (CentroSalud.EspecialidadIncorrecta e) {
					mensaje("Error", "Num. especialiad incorrecto");
				} catch (CentroSalud.TarjetaIncorrecta e) {
					mensaje("Error", "Cod. tarjeta incorrecto");
				}
				
				break;
			}
		}
	}

	/**
	 * Muestra un ventana de Mensaje del paquete Fundamentos.
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}

	/**
	 * Muestra en una Caja Texto del paquete fundamentos los elementos de la
	 * coleccion pasada como parametro.
	 * Cada elemento se muestra en una linea.
	 * Llama al metodo toString de los elementos de la coleccion.
	 * @param titulo titulo de la ventana
	 * @param coleccion coleccion de elementos a mostrar
	 */
	private static void muestraColeccion(String titulo, Collection<?> coleccion) {
		CajaTexto caja = new CajaTexto(titulo, 10, 25);
		for(Object obj: coleccion) {
			caja.println(obj.toString());
		}
		caja.esperaYCierra();
	}

}
