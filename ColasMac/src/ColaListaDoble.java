import java.util.LinkedList;
import java.util.List;

public class ColaListaDoble<E> implements ICola<E> {

	private List <E> lista;

	public ColaListaDoble() {
		lista = new LinkedList<E>();
	}
	
	@Override
	public void encola(E e) {
		lista.add(e);
	}

	@Override
	public E desencola() {
		E dato = lista.remove(0);
		return dato;
	}

	@Override
	public boolean estaVacia() {
		return lista.isEmpty();
	}

	@Override
	public int tamanho() {
		return lista.size();
	}
	
	
}
