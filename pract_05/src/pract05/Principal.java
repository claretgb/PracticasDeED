package pract05;

import pract05.lista_array.ListaArray;
import pract05.operaciones.OperacionesConListas;

public class Principal {

	public static void main(String[] args) {
		ListaArray<String> prueba = new ListaArray<String>();
		ListaArray<String> sublista;
		prueba.anhade(0, "h");
		prueba.anhade(1, "o");
		prueba.anhade(2, "l");
		prueba.anhade(3, "a");
		int e[] = {1,2};
		sublista = OperacionesConListas.sublistaPosiciones(prueba, e);
		OperacionesConListas.duplicaOcurrenciasElemento(prueba, "a");
		IIteradorSimple<String> iterador = sublista.iterador();
		while(iterador.haySiguiente()) {
			System.out.println(iterador.siguiente().toString());
		}
		IIteradorSimple<String> iterador2 = prueba.iterador();
		while(iterador2.haySiguiente()) {
			System.out.println(iterador2.siguiente().toString());
		}
	}

}
