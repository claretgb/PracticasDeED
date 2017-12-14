package pract10.gui;

import java.util.Collection;

import pract10.modelo.EmpresaAlquiler;
import pract10.modelo.Oficina;
import pract10.modelo.Vehiculo;
import fundamentos.*;

/**
 * Clase principal del programa de gestion de una empresa
 * de alquiler de vehiculos.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2017
 */
public class GestionEmpresaAlquiler {

	public static void main(String[] args) {
		// opciones del menu
		final int ANHADE_OFICINA=0, ANHADE_VEHICULO=1, ALQUILA_VEHICULO=2,
				DEVUELVE_VEHICULO=4, LOCALIZA_VEHICULO=5, LISTADO_OFICINAS=6;

		// variables auxiliares
		Lectura lect;
		String nombreCiudad;
		String matricula;
		int numPlazas;
		Vehiculo.TipoCombustible combustible;

		// crea la empresa de alquiler
		EmpresaAlquiler empresa = new EmpresaAlquiler(5000);		

		// crea el menu
		Menu menu = new Menu("Gestion Empresa Alquiler");
		menu.insertaOpcion("Anhade Oficina", ANHADE_OFICINA);
		menu.insertaOpcion("Anhade vehiculo", ANHADE_VEHICULO);
		menu.insertaOpcion("Alquila vehiculo", ALQUILA_VEHICULO);
		menu.insertaOpcion("Devuelve vehiculo", DEVUELVE_VEHICULO);
		menu.insertaOpcion("Localiza vehiculo", LOCALIZA_VEHICULO);
		menu.insertaOpcion("Listado oficinas", LISTADO_OFICINAS);
		int opcion = 0;

		// lazo de espera de comandos del usuario
		while(true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opción elegida
			switch (opcion) {
			case ANHADE_OFICINA:
				lect = new Lectura("Nueva oficina");
				lect.creaEntrada("Nombre ciudad", "Santander");
				lect.esperaYCierra();
				nombreCiudad = lect.leeString("Nombre ciudad");

				// anhade la oficina a la empresa
				try {
					empresa.anhadeOficina(new Oficina(nombreCiudad));
				} catch (EmpresaAlquiler.OficinaYaExistente e) {
					mensaje("Error", "Nombre de oficina ya existente");
				}
				break;

			case ANHADE_VEHICULO:
				lect = new Lectura("Nuevo vehiculo");
				lect.creaEntrada("Matricula", "1111-AAA");
				lect.creaEntrada("Num plazas", "5");
				lect.creaEntrada("Combustible", "Diesel");
				lect.creaEntrada("Nombre ciudad", "Santander");
				lect.esperaYCierra();
				matricula = lect.leeString("Matricula");
				numPlazas = lect.leeInt("Num plazas");
				try {
					combustible =
							Vehiculo.TipoCombustible.valueOf(lect.leeString("Combustible"));
				} catch (IllegalArgumentException e) {
					mensaje("Error", "Tipo combustible incorrecto");
					break;
				}
				nombreCiudad = lect.leeString("Nombre ciudad");

				// anhade el vehiculo a la empresa
				try {
					empresa.anhadeVehiculo(new Vehiculo(matricula, combustible, numPlazas),
							nombreCiudad);
				} catch (EmpresaAlquiler.VehiculoYaExistente e) {
					mensaje("Error", "Matricula ya existente");
				} catch (EmpresaAlquiler.NombreOficinaIncorrecto e) {
					mensaje("Error", "El nombre no corresponde a ninguna oficina");
				}
				break;

			case ALQUILA_VEHICULO:
				lect = new Lectura("Alquila vehiculo");
				lect.creaEntrada("Nombre ciudad", "Santander");
				lect.creaEntrada("Num plazas", "5");
				lect.creaEntrada("Combustible", "Diesel");
				lect.esperaYCierra();
				nombreCiudad = lect.leeString("Nombre ciudad");
				numPlazas = lect.leeInt("Num plazas");
				try {
					combustible =
							Vehiculo.TipoCombustible.valueOf(lect.leeString("Combustible"));
				} catch (IllegalArgumentException e) {
					mensaje("Error", "Tipo combustible incorrecto");
					break;
				}

				// alquila el vehiculo
				try {
					Vehiculo vehiculo =
							empresa.alquilaVehiculo(nombreCiudad, numPlazas, combustible);

					if (vehiculo == null) {
						mensaje("Vehiculo no disponible",
								"NO hay vehiculos disponibles con las caracteristicas indicadas");
					} else {
						mensaje("Vehiculo alquilado",
								"Matricula:" + vehiculo.matricula());
					}

				} catch (EmpresaAlquiler.NombreOficinaIncorrecto e) {
					mensaje("Error", "El nombre no corresponde a ninguna oficina");
				}
				break;

			case DEVUELVE_VEHICULO:
				lect = new Lectura("Devuelve vehiculo");
				lect.creaEntrada("Nombre ciudad", "Santander");
				lect.creaEntrada("Matricula", "1111-AAA");
				lect.esperaYCierra();
				nombreCiudad = lect.leeString("Nombre ciudad");
				matricula = lect.leeString("Matricula");

				// devuelve el vehiculo
				try {
					empresa.devuelveVehiculo(nombreCiudad, matricula);

				} catch (EmpresaAlquiler.NombreOficinaIncorrecto e) {
					mensaje("Error", "El nombre no corresponde a ninguna oficina");
				} catch (EmpresaAlquiler.MatriculaVehiculoIncorrecta e) {
					mensaje("Error", "La matricula no corresponde a ningun vehiculo");
				} catch (EmpresaAlquiler.VehiculoNoAlquilado e) {
					mensaje("Error", "El vehiculo no se encuentra alquilado en este momento");
				}
				break;

			case LOCALIZA_VEHICULO:
				lect = new Lectura("localiza vehiculo");
				lect.creaEntrada("Matricula", "1111-AAA");
				lect.esperaYCierra();
				matricula = lect.leeString("Matricula");

				// busca el vehiculo
				Vehiculo vehiculo = empresa.buscaVehiculo(matricula);
				if (vehiculo==null)
				{
					mensaje("Error", "La matricula no corresponde a ningun vehiculo");
				} else {
					if (vehiculo.oficina() == null) {
						mensaje("Vehiculo alquilado",
								"El vehiculo " + vehiculo.matricula() +
								" se encuentra alquilado en este momento");
					} else {
						mensaje("Vehiculo en oficina",
								"El vehiculo " + vehiculo.matricula() +
								" se encuentra disponible en la oficina " +
								vehiculo.oficina().nombre());
					}
				} 
				break;

			case LISTADO_OFICINAS:
				muestraColeccion("Oficinas", empresa.oficinas());
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
	private static void muestraColeccion(String titulo, Collection<Oficina> coleccion) {
		CajaTexto caja = new CajaTexto(titulo, 10, 25);
		for(Oficina oficina: coleccion) {
			caja.println(oficina.nombre() + ": " + oficina.numVehiculosDisponibles() +
					" vehiculos disponibles");
		}
		caja.esperaYCierra();
	}

}
