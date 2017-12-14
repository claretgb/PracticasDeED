package Septiembre_2014;

import java.util.LinkedList;
import java.util.Queue;

public class Libro {

	private final String codigo;
	private final String titulo;
	private Queue<Socio> sociosALaEspera;
	private boolean prestado; 
	private Socio loTiene;
	
	public Libro(String codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
		sociosALaEspera = new LinkedList<Socio>();
		prestado = false;
		loTiene = null;
	}

	public String codigo() {
		return codigo;
	}

	public String titulo() {
		return titulo;
	}

	public Queue<Socio> getSociosALaEspera() {
		return sociosALaEspera;
	}

	public boolean prestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	public Socio loTiene() {
		return loTiene;
	}
	
	public void setLoTiene(Socio socio) {
		loTiene = socio;
	}
	
	public void anhadeSocioALaEspera(Socio socio) {
		sociosALaEspera.add(socio);
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", prestado=" + prestado 
				+ ", gente esperando=" + getSociosALaEspera().size() + "]";
	}
}
