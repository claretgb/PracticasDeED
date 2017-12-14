
public class ListaNodosCab <E> implements ILista <E> {
	private static class Celda <E>{
		private E dato;
		private Celda <E> ant, sig;
		public Celda(E dato) {
			this.dato = dato;
			ant = null;
			sig = null;
		}
	}

	private Celda <E> inicio, fin;
	private int numeroElementos;
	
	public ListaNodosCab(){
		Celda <E> nueva = new Celda <E> (null);
		inicio = nueva;
		fin = nueva;
		numeroElementos = 0;
	}
	
	@Override
	public void anhade(E dato, int posicion)
		throws IndexOutOfBoundsException {
		if(posicion < 0 || posicion >= numeroElementos) {
			throw new IndexOutOfBoundsException();
		}
		if(posicion == 0) {
			anhadeInicio(dato);
		} else if(posicion == numeroElementos) {
			anhadeFinal(dato);
		} else {
			Celda<E> nueva = new Celda(dato);
			Celda<E> aux = inicio.sig;
			for(int i = 0; i < posicion; i++) {
				aux = aux.sig;
			}
			aux.ant.sig = nueva;
			nueva.ant = aux.ant;
			aux.ant = nueva;
			nueva.sig = aux;
			numeroElementos++;
		}
		
	}

	@Override
	public void anhadeInicio(E dato) {
		Celda <E> nueva = new Celda<E> (dato);
		if (numeroElementos > 0) {
			inicio.sig.ant = nueva;
			nueva.sig = inicio.sig;
		}
		inicio.sig = nueva;
		nueva.ant = inicio;
		if (numeroElementos == 0) {
			fin = nueva;
		}
		numeroElementos++;
		
	}
	
	@Override
	public E obten(int posicion) {
		if(posicion < 0 || posicion >= numeroElementos) {
			throw new IndexOutOfBoundsException();
		}
		Celda<E> aux = inicio.sig;
		for(int i = 0; i < posicion; i++) {
			aux = aux.sig;
		}
		return aux.dato;
	}
	
	@Override
	public int cuantosEntreDos(E dato1, E dato2) {
		Celda <E> aux = inicio.sig;
		while(aux != null && !aux.dato.equals(dato1)) {
			aux = aux.sig;
		}
		if(aux == null) {
			return 0;
		}
		int contador = 0; 
		aux = aux.sig;
		while(aux != null && !aux.dato.equals(dato2)) {
			contador++;
			aux = aux.sig;
		}
		return contador;
	}
	
	public ListaNodosCab<E> listaEntreDos(E dato1, E dato2) {
		ListaNodosCab<E> listaResultado = new ListaNodosCab<E>();
		Celda<E> aux = inicio.sig;
		while(aux != null && !aux.dato.equals(dato1)) {
			aux = aux.sig;
		}
		if(aux == null) {
			return null;
		}
		aux = aux.sig;
		while(aux != null && !aux.dato.equals(dato2)) {
			listaResultado.anhadeFinal(aux.dato);
			aux = aux.sig;
		}
		return listaResultado;
	}
	
	public ListaNodosCab<E> alReves() {
		ListaNodosCab<E> listaResultado = new ListaNodosCab<E>();
		Celda<E> aux = fin;
		while(aux != inicio) {
			listaResultado.anhadeInicio(aux.dato);
			aux = aux.ant;
		}
		return listaResultado;
	}

	@Override
	public void anhadeFinal(E dato) {
		Celda <E> nueva = new Celda<E> (dato);
		fin.sig = nueva;
		nueva.ant = fin;
		fin = nueva;
		numeroElementos++;
	}

	@Override
	public void elimina(int posicion) 
		throws IndexOutOfBoundsException {
		if(posicion < 0 || posicion >= numeroElementos) {
			throw new IndexOutOfBoundsException();
		}
		if(posicion < (numeroElementos-1)) {
			Celda<E> aux = inicio.sig;
			for(int i = 0; i < posicion; i++) {
				aux = aux.sig;
			}
			aux.sig.ant = aux.ant;
			aux.ant.sig = aux.sig;
			aux.ant = null;
			aux.sig = null;
			numeroElementos--;
		} else {
			Celda<E> anteult = fin.ant;
			anteult.sig = null;
			fin.ant = null;
			fin = anteult;
			numeroElementos++;
		}
	}

