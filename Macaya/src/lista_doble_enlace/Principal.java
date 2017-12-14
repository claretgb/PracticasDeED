package lista_doble_enlace;

public class Principal {

	public static void main1(String[] args) {
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
	
	public static void main2(String[] args) {
		ListaDobleEnlace<String> prueba = new ListaDobleEnlace<String>();
		prueba.anhade(0, "h");
		prueba.anhade(1, "o");
		prueba.anhade(2, "l");
		prueba.anhade(3, "a");
		prueba.anhade(4, "h");
		IIteradorSimple<String> iterador = prueba.iterador();
			System.out.println(iterador.siguientes(3));
	}
	
	public static void main3(String[] args) {
		ListaDobleEnlace<String> prueba = new ListaDobleEnlace<String>();
		prueba.anhade(0, "h");
		prueba.anhade(1, "o");
		prueba.anhade(2, "l");
		prueba.anhade(3, "a");
		prueba.anhade(4, "h");
		prueba.eliminaVarios("h", 3);
		IIteradorSimple<String> iterador = prueba.iterador();
		while(iterador.haySiguiente()) {
			System.out.println(iterador.siguiente());
		}
	}
	
	public static void main(String[] args) {
		ListaDobleEnlace<String> prueba = new ListaDobleEnlace<String>();
		prueba.anhade(0, "h");
		prueba.anhade(1, "o");
		prueba.anhade(2, "l");
		prueba.anhade(3, "a");
		prueba.anhade(4, "h");
		ILista<String> lista = prueba.extrae(2, 4);
		IIteradorSimple<String> iterador = lista.iterador();
		while(iterador.haySiguiente()) {
			System.out.println(iterador.siguiente());
		}
	}

}
