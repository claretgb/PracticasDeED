
public interface IIterador <E> {
	public E siguiente();
	public boolean tieneSiguiente();
	public void elimina();
	public void inserta(E dato);
}
