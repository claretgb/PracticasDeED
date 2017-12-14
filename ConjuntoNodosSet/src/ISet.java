
public interface ISet <E> {
	public void anhadir(E e);
	public boolean contiene(E e);
	public void elimina(E e);
	public ISet <E> conjuntoInterseccion(ISet <E> conjunto2);
	public ISet <E> conjuntoUnion(ISet <E> conjunto2);
	public ISet <E> diferencia(ISet <E> conjunto2);
}
