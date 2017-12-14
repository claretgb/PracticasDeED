package Septiembre_2014;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Biblioteca {

	private Map<Integer,Socio> mapaSocios;
	private Map<String,Libro> mapaLibros;
	
	@SuppressWarnings("serial")
	public class NumeroDeSocioIncorrecto extends RuntimeException{};
	
	@SuppressWarnings("serial")
	public class CodigoDeLibroIncorrecto extends RuntimeException{};
	
	@SuppressWarnings("serial")
	public class LibroYaPrestadoASocio extends RuntimeException{};
	
	@SuppressWarnings("serial")
	public class LibroNoPrestado extends RuntimeException{};
	
	@SuppressWarnings("serial")
	public class LibroPrestadoAOtroSocio extends RuntimeException{};
	
	public Biblioteca() {
		mapaSocios = new HashMap<Integer,Socio>();
		mapaLibros = new HashMap<String,Libro>();
	}
	
	public void prestalibro(int numeroSocio, String codigoLibro) 
			throws NumeroDeSocioIncorrecto, CodigoDeLibroIncorrecto, LibroYaPrestadoASocio {
		if(!mapaSocios.containsKey(numeroSocio)) {
			throw new NumeroDeSocioIncorrecto();
		}
		if(!mapaLibros.containsKey(codigoLibro)) {
			throw new CodigoDeLibroIncorrecto();
		}
		if(mapaLibros.get(codigoLibro).prestado()) {
			if(mapaLibros.get(codigoLibro).loTiene().equals(mapaSocios.get(numeroSocio))) {
				throw new LibroYaPrestadoASocio();
			} else {
				mapaLibros.get(codigoLibro).anhadeSocioALaEspera(mapaSocios.get(numeroSocio));
			}
		} else {
			mapaLibros.get(codigoLibro).setPrestado(true);
			mapaLibros.get(codigoLibro).setLoTiene(mapaSocios.get(numeroSocio));
			mapaSocios.get(numeroSocio).anhadeLibroPrestado(mapaLibros.get(codigoLibro));
		}
	}
	
	public void devuelveLibro(String codigoLibro, int numeroSocio) 
			throws NumeroDeSocioIncorrecto, CodigoDeLibroIncorrecto, LibroNoPrestado, LibroPrestadoAOtroSocio {
		if(!mapaSocios.containsKey(numeroSocio)) {
			throw new NumeroDeSocioIncorrecto();
		}
		if(!mapaLibros.containsKey(codigoLibro)) {
			throw new CodigoDeLibroIncorrecto();
		}
		if(!mapaLibros.get(codigoLibro).prestado()) {
			throw new LibroNoPrestado();
		}
		if(!mapaLibros.get(codigoLibro).loTiene().equals(mapaSocios.get(numeroSocio))) {
			throw new LibroPrestadoAOtroSocio();
		} else {
			mapaLibros.get(codigoLibro).setLoTiene(null);
			mapaSocios.get(numeroSocio).devuelveLibroPrestado(mapaLibros.get(codigoLibro));	
			if(mapaLibros.get(codigoLibro).getSociosALaEspera().size() == 0) {
				mapaLibros.get(codigoLibro).setPrestado(false);
			} else{
				Queue<Socio> socios = mapaLibros.get(codigoLibro).getSociosALaEspera();
				socios.poll().anhadeLibroPrestado(mapaLibros.get(codigoLibro));
			}
		}
	
	}
	
	public List<Libro> librosPrestadosASocio(int numeroSocio) 
			throws NumeroDeSocioIncorrecto {
		if(!mapaSocios.containsKey(numeroSocio)) {
			throw new NumeroDeSocioIncorrecto();
		}
		return mapaSocios.get(numeroSocio).librosPrestados();
	}
	
	public Collection<Libro> muestraLibros() {
		return mapaLibros.values();
	}
	
	public void anhadeLibro(Libro libro) {
		mapaLibros.put(libro.codigo(), libro);
	}
	
	public void anhadeSocio(Socio socio) {
		mapaSocios.put(socio.numeroSocio(), socio);
	}
}

