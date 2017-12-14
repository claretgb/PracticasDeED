package pract10.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Gestion de una empresa de alquiler de vehiculos
 * 
 * @author  <Clara Torre García-Barredo> y Estructuras de Datos (UC)
 * @version nov-2017
 */
public class EmpresaAlquiler {
	private Map<String,Oficina> mapaOficinas;
	private Map<String,Vehiculo> mapaVehiculos;
	
	@SuppressWarnings("serial")
	public static class OficinaYaExistente extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class VehiculoYaExistente extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class NombreOficinaIncorrecto extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class MatriculaVehiculoIncorrecta extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class VehiculoNoAlquilado extends RuntimeException {}
	
	/**
	 * Construye la empresa de alquiler. La empresa comienza sin
	 * vehiculos ni oficinas.
	 */
	public EmpresaAlquiler(int numVehiculos) {
		mapaOficinas = new TreeMap<String,Oficina>();
		mapaVehiculos = new HashMap<String,Vehiculo>(numVehiculos);
	}
	
	/**
	 * Anhade la oficina a la empresa.
	 * @param oficina oficina a anhadir.
	 * @throws OficinaYaExistente si ya existe una oficina con 
	 * el mismo nombre.
	 */
	/*
	 * Complejidad temporal: O(log(n)).
	 */
	public void anhadeOficina(Oficina oficina) throws OficinaYaExistente {
		if(mapaOficinas.containsKey(oficina.nombre())) {
			throw new OficinaYaExistente();
		}
		mapaOficinas.put(oficina.nombre(), oficina);
	}


	/**
	 * Anhade el vehiculo a la empresa y le pone como disponible en la
	 * oficina indicada.
	 * @param vehiculo vehiculo a anhadir.
	 * @param nombreOficina nombre de la oficina en la que el vehiculo esta disponible.
	 * @throws VehiculoYaExistente si ya existe otro vehiculo con la misma
	 * matricula.
	 * @throws NombreOficinaIncorrecto si no existe ninguna oficina con el
	 * nombre indicado.
	 */
	/*
	 * Complejidad temporal: O(log(n)).
	 */
	public void anhadeVehiculo(Vehiculo vehiculo,
			String nombreOficina) throws VehiculoYaExistente, NombreOficinaIncorrecto {
		if(!mapaOficinas.containsKey(nombreOficina)) {
			throw new NombreOficinaIncorrecto();
		}
		if(mapaVehiculos.containsKey(vehiculo.matricula())) {
			throw new VehiculoYaExistente();
		}
		mapaVehiculos.put(vehiculo.matricula(), vehiculo);
		Oficina oficina = mapaOficinas.get(nombreOficina);
		oficina.anhadeVehiculo(vehiculo);
	}
	
	/**
	 * Alquila en la oficina indicada un vehiculo con las caracteristicas que se
	 * pasan como parametros
	 * @param nombreOficina nombre de la oficina en la que se desea alquilar el vehiculo
	 * @param numPlazas numero de plazas del vehiculo deseado
	 * @param tipoCombustible tipo de combustible del vehiculo deseado
	 * @return el vehiculo que lleva mas tiempo disponible en la oficina con las
	 * caracteristicas indicadas o null si no existe ningun vehiculo con esas
	 * caracterisiticas
	 * @throws NombreOficinaIncorrecto si el nombre de la oficina no corresponde
	 * con ninguna de las oficinas existentes en la empresa.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public Vehiculo alquilaVehiculo(String nombreOficina,
			int numPlazas,
			Vehiculo.TipoCombustible tipoCombustible) throws NombreOficinaIncorrecto
	{
		if(mapaOficinas.get(nombreOficina) == null) {
			throw new NombreOficinaIncorrecto();
		}
		Oficina oficina = mapaOficinas.get(nombreOficina);
		return oficina.alquilaVehiculo(numPlazas,tipoCombustible);
	}
	
	/**
	 * Devuelve el vehiculo a la oficina indicada.
	 * @param nombreOficina nombre de la oficina a la que se devuelve el vehiculo
	 * @param matricula matricula del vehiculo que se desea devolver
	 * @throws NombreOficinaIncorrecto si el nombre de la oficina no corresponde
	 * con ninguna de las oficinas existentes en la empresa.
	 * @throws MatriculaVehiculoIncorrecta si la matricula no corresponde con
	 * ninguna de las matriculas de los vehiculos de la empresa.
	 * @throws VehiculoNoAlquilado si el vehiculo no se encuentra alquilado en
	 * este momento.
	 */
	/*
	 * Complejidad temporal: O(log(n)).
	 */
	public void devuelveVehiculo(String nombreOficina,
			String matricula) throws NombreOficinaIncorrecto, MatriculaVehiculoIncorrecta,
			VehiculoNoAlquilado
	{
		if(mapaOficinas.get(nombreOficina) == null) {
			throw new NombreOficinaIncorrecto();
		}
		if(buscaVehiculo(matricula) == null) {
			throw new MatriculaVehiculoIncorrecta();
		}
		if(buscaVehiculo(matricula).oficina() != null) {
			throw new VehiculoNoAlquilado();
		}
		Oficina oficina = mapaOficinas.get(nombreOficina);
		oficina.devuelveVehiculo(buscaVehiculo(matricula));
	}
	
	/**
	 * Busca el vehiculo con la matricula indicada entre todos los vehiculos
	 * que tiene la empresa (alquilados o no)
	 * @param matricula matricula del vehiculo buscado
	 * @return el vehiculo buscado o null si no hay ningun vehiculo con esa
	 * matricula
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public Vehiculo buscaVehiculo(String matricula) {
		return mapaVehiculos.get(matricula);
	}
	
	/**
	 * Retorna todas las oficinas de la empresa
	 * @return todas las oficinas de la empresa
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	public Collection<Oficina> oficinas() {
		Set<String> conjunto = mapaOficinas.keySet();
		List<Oficina> lista = new LinkedList<Oficina>();
		for(String nombreOficina : conjunto) {
			lista.add(mapaOficinas.get(nombreOficina));
		}
		return lista;
	}
}
