
public interface IPila<E> {
	public void apilar(E e);
	public E desapilar();
	public boolean estaVacia();
	public int tamanho();
	public E tope();
}
