package pract07.modelo;

/**
 * Entrada en el historial de un paciente del centro
 * de salud.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2017
 */
public class EntradaHistorial
{
    private final String observaciones;
    private final int especialidad; 

    /**
     * Crea una entrada con los datos indicados.
     * @param observaciones observaciones de la entrada
     * @param especialidad especialidad con la que esta relacionada la entrada
     */
	/*
	 * Complejidad temporal: O(1).
	 */
    public EntradaHistorial(String observaciones, int especialidad)
    {
        this.observaciones=observaciones;
        this.especialidad = especialidad;
    }

    /**
     * Retorna las observaciones de la entrada.
     * @return observaciones de la entrada.
     */
	/*
	 * Complejidad temporal: O(1).
	 */
    public String observaciones() {
    	return observaciones;
    }
    
    /**
     * Retorna la especialidad con la que esta relacionada la entrada.
     * @return especialidad con la que esta relacionada la entrada.
     */
	/*
	 * Complejidad temporal: O(1).
	 */
    public int especialidad() {
    	return especialidad;
    }

	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public String toString() {
		return "(esp: " + especialidad + ") " + observaciones;
	}
    
    
}