	@Override
	public void elimina(E dato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int busca(E dato) {
		int contador = 0;
		Celda <E> aux = inicio.sig;
		for (int i = 0; i<numeroElementos; i++) {
			if(aux.dato.equals(dato)) {
				return contador;
			}
			aux = aux.sig;
			contador++;
		}
		return -1;
	}
	
	@Override
	public int repeticion(E dato) {
		int i = 0;
		Celda<E> aux = inicio.sig;
		while(aux != null) {
			if(aux.dato.equals(dato)) {
				i++;
			}
				aux = aux.sig;
		}
		return i;
	}
	
	@Override
	public void anhadeDetras(E dato, E nuevo) {
		Celda<E> aux = inicio.sig;
		while(aux.sig != null) {
			if(aux.dato.equals(dato)) {
				Celda<E> nueva = new Celda<E>(nuevo);
				Celda<E> aux2 = aux.sig;
				nueva.ant = aux;
				nueva.sig = aux2;
				aux.sig = nueva;
				aux2.ant = nueva;
				numeroElementos++;
				aux = aux2;
			} else {
				aux = aux.sig;
			}
		}
		if(aux.dato.equals(dato)) {
			Celda<E> nuevaCelda = new Celda<E>(nuevo);
			nuevaCelda.ant = aux;
			aux.sig = nuevaCelda;
			fin = nuevaCelda;
			numeroElementos++;
		}
	}

	@Override
	public int tamanho() {
		return numeroElementos;
	}

	public String muestra() {
		String t = "";
		Celda <E> aux = inicio.sig; //aux siempre es un puntero auxiliar
		while(aux != null) {
			t = t + aux.dato + "->";
			aux = aux.sig;
		}
		t = t + "(" + numeroElementos + ")";
		return t;
	}
	
	public String muestraReves() {
		String t = "";
		Celda <E> aux = fin;
		while(aux != inicio) {
			t = t + aux.dato + "->";
			aux = aux.ant;
		}
		t = t + "(" + numeroElementos + ")";
		return t;
	}
	
	public ILista<E> mezcla(ILista<E> l2) {
		ILista<E> l3 = new ListaNodosCab<E>();
		Celda<E> c1 = inicio.sig;
		Celda<E> c2 = ((ListaNodosCab<E>) l2).inicio.sig;
		while(c1.sig != null || c2.sig != null) {
			if(c1.sig != null) {
				l3.anhadeFinal(c1.dato);
			}
			if(c2.sig != null) {
				l3.anhadeFinal(c2.dato);
			}
		}
		return l3;
	}

	//Aquí empieza la implementación del iterador
	
	private class Iterador<E> implements IIterador<E> {
		private Celda<E> puntero;
		private ListaNodosCab<E> lista;
		
		public Iterador(ListaNodosCab<E> lista) {
			this.lista = lista;
			puntero = lista.inicio;
		}
		
		@Override
		public E siguiente() { 
			//es el usuario quien comprueba si hay siguiente.
			puntero = puntero.sig;
			return puntero.dato;
		}

		@Override
		public boolean tieneSiguiente() {
			return puntero != lista.fin;
			//return puntero.sig != null; es una alternativa.
		}

		@Override
		public void elimina() {
			if(!tieneSiguiente()) {
				puntero.ant.sig = null;
				puntero = puntero.ant;
				numeroElementos--;
			} else {
				puntero.ant.sig = puntero.sig;
				puntero.sig.ant = puntero.ant;
				puntero = puntero.ant;
				numeroElementos--;
			}
		}

		@Override
		public void inserta(E dato) {
			Celda<E> nueva = new Celda<E>(dato);
			if(!tieneSiguiente()) {
				puntero.sig = nueva;
				nueva.ant = puntero;
				nueva.sig = null;
				siguiente();
				numeroElementos++;
			} else {
				nueva.sig = puntero.sig;
				puntero.sig.ant = nueva;
				puntero.sig = nueva;
				nueva.ant = puntero;
				siguiente();
				numeroElementos++;
			}
		}
	}
	
	
	public int acota(E e) {
		int i = 0;
		Celda<E> aux = inicio.sig;
		Celda<E> aux2 = fin;
		while(aux.sig != null && !aux.dato.equals(e)) {
			aux = aux.sig;
		}
		while(aux2.ant != null && !aux2.dato.equals(e)) {
			aux2 = aux2.ant;
		}
		if(aux != aux2 && aux != null && aux2 != null) {
			while(aux.sig != aux2) {
				i++;
				aux = aux.sig;
			}
		}
		return i;
	}
	
	//Este método es la única manera de acceder al iterador desde el main.
	@Override
	public IIterador<E> iterador() {
		return new Iterador<E>(this);
	}
	
}
