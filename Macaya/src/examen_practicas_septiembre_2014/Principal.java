package examen_practicas_septiembre_2014;

import lista_doble_enlace.IIteradorSimple;

public class Principal {

	public static void main(String[] args) {
		ListaDobleEnlaceCC<String> lista = new ListaDobleEnlaceCC<String>();
		lista.anhade(0, "A");
		lista.anhade(1, "B");
		lista.anhade(2, "C");
		lista.anhade(3, "D");
		lista.anhade(4, "E");
		lista.anhade(5, "F");
		lista.anhade(6, "G");
		lista.anhade(7, "H");
		System.out.println(lista.mueveAlComienzo("D"));
		IIteradorSimple<String> iterador = lista.iterador();
		while(iterador.haySiguiente()) {
			System.out.println(iterador.siguiente());
		}
	}

}
