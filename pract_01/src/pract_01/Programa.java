package pract_01;

public class Programa {
	public static void main(String[] args) {
		ListaArraySimple<String> listaString = new ListaArraySimple<String>(7);
		ListaArraySimple<Integer> listaInteger = new ListaArraySimple<Integer>(5);
		
		listaString.anhade(0, (String) "A");
		listaString.anhade(1, (String) "B");
		listaString.anhade(2, (String) "C");
		listaString.anhade(3, (String) "D");
		listaString.anhade(4, (String) "E");
		listaString.anhade(5, (String) "F");
		listaString.anhade(6, (String) "G");
		
		listaInteger.anhade(0, 1);
		listaInteger.anhade(1, 2);
		listaInteger.anhade(2, 3);
		listaInteger.anhade(3, 4);
		listaInteger.anhade(4, 5);
		
		for(int i = 0; i<listaString.tamanho(); i++) {
			System.out.println(listaString.obtenElemento(i));
		}
		
		for(int i = 0; i<listaInteger.tamanho(); i++) {
			System.out.println(listaInteger.obtenElemento(i));
		}
	}
}