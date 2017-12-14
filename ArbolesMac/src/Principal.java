import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		ArbolBin<String> arbol = new ArbolBin<String>("Quini");
		ArbolBin.Iterador<String> iterador = arbol.iterador();
		System.out.println(iterador.actual());
		iterador.anhadeIzquierdo("Alejandro");
		iterador.irAHijoIzquierdo();
		iterador.anhadeIzquierdo("María");
		iterador.anhadeDerecho("Lucía");
		iterador.irAPadre();
		iterador.anhadeDerecho("Gabriela");
		iterador.irARaiz();
		inOrden(iterador);
		iterador.irARaiz();
		posOrden(iterador);
		iterador.irARaiz();
		List<String> l = new LinkedList<String>();
		aLista(iterador, l);
		Iterator<String> iterator = l.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(busca(iterador, "Alejandro"));
	}
	
	public static void preOrden(ArbolBin.Iterador<String> iterador) {
		System.out.println(iterador.actual());
		if(iterador.hayIzquierdo()) {
			iterador.irAHijoIzquierdo();
			preOrden(iterador);
			iterador.irAPadre();
		}
		if(iterador.hayDerecho()) {
			iterador.irAHijoDerecho();
			preOrden(iterador);
			iterador.irAPadre();
		}
	}
	public static void inOrden(ArbolBin.Iterador<String> iterador) {
		if(iterador.hayIzquierdo()) {
			iterador.irAHijoIzquierdo();
			inOrden(iterador);
			iterador.irAPadre();
		}
		System.out.println(iterador.actual());
		if(iterador.hayDerecho()) {
			iterador.irAHijoDerecho();
			inOrden(iterador);
			iterador.irAPadre();
		}
	}
	
	public static void posOrden(ArbolBin.Iterador<String> iterador) {
		if(iterador.hayIzquierdo()) {
			iterador.irAHijoIzquierdo();
			posOrden(iterador);
			iterador.irAPadre();
		}
		if(iterador.hayDerecho()) {
			iterador.irAHijoDerecho();
			posOrden(iterador);
			iterador.irAPadre();
		}
		System.out.println(iterador.actual());
	}
	
	public static void aLista(ArbolBin.Iterador<String> iterador, List<String> l) {
		l.add(iterador.actual());
		if(iterador.hayIzquierdo()) {
			iterador.irAHijoIzquierdo();
			aLista(iterador, l);
			iterador.irAPadre();
		}
		if(iterador.hayDerecho()) {
			iterador.irAHijoDerecho();
			aLista(iterador, l);
			iterador.irAPadre();
		}
	}
	
	public static boolean busca(ArbolBin.Iterador<String> iterador, String b) {
		if(iterador.actual().equals(b)) {
			return true;
		}
		if(iterador.hayIzquierdo()) {
			iterador.irAHijoIzquierdo();
			if(busca(iterador, b) == true) {
				return true;
			}
			iterador.irAPadre();
		}
		if(iterador.hayDerecho()) {
			iterador.irAHijoDerecho();
			if(busca(iterador, b) == true) {
				return true;
			}
			iterador.irAPadre();
		}
		return false;
	}
}
