package pract12.Imserso;

/**
 * Clase que representa un cliente registrado en la agencia.
 * Por cada cliente se almacena solo su nombre y sus puntos, 
 * que se emplean para ordenar los clientes.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version dic-2017
 *
 */
public class Cliente implements Comparable<Cliente> {

	private final String nombre;
	private final int puntos;
	
	/**
	 * Constructor de la clase Cliente.
	 * @param nombre Nombre del cliente
	 * @param puntos puntos del cliente
	 */
	public Cliente(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos; 
	}

	/**
	 * Retorna el nombre del cliente.
	 * @return Nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna los puntos del cliente.
	 * @return puntos del cliente
	 */
	public int getPuntos() {
		return puntos;
	}
	
	@Override
	public String toString() {
		return "Nombre: "+nombre+", Puntuacion: "+puntos;
	}

	@Override
	public int compareTo(Cliente arg0) {
		int i = 0;
		if(this.getPuntos() < arg0.getPuntos()) {
			 i = 1;
		}
		if(this.getPuntos() == arg0.getPuntos()) {
			i = 0;
		}
		if(this.getPuntos() > arg0.getPuntos()) {
			i = -1;
		}
		return i;
	}
	
}

