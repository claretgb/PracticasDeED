package carrera;

import java.util.Arrays;

public class Equipo implements Comparable<Equipo>{

	private String nombre;
	private Participante[] miembros;
	private Double tiempo;
	int CONTADOR = 0;
	
	public Equipo(Participante[] miembros, String nombre) {
		this.nombre = nombre;
		this.miembros = new Participante[10];
		this.miembros = miembros;
		tiempo = 0.0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Participante[] getMiembros() {
		return miembros;
	}

	public void setMiembros(Participante[] miembros) {
		this.miembros = miembros;
	}

	public double getTiempo() {
		return tiempo;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", miembros=" + Arrays.toString(miembros) + ", tiempo=" + tiempo
				+ ", CONTADOR=" + CONTADOR + "]";
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public int compareTo(Equipo o) {
		return this.tiempo.compareTo(o.tiempo);
	}
	
}
