package Septiembre_2014;

import java.util.Collection;

public class Principal {

	public static void main(String[] args) {
		Biblioteca biblioteca = new Biblioteca();
		Libro libro1 = new Libro("001Inf","Alicia en el País de las Maravillas");
		biblioteca.anhadeLibro(libro1);
		Libro libro2 = new Libro("002Inf","La pizzería del Oso Barney");
		biblioteca.anhadeLibro(libro2);
		Libro libro3 = new Libro("003Inf","El Principito");
		biblioteca.anhadeLibro(libro3);
		Libro libro4 = new Libro("001Mis","Los hombres que no amaban a las mujeres");
		biblioteca.anhadeLibro(libro4);
		Libro libro5 = new Libro("002Mis","Muerte en el Nilo");
		biblioteca.anhadeLibro(libro5);
		Libro libro6 = new Libro("003Mis","El manuscrito de piedra");
		biblioteca.anhadeLibro(libro6);
		Libro libro7 = new Libro("004Mis","Amanecer rojo");
		biblioteca.anhadeLibro(libro7);
		Socio socio1 = new Socio("Juan");
		biblioteca.anhadeSocio(socio1);
		Socio socio2 = new Socio("Pedro");
		biblioteca.anhadeSocio(socio2);		
		Socio socio3 = new Socio("Marta");
		biblioteca.anhadeSocio(socio3);
		biblioteca.prestalibro(socio1.numeroSocio(), libro1.codigo());
		biblioteca.prestalibro(socio1.numeroSocio(), libro2.codigo());
		biblioteca.prestalibro(socio2.numeroSocio(), libro4.codigo());
		biblioteca.prestalibro(socio3.numeroSocio(), libro7.codigo());
		System.out.println(biblioteca.librosPrestadosASocio(socio1.numeroSocio()));
		biblioteca.devuelveLibro(libro1.codigo(), socio1.numeroSocio());
		System.out.println(biblioteca.librosPrestadosASocio(socio1.numeroSocio()));
		Collection<Libro> libros = biblioteca.muestraLibros();
		for(Libro libro : libros) {
			System.out.println(libro);
		}
	}

}
