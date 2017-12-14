
public class SetSimpleEnlace <E> implements ISet <E> {

	private static class Celda <E>{
		private E dato;
		private Celda <E> sig;
		public Celda(E dato) {
			this.dato = dato;
			sig = null;
		}
	}

	private Celda <E> inicio;
	private int numeroElementos;
	
	public SetSimpleEnlace(){
		Celda <E> nueva = new Celda <E> (null);
		inicio = nueva;
		numeroElementos = 0;
	}
	
	@Override
	public void anhadir(E e) {
		Celda <E> aux = new Celda<E>(e);
		if(contiene(e)) {
			return;
		} else{
			aux.sig = inicio.sig;
			inicio.sig = aux;
			numeroElementos++;
		}
	}

	@Override
	public boolean contiene(E e) {
		Celda <E> aux = inicio.sig;
		for(int i = 0; i < numeroElementos; i++) {
			if(aux.dato.equals(e)) {
				return true;
			}
			aux = aux.sig;
		}
		return false;
	}

	@Override
	public void elimina(E e) {
		if(contiene(e)) {
			Celda <E> aux = inicio;
			for(int i = 0; i < numeroElementos; i++) {
				if(aux.sig.dato.equals(e)) {
					aux.sig = aux.sig.sig;
					numeroElementos--;
				} else {
				aux = aux.sig;
				}
			}
		} else{
			return;
		}
	}

	@Override
	public ISet <E> conjuntoInterseccion(ISet <E> conjunto2) {
		ISet <E> interseccion = new SetSimpleEnlace <E>();
		Celda <E> aux = inicio.sig;
		while(aux != null) {
			if(conjunto2.contiene(aux.dato)) {
				interseccion.anhadir(aux.dato);
			}
			aux = aux.sig;
		}
		return interseccion;
	}

	@Override
	public String toString() {
		
		String t="";
		
		Celda<E> aux = inicio.sig;
		while(aux!=null){
			t=t+aux.dato+"->";
			aux=aux.sig;
		}
		return t;
	}
	
	@Override
	public ISet <E> conjuntoUnion(ISet <E> conjunto2) {
		SetSimpleEnlace <E> ss2 = (SetSimpleEnlace<E>) conjunto2;
		ISet <E> union = new SetSimpleEnlace <E>();
		Celda <E> aux = inicio.sig;
		Celda <E> aux2 = ss2.inicio.sig;
		while(aux != null) {
			union.anhadir(aux.dato);
			aux = aux.sig;
		}
		while(aux2 != null) {
			union.anhadir(aux2.dato);
			aux2 = aux2.sig;
		}
		return union;
	}

	@Override
	public ISet<E> diferencia(ISet<E> conjunto2) {
		ISet <E> diferencia = new SetSimpleEnlace <E>();
		Celda <E> aux = inicio.sig;
		while(aux != null) {
			if(!conjunto2.contiene(aux.dato)) {
				diferencia.anhadir(aux.dato);
			}
			aux = aux.sig;
		}
		return diferencia;
	}

}
