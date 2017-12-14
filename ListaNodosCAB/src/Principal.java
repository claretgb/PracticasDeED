import java.util.*;

public class Principal {
	
	public static void main3(String[] args) {
		List<Double> lista = new LinkedList<Double>();
		lista.add(3.0);
		lista.add(5.5);
		lista.add(1.0);
		Iterator<Double> iterador = lista.iterator();
		while(iterador.hasNext()) {
			System.out.println(iterador.next());
		}
		lista.add(-0.7);
		lista.add(5.7);
		lista.add(-1.6);
		Iterator<Double> iterador2 = lista.iterator();
		while(iterador2.hasNext()) {
			if(iterador2.next() < 0) {
				iterador2.remove();
			}
		}
		System.out.println(lista);
	}

	public static void main4(String[] args) {
		ListaNodosCab <Integer> lista = new ListaNodosCab <Integer>();
		lista.anhadeFinal(1);
		lista.anhadeFinal(2);
		lista.anhadeFinal(3);
		lista.anhadeFinal(4);
		lista.anhadeFinal(5);
		System.out.println(lista.muestra());
		//No se utiliza el "new" para crear un iterador, se hace así:
		IIterador<Integer> iterador = lista.iterador();
		//Recorrer lista con iterador:
		while(iterador.tieneSiguiente()) {
			Integer n = iterador.siguiente();
			System.out.println(n);
		}
		//Esto no se hace, hay que hacer lo de arriba. Porque es O(n^2) y lo de arriba O(n).
	//	for(int i = 0; i < lista.tamanho(); i++) {
	//		System.out.println(lista.obten(i));
	//	}
		IIterador<Integer> iterador2 = lista.iterador();
		iterador2.siguiente();
		iterador2.siguiente();
		iterador2.elimina();
		System.out.println(lista.muestra());
		iterador2.siguiente();
		iterador2.siguiente();
		iterador2.inserta(2);
		iterador2.siguiente();
		iterador2.elimina();
		iterador2.inserta(7);
		System.out.println(lista.muestra());
	}
	
	public static void main2(String[] args) {
		ListaNodosCab <String> lista = new ListaNodosCab <String> ();
		lista.anhadeInicio("Pepe");
		lista.anhadeInicio("Juan");
		lista.anhadeInicio("Ana");
		lista.anhadeInicio("Ana");
		lista.anhadeFinal("Marta");
		lista.anhade("Lucía", 2);
		System.out.println(lista.muestra());
		System.out.println(lista.muestraReves());
		System.out.println(lista.busca("Pepe"));
		System.out.println(lista.tamanho());
		System.out.println("Número de repeticiones: "+lista.repeticion("Ana"));
		lista.anhadeDetras("Ana", "Juana");
		System.out.println(lista.muestra());
		System.out.println(lista.cuantosEntreDos("Ana", "Pepe"));
		ListaNodosCab<String> sublista = lista.listaEntreDos("Ana", "Pepe");
		System.out.println(sublista.muestra());
		ListaNodosCab<String> listaAlReves = lista.alReves();
		System.out.println(listaAlReves.muestra());
		lista.elimina(6);
		System.out.println(lista.muestra());
	}
	
	/*
	 * Este es un ejercicio del día 24 de Octubre de 2017.
	 */
	public static void main5(String[] args) {
		List<Integer> lista = new LinkedList<Integer>();
		lista.add(3);
		lista.add(0);
		lista.add(2);
		lista.add(9);
		lista.add(9);
		lista.add(5);
		lista.add(1);
		lista.add(3);
		lista.add(3);
		lista.add(0);
		Iterator<Integer> iterador = lista.iterator();
		while(iterador.hasNext()) {
			int dato = iterador.next();
			int veces = 0;
			Iterator<Integer> iterador2 = lista.iterator();
			while(iterador2.hasNext()) {
				if(iterador2.next() == dato) {
					veces++;
				}
			}
			System.out.println(dato + " se repite " + veces + " veces.");
		}
	}
	
	public static ILista<String> mezcla(ILista<String> l1, ILista<String> l2){
		ILista<String> l3 = new ListaNodosCab<String>();
		IIterador<String> iterador = l1.iterador();
		IIterador<String> iterador2 = l2.iterador();
		while(iterador.tieneSiguiente() || iterador2.tieneSiguiente()) {
				if(iterador.tieneSiguiente()) {
					l3.anhadeFinal(iterador.siguiente());
				}
				if(iterador2.tieneSiguiente()) {
					l3.anhadeFinal(iterador2.siguiente());
				}
			}
		
		return l3;
	}
	
	public static void main6(String[] args) {
		ILista<String> l1 = new ListaNodosCab<String>();
		ILista<String> l2 = new ListaNodosCab<String>();
		ListaNodosCab<String> l3;
		l1.anhadeFinal("A");
		l1.anhadeFinal("B");
		l1.anhadeFinal("C");
		l1.anhadeFinal("D");
		l1.anhadeFinal("E");
		l2.anhadeFinal("1");
		l2.anhadeFinal("2");
		l2.anhadeFinal("3");
		l3=(ListaNodosCab<String>) mezcla(l1,l2);
		System.out.println(l3.muestra());;
	}
	
	public static void main(String[] args) {
		ILista<String> l1 = new ListaNodosCab<String>();
		l1.anhadeFinal("A");
		l1.anhadeFinal("B");
		l1.anhadeFinal("C");
		l1.anhadeFinal("D");
		ILista<String> l2 = new ListaNodosCab<String>();
		l2.anhadeFinal("C");
		l2.anhadeFinal("D");
		l2.anhadeFinal("E");
		ILista<String> l3 = filtro(l1, l2);
		System.out.println(((ListaNodosCab<String>) l3).muestra());
		System.out.println(l1.acota("D"));
	}
	
	//Complejidad temporal: O(n*m).
	public static ILista filtro(ILista l1, ILista l2) {
		ILista<String> l3 = new ListaNodosCab<String>();
		IIterador<String> iterador = l1.iterador();
		while(iterador.tieneSiguiente()) {
			String aux = iterador.siguiente();
			if(l2.busca(aux) == -1) {
				l3.anhadeFinal(aux);
			}
		}
		return l3;
	}

	
}
