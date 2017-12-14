package pract09;

public class Macaya {
	public List<V> ListaValores() {
		List<V> l = new LinkedList<V>();
		for(int i = 0; i < tabla.length; i++) {
			for(Entrada<K,V> ent : tabla[i]) {
				l.add(ent.V);
			}
		}
		return l;
	}
}
