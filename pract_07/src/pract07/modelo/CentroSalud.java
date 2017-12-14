package pract07.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Centro de salud, con sus colas de pacientes por especialidad.
 * 
 * @author Estructuras de Datos (UC) y <Clara Torre García-Barredo>
 * @version nov-2017
 */
public class CentroSalud {
	
	// Colas de las especialidades
	private Queue<Paciente>[] especialidades;
	
	// Registro con los datos de los pacientes
	private List<Paciente> registroDatos = new LinkedList<Paciente>();
	
	@SuppressWarnings("serial")
	public static class EspecialidadIncorrecta extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class TarjetaIncorrecta extends RuntimeException {}

	/**
	 * Construye el array de colas con tantas colas como especialidades y 
	 * construye las colas vacias
	 * @param numEspecialidades
	 */
	/*
	 * Complejidad temporal: O(numEspecialidades).
	 */
	@SuppressWarnings("unchecked")
	public CentroSalud(int numEspecialidades) {
		especialidades = new Queue[numEspecialidades];
		for(int i = 0; i < numEspecialidades; i++){
			especialidades[i] = new LinkedList<Paciente>();
		}
	}
	
	/**
	 * Un paciente ya registrado en el centro de salud se pone en espera
	 * de ser atendido en la especialidad indicada.
	 * @param codTarjeta codigo de la tarjeta del paciente.
	 * @param especialidad especialidad en la que el paciente quiere ser atendido.
	 * @throws TarjetaIncorrecta cuando el codigo de tarjeta no corresponde a ningun
	 * paciente registrado en el centro.
	 * @throws EspecialidadIncorrecta cuando el numero de la especialidad no se
	 * corresponde con ninguna de las especialidades del centro
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public void pacienteEnEspera(String codTarjeta, int especialidad)
			throws TarjetaIncorrecta, EspecialidadIncorrecta {
		if(!existeTarjeta(codTarjeta)) {
			throw new TarjetaIncorrecta();
		}
		if(especialidad >= especialidades.length) {
			throw new EspecialidadIncorrecta();
		}
		Paciente paciente = busca(codTarjeta);
		especialidades[especialidad].add(paciente);
	}
	
	/**
	 * Registra un paciente en el registro de pacientes del centro.
	 * @param paciente paciente a registrar.
	 * @throws RegistroPacientes.CodTarjetaYaExistente cuando ya hay
	 * otro paciente en el centro con el mismo codigo de tarjeta.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public void registraNuevoPaciente(Paciente paciente)
			throws TarjetaIncorrecta {
		if(existeTarjeta(paciente.codTarjeta())) {
			throw new TarjetaIncorrecta();
		}
		registroDatos.add(paciente);
	}

	/**
	 * El siguiente paciente de la especialidad indicada puede pasar a consulta con 
	 * el medico. El paciente deja de estar a la espera.
	 * @param especialidad especialidad de la que se quiere atender un paciente
	 * @return paciente a atender de la especialidad indicada o null en el caso
	 * de que no haya ningun paciente esperando
	 * @throws EspecialidadIncorrecta cuando el numero de la especialidad no se
	 * corresponde con ninguna de las especialidades del centro
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public Paciente siguientePaciente(int especialidad) throws EspecialidadIncorrecta {
		if(especialidad >= especialidades.length || especialidad < 0) {
			throw new EspecialidadIncorrecta();
		}
		if(especialidades[especialidad].size() != 0) {
				Paciente paciente = (Paciente) especialidades[especialidad].remove();
				return paciente;
		}
		return null;
	}

	/**
	 * Anhade una entrada en el historial del paciente.
	 * @param codTarjeta codigo de la tarjeta del paciente al que se quiere anhadir
	 * la entrada.
	 * @param entradaHistorial entrada a anhadir en el historial del paciente.
	 * @throws TarjetaIncorrecta cuando no existe ningun paciente registrado con
	 * el codigo de tarjeta indicado.
	 * @throws EspecialidadIncorrecta cuando el numero de la especialidad no se
	 * corresponde con ninguna de las especialidades del centro
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public void actualizaHistorialPaciente(String codTarjeta, EntradaHistorial entradaHistorial)
			throws TarjetaIncorrecta, EspecialidadIncorrecta {
		if(!existeTarjeta(codTarjeta)) {
			throw new TarjetaIncorrecta();
		}
		if(entradaHistorial.especialidad() >= especialidades.length 
				|| entradaHistorial.especialidad() < 0) {
			throw new EspecialidadIncorrecta();
		}
		Paciente paciente = busca(codTarjeta);
		paciente.anhadirVisita(entradaHistorial);
	}
	
	/**
	 * Retorna las entradas del historial del paciente relacionadas con la especialidad
	 * indicada. Las entradas se encuentran ordenadas de mas modernas a mas antiguas.
	 * @param codTarjeta codigo de la tarjeta del paciente.
	 * @param especialidad especialidad relacionada con las entradas del historial buscadas.
	 * @return lista con las entradas del historial del paciente relacionadas con la
	 * especialidad indicada.
	 * @throws TarjetaIncorrecta cuando no existe ningun paciente registrado con
	 * el codigo de tarjeta indicado.
	 * @throws EspecialidadIncorrecta cuando el numero de la especialidad no se
	 * corresponde con ninguna de las especialidades del centro
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public List<EntradaHistorial> historialEspecialidad(String codTarjeta, int especialidad)
			throws TarjetaIncorrecta, EspecialidadIncorrecta {
		if(!existeTarjeta(codTarjeta)) {
			throw new TarjetaIncorrecta();
		}
		if(especialidad >= especialidades.length || especialidad < 0) {
			throw new EspecialidadIncorrecta();
		}
		Paciente paciente = busca(codTarjeta);
		paciente.historial();
		List<EntradaHistorial> historial = new ArrayList<EntradaHistorial>();
		Iterator<EntradaHistorial> iterador = paciente.historial().iterator();
		while(iterador.hasNext()) {
			EntradaHistorial visita = (EntradaHistorial) iterador.next();
			if(visita.especialidad() == especialidad) {
				historial.add(visita);
			}
		}
		return historial;
	}

	/**
	 * Indica si existe un paciente registrado con el codigo de tarjeta
	 * indicado. 
	 * @param codTarjeta codigo de tarjeta buscado
	 * @return verdadero si existe un paciente registrado con el codigo de tarjeta
	 * indicado. 
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public boolean existeTarjeta(String codTarjeta) {
		if(busca(codTarjeta) != null) {
			return true;
		}
		return false;
	}
	
	/*
	 * Complejidad temporal: O(n).
	 */
	private Paciente busca(String codTarjeta) {
		Iterator iterador = registroDatos.iterator();
		while(iterador.hasNext()) {
			Paciente paciente = (Paciente) iterador.next();
			if(paciente.codTarjeta().equals(codTarjeta)) {
				return paciente;
			}
		}
		return null;
	}
}
