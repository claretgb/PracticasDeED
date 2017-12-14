package carrera;

public class Participante {

	private static int CONTADOR=1;
	private int numeroDorsal;
	private double tiempo;
	private Equipo equipo;
	
	public Participante(Equipo equipo) {
		numeroDorsal = CONTADOR;
		CONTADOR++;
		tiempo = 0;
		this.equipo = equipo;
	}

	public int getNumeroDorsal() {
		return numeroDorsal;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
}
