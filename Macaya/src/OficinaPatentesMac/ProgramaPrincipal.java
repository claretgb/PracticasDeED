package OficinaPatentesMac;

import java.util.Iterator;
import java.util.List;

public class ProgramaPrincipal {

	public static void main(String []args) {
		OficinaPatentes oficina = new OficinaPatentes();
		Peticion je = new Peticion("Je");
		oficina.nuevaPeticion(je, 6);
		Peticion yo = new Peticion("Yo");
		oficina.nuevaPeticion(yo, 3);
		Peticion molo = new Peticion("Molo");
		oficina.nuevaPeticion(molo, 5);
		oficina.transvase(6, 3, 2);
		List<Peticion> lista = oficina.atenderTodas();
		Iterator<Peticion> iterador = lista.iterator();
		while(iterador.hasNext()) {
			Peticion peticion = iterador.next();
			System.out.println(peticion.descripcion());
		}
	}
}
