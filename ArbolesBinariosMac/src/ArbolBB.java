import java.util.LinkedList;
import java.util.List;

public class ArbolBB<E extends Comparable<E>> {
	
	private static class Nodo<E extends Comparable<E>> {
		private E dato;
		private Nodo<E> hijoIzquierdo, hijoDerecho;
		
		public Nodo(E e) {
			dato = e;
			hijoIzquierdo = null;
			hijoDerecho = null;
		}
	}
	
	//Atributos de la clase ArbolBB.
	private Nodo<E> raiz;
	private int numeroNodos;
	
	public ArbolBB(E e) {
		raiz = new Nodo<E>(e);
		numeroNodos = 1;
	}
	
	public void anhade(E e) {
		int contador = 0;
		Nodo<E> puntero = raiz;
		boolean fin = false;
		while(!fin) {
			int n = puntero.dato.compareTo(e);
			contador++;
			if(n == 0) {
				fin = true;
			} else if(n < 0) {
				if(puntero.hijoIzquierdo != null) {
					puntero = puntero.hijoIzquierdo;
				} else {
					puntero.hijoIzquierdo = new Nodo<E>(e);
					fin = true;
					numeroNodos++;
				}
			} else {
				if(puntero.hijoDerecho != null) {
					puntero = puntero.hijoDerecho;
				} else {
					puntero.hijoDerecho = new Nodo<E>(e);
					fin = true;
					numeroNodos++;
				}
			}
		}
		System.out.println("Insertado tras " + contador + " veces.");
	}

	public String toString() {
		if(raiz == null) {
			return "No hay datos";
		}
		return inOrden(raiz) + " [El número de nodos es " + numeroNodos + "]";
	}
	
	private String inOrden(Nodo<E> puntero) {
		String str = "";
		if(puntero.hijoIzquierdo != null) {
			str = str + inOrden(puntero.hijoIzquierdo);
		}
		str = str+ puntero.dato + "";
		if(puntero.hijoDerecho != null) {
			str = str + inOrden(puntero.hijoDerecho);
		}
		return str;
	}
	
	public boolean estaDentro(E e) {
		int contador = 0;
		Nodo<E> puntero = raiz;
		boolean fin = false;
		while(!fin) {
			int n = puntero.dato.compareTo(e);
			contador++;
			if(n == 0) {
				fin = true;
				System.out.println("Encontrado tras " + contador + " veces.");
				return true;
			} else if(n < 0) {
				if(puntero.hijoIzquierdo != null) {
					puntero = puntero.hijoIzquierdo;
				} else {
					fin = true;
				}
			} else {
				if(puntero.hijoDerecho != null) {
					puntero = puntero.hijoDerecho;
				} else {
					fin = true;
				}
			}
		}
		System.out.println("No encontrado.");
		return false;
	}
	
	public E maximo() {
		Nodo<E> puntero = raiz;
		boolean fin = false;
		while(!fin) {
			if(puntero.hijoIzquierdo != null) {
				puntero = puntero.hijoIzquierdo;
			} else {
				fin = true;
			}
		}
		return puntero.dato;
	}
	
	public E minimo() {
		Nodo<E> puntero = raiz;
		boolean fin = false;
		while(!fin) {
			if(puntero.hijoDerecho != null) {
				puntero = puntero.hijoDerecho;
			} else {
				fin = true;
			}
		}
		return puntero.dato;
	}
	
	public List<E> ordenado() {
		if(raiz == null) {
			return null;
		}
		List<E> lista = new LinkedList<E>();
		inOrdenLista(raiz, lista);
		return lista;
	}
	
	public void inOrdenLista(Nodo<E> puntero, List<E> lista) {
		if(puntero.hijoIzquierdo != null) {
			inOrdenLista(puntero.hijoIzquierdo, lista);
		}
		lista.add(puntero.dato);
		if(puntero.hijoDerecho != null) {
			inOrdenLista(puntero.hijoDerecho, lista);
		}
	}
	
	public E segundoMinimo() {
		List<E> lista = ordenado();
		return lista.get(lista.size()-2);
	}
}