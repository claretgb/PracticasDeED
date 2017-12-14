package pract10.modelo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Oficina de una empresa de alquiler de vehiculos
 * 
 * @author <Clara Torre García-Barredo> y Estructuras de Datos (UC)
 * @version nov-2017
 */
public class Oficina {
	// nombre de la oficina
	private final String nombre;
	private Queue<Vehiculo> vehiculos;
	
	@SuppressWarnings("serial")
	public static class VehiculoYaExistente extends RuntimeException {}
	
	/**
	 * Construye una oficina con el nombre indicado
	 * @param nombre nombre de la oficina
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public Oficina(String nombre) {
		this.nombre = nombre;
		vehiculos = new LinkedList<Vehiculo>();
	}
	
	/**
	 * Retorna el nombre de la oficina
	 * @return el nombre de la oficina
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * Retorna el numero de vehiculos disponibles para alquiler en la oficina
	 * @return numero de vehiculos disponibles para alquiler en la oficina
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public int numVehiculosDisponibles() {
		return vehiculos.size();
	}

	/**
	 * Método que añade un vehículo a la cola de disponibles para alquilar de la oficina.
	 * @param vehiculo el vehículo a añadir
	 * @throws VehiculoYaExistente si el vehículo ya estaba añadido en la oficina. 
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public void anhadeVehiculo(Vehiculo vehiculo) throws VehiculoYaExistente {
		if(vehiculos.contains(vehiculo)) {
			throw new VehiculoYaExistente();
		}
		vehiculos.add(vehiculo);
	}
	
	/**
	 * Método que alquila un vehículo, retirándolo de la cola de disponibles de la oficina.
	 * El vehículo debe ser el que más tiempo lleve en la cola de disponibles, y que cumpla 
	 * exactamente las exigencias del cliente.
	 * Además, este método devuelve el vehículo que ha sido alquilado.
	 * @param numPlazas el número de plazas que tiene que tener el vehículo. 
	 * @param tipoCombustible el tipo de combustible que tiene que tener el vehículo.
	 * @return vehiculo el vehículo alquilado.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public Vehiculo alquilaVehiculo(int numPlazas, Vehiculo.TipoCombustible tipoCombustible) {
		Iterator<Vehiculo> iterador = vehiculos.iterator();
		while(iterador.hasNext()) {
			Vehiculo vehiculo = iterador.next();
			if(vehiculo.numPlazas() == numPlazas && vehiculo.tipoCombustible() == tipoCombustible) {
				vehiculo.setOficina(null);
				iterador.remove();
				return vehiculo;
			}
		}
		return null;
	}

	/**
	 * Método que devuelve un vehículo a la oficina, después de estar alquilado. 
	 * @param vehiculo el vehículo que necesita ser devuelto a la oficina.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public void devuelveVehiculo(Vehiculo vehiculo) {
		vehiculos.add(vehiculo);
		vehiculo.setOficina(this);
	}
}
