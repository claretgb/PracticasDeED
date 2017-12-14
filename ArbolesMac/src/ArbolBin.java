

public class ArbolBin<E> {

	private static class Nodo<E> {
		private E dato;
		private Nodo<E> padre, hijoIzquierdo, hijoDerecho;
		
		public Nodo(E dato) {
			this.dato = dato;
			padre = null;
			hijoIzquierdo = null;
			hijoDerecho = null;
		}
	}
	
	private Nodo<E> raiz;
	private int numeroNodos;
	
	public ArbolBin(E dato) {
		raiz = new Nodo<E>(dato);
		numeroNodos = 1;
	}
	
	public static class Iterador<E> {
		private Nodo<E> actual;
		private ArbolBin<E> arbol;
		
		public Iterador(ArbolBin<E> a) {
			arbol = a;
			actual = arbol.raiz;
		}
		
		public E actual() {
			return actual.dato;
		}
		
		public boolean hayIzquierdo() {
			if(actual.hijoIzquierdo != null) {
				return true;
			}
			return false;
		}
		
		public boolean hayDerecho() {
			if(actual.hijoDerecho != null) {
				return true;
			}
			return false;
		}
		
		public boolean hayPadre() {
			if(actual.padre != null) {
				return true;
			}
			return false;
		}
		
		public void anhadeIzquierdo(E dato) {
			if(!hayIzquierdo()) {
				Nodo<E> nuevo = new Nodo<E>(dato);
				actual.hijoIzquierdo = nuevo;
				nuevo.padre = actual;
				arbol.numeroNodos++;
			}
		}
		
		public void anhadeDerecho(E dato) {
			if(!hayDerecho()) {
				Nodo<E> nuevo = new Nodo<E>(dato);
				actual.hijoDerecho = nuevo;
				nuevo.padre = actual;
				arbol.numeroNodos++;
			}
		}
		
		public void irAPadre() {
			actual = actual.padre;
		}
		
		public void irAHijoIzquierdo() {
			actual = actual.hijoIzquierdo;
		}
		
		public void irAHijoDerecho() {
			actual = actual.hijoDerecho;
		}
		
		public void irARaiz() {
			actual = arbol.raiz;
		}
		
	}
	
	public Iterador<E> iterador() {
		return new Iterador<E>(this);
	}
	
}
