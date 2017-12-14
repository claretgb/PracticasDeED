package pract07.modelo;

import java.util.ArrayDeque;

/**
 * Paciente de un centro de salud.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version nov-2017
 */
public class Paciente {

	private final String codTarjeta; // codigo de tarjeta sanitaria del paciente
	private final String nombre; // nombre del paciente
	private final String direccion; // direccion del paciente	
	private ArrayDeque<EntradaHistorial> historial;//historial del paciente

	/**
	 * Construye un paciente con un historial vacio
	 * @param codTarjeta codigo de tarjeta sanitaria del paciente
	 * @param nombre nombre del paciente
	 * @param direccion direccion del paciente
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public Paciente(String codTarjeta, String nombre, String direccion) {
		this.codTarjeta = codTarjeta;
		this.nombre = nombre;
		this.direccion = direccion;
		historial = new ArrayDeque<EntradaHistorial>();
	}

	/**
	 * Retorna el codigo de tarjeta sanitaria del paciente
	 * @return codigo de tarjeta sanitaria del paciente
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public String codTarjeta() {
		return codTarjeta;
	}

	/**
	 * Retorna el nombre del paciente
	 * @return nombre del paciente
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna la direccion del paciente
	 * @return direccion del paciente
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public String direccion() {
		return direccion;
	}
	
	//metodos para gestionar el historial del paciente
	
	/**
	 * Retorna el historial del paciente
	 * @return historial del paciente
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public ArrayDeque<EntradaHistorial> historial() {
		return historial;
	}
	
	/**
	 * Añade una visita al historial del paciente
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public void anhadirVisita(EntradaHistorial visita) {
		historial.add(visita);
	}
}
