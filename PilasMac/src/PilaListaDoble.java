import java.util.*;

public class PilaListaDoble<E> implements IPila<E> {
	private List <E> lista;

	public PilaListaDoble() {
		lista = new LinkedList<E>();
	}

	@Override
	public void apilar(E e) {
		lista.add(0, e);
	}

	@Override
	public E desapilar() 
			throws NoSuchElementException {
		if(lista.isEmpty()) {
			throw new NoSuchElementException();
		}
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

	@Override
	public E tope() 
			throws NoSuchElementException {
		if(lista.isEmpty()) {
			throw new NoSuchElementException();
		}
		E dato = lista.get(0);
		return dato;
	}

}
