package pract10.modelo;

/**
 * Vehiculo en alquiler.
 * 
 * @author <Clara Torre García-Barredo> y Estructuras de Datos (UC)
 * @version nov-2017
 */
public class Vehiculo {
	public static enum TipoCombustible {Gasolina, Diesel, Electrico, Hibrido}
	
	// datos del vehiculo
	private final String matricula;
	private final TipoCombustible tipoCombustible;
	private final int numPlazas;
	private Oficina oficina;

	/**
	 * Construye un vehiculo con los datos indicados
	 * @param matricula matricula del vehiculo
	 * @param tipoCombustible tipo de combustible del vehiculo
	 * @param numPlazas numero de plazas del vehiculo
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public Vehiculo(String matricula, TipoCombustible tipoCombustible, int numPlazas) {
		this.matricula = matricula;
		this.tipoCombustible = tipoCombustible;
		this.numPlazas = numPlazas;
		oficina = null;
	}

	/**
	 * Retorna la matricula del vehiculo
	 * @return matricula del vehiculo
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public String matricula() {
		return matricula;
	}

	/**
	 * Retorna el tipo de combustible del vehiculo
	 * @return tipo de combustible del vehiculo
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public TipoCombustible tipoCombustible() {
		return tipoCombustible;
	}
	
	/**
	 * Retorna el numero de plazas del vehiculo
	 * @return numero de plazas del vehiculo
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public int numPlazas() {
		return numPlazas;
	}
	
	/**
	 * Oficina en la que se encuentra estacionado el vehiculo, o null si el vehiculo
	 * se encuentra alquilado
	 * @return oficina en la que se encuentra estacionado el vehiculo
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public Oficina oficina() {
		return oficina;
	}
	
	/**
	 * Método que cambia la oficina en la que está el vehículo por la que se le pasa.
	 * @param oficina la oficina en la que pasa a estar el producto.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}
	
}
