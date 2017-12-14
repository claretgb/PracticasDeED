
public interface ILista <E> {
	public void anhade(E dato, int posicion);
	public void anhadeInicio(E dato);
	public void anhadeFinal(E dato); 
	public void elimina(int posicion);
	public void elimina(E dato);
	public int busca(E dato);
	public int tamanho();
	public int repeticion(E dato);
	public void anhadeDetras(E dato, E nuevo);
	public E obten(int pos);
	public int cuantosEntreDos(E dato1, E dato2);
	public ILista<E> listaEntreDos(E dato1, E dato2);
	public ILista<E> alReves();
	public IIterador<E> iterador();
	public int acota(E e);
}
