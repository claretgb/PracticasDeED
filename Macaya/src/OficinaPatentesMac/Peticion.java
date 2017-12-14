package OficinaPatentesMac;

public class Peticion {

	private String descripcion;
	private static int CONTADOR = 1;
	private int numeroDePeticion;
	
	public Peticion(String descripcion) {
		this.descripcion = descripcion;
		numeroDePeticion = CONTADOR;
		CONTADOR++;
	}
	
	public String descripcion() {
		return descripcion;
	}
	
	public int numeroDePeticion() {
		return numeroDePeticion;
	}
}
