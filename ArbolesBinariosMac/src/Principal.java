import java.util.Iterator;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		ArbolBB<Integer> arbol = new ArbolBB<Integer>(2);
		arbol.anhade(3);
		arbol.anhade(7);
		arbol.anhade(1);
		arbol.anhade(9);
		arbol.anhade(10);
		System.out.println(arbol.toString());
		System.out.println(arbol.estaDentro(4));
		System.out.println(arbol.estaDentro(3));
		System.out.println(arbol.maximo());
		System.out.println(arbol.minimo());
		System.out.println(arbol.segundoMinimo());
		List<Integer> listaOrdenada = arbol.ordenado();
		Iterator<Integer> iterador = listaOrdenada.iterator();
		while(iterador.hasNext()) {
			System.out.print(iterador.next() + " ");
		}
	}

}
