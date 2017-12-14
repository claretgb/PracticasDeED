package Septiembre_2014;

import java.util.LinkedList;
import java.util.List;

public class Socio {

	private static int CONTADOR = 0;
	private final int numeroSocio;
	private final String nombre;
	private List<Libro> librosPrestados;
	
	public Socio(String nombre) {
		numeroSocio = CONTADOR;
		CONTADOR++;
		this.nombre = nombre;
		librosPrestados = new LinkedList<Libro>();
	}

	public List<Libro> librosPrestados() {
		return librosPrestados;
	}

	public int numeroSocio() {
		return numeroSocio;
	}

	public String nombre() {
		return nombre;
	}
	
	public void anhadeLibroPrestado(Libro libro) {
		librosPrestados.add(libro);
	}
	
	public void devuelveLibroPrestado(Libro libro) {
		librosPrestados.remove(libro);
	}
}
