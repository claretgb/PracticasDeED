
import java.util.*;

/**
 * Clase que crea una estructura de mapa
 * implementando la interfaz IMapa<K,V>.
 * 
 * @author Clara Torre García-Barredo.
 * @version nov-17
 * @param <K>
 * @param <V>
 */
public class MapaDispersionAbierta<K,V> implements IMapa<K,V> {

	/**
	 * Clase que crea una entrada con llave y valor para el mapa. 
	 * 
	 * @author Clara Torre García-Barredo
	 * @version nov-17
	 *
	 * @param <K>
	 * @param <V>
	 */
	@SuppressWarnings("hiding")
	public class Entrada<K, V> {
		//Atributos de la clase Entrada.
		private K k;
		private V v;
		
		/**
		 * Método constructor de la clase Entrada.
		 * @param k la llave
		 * @param v el valor
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		public Entrada(K k, V v) {
			this.k = k;
			this.v = v;
		}
		
		/**
		 * Método getter del atributo k.
		 * @return k la llave
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		public K k() {
			return k;
		}
		
		/**
		 * Método getter del atributo v.
		 * @return v el valor
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		public V v() {
			return v;
		}
		
		/**
		 * Método setter del atributo v.
		 * @param valor el valor nuevo
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		public void setV(V valor) {
			v = valor;
		}
		
		/**
		 * Método que convierte los atributos de la clase Entrada a String.
		 * @return string
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public String toString() {
			return "(" + k + "," + v + ")";
		}
	}
	
	//Atributos de la clase mapa.
	private List<Entrada<K,V>>[] tabla;
	private int numeroEntradas;
	
	/**
	 * Método constructor de la clase MapaDispersionAbierta.
	 * En este caso, no recibe ningún parámetro porque es un constructor por defecto.
	 */
	/*
	 * Complejidad temporal: O(tabla.length).
	 */
	@SuppressWarnings("unchecked")
	public MapaDispersionAbierta() {
		tabla = new List[10];
		for(int i = 0; i < tabla.length; i++) {
			tabla[i] = new LinkedList<Entrada<K,V>>();
			numeroEntradas = 0;
		}
	}
	
	/**
	 * Método constructor de la clase MapaDispersionAbierta.
	 * @param longitud la longitud del mapa.
	 */
	/*
	 * Complejidad temporal: O(longitud).
	 */
	@SuppressWarnings("unchecked")
	public MapaDispersionAbierta(int longitud) {
		 tabla = new LinkedList[longitud];
		 for(int i=0; i<longitud; i++) {
			 tabla[i] = new LinkedList<Entrada<K, V>>();
		 }
		 numeroEntradas = 0;
	}
	
	/**
	 * Método que calcula el código Hash de la llave.
	 * @param llave la llave para sacar el código
	 * @return el código de la llave
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public int hash(K llave) {
		int cod = llave.hashCode() % tabla.length;
		if(cod < 0) {
			cod += tabla.length;
		}
		return cod;
	}
	
	/**
	 * Método que añade una entrada al mapa.
	 * @param llave la llave de la entrada
	 * @param valor el valor de la entrada
	 */
	/*
	 * Complejidad temporal: O(buscaEnLista) = O(list.size()) -> peor.
	 * Complejidad temporal: O(1) <-caso promedio. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void anhade(K llave, V valor) {
		int cod = hash(llave);
		Entrada entrada = buscaEnLista(tabla[cod], llave);
		if(entrada == null) {
			entrada = new Entrada(llave, valor);
			tabla[cod].add(0, entrada);
			numeroEntradas++;
		} else {
			entrada.setV(valor);
		}
	}

	/**
	 * Método privado que busca dentro de la lista una entrada por su llave. 
	 * @param list la lista donde ha de buscar
	 * @param llave la llave que tiene que encontrar
	 * @return entrada la entrada buscada
	 */
	/*
	 * Complejidad temporal: O(list.size()).
	 */
	private Entrada<K, V> buscaEnLista(List<Entrada<K, V>> list, K llave) {
		for(Entrada<K,V> auxiliar : list) {
			if(auxiliar.k.equals(llave)) {
				return auxiliar;
			}
		}
		return null;
	}

	/**
	 * Método que elimina una entrada del mapa.
	 * @param llave la llave de la entrada a eliminar
	 */
	/*
	 * Complejidad temporal: O(eliminaDeLaLista) = O(list.size()) -> peor.
	 * Complejidad temporal: O(1) <-caso promedio. 
	 */
	@Override
	public void elimina(K llave) {
		int cod = hash(llave);
		boolean eliminado = eliminaDeLaLista(tabla[cod], llave);
		if(eliminado) {
			numeroEntradas--;
		}
	}
	
	/**
	 * Método privado que elimina una entrada determinada de la lista.
	 * @param list la lista de la que se tiene que eliminar
	 * @param llave la llave para encontrar la entrada a eliminar
	 * @return true si se ha eliminado, false si no se ha encontrado
	 */
	/*
	 * Complejidad temporal: O(list.size()).
	 */
	private boolean eliminaDeLaLista(List<MapaDispersionAbierta<K, V>.Entrada<K, V>> list, K llave) {
		Iterator<Entrada<K,V>> iterador = list.iterator();
		while(iterador.hasNext()) {
			Entrada<K,V> auxiliar = iterador.next();
			if(auxiliar.k.equals(llave)) {
				iterador.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que busca una entrada en el mapa.
	 * @param llave la llave para identificar la entrada
	 * @return v el valor buscado
	 */
	/*
	 * Complejidad temporal: O(buscaEnLista) = O(list.size()) -> peor.
	 * Complejidad temporal: O(1) <-caso promedio. 
	 */
	@Override
	public V busca(K llave) {
		V v = null;
		int cod = hash(llave);
		Entrada<K,V> entrada = buscaEnLista(tabla[cod], llave);
		if(entrada != null) {
			v = entrada.v;
		}
		return v;
	}

	/**
	 * Método que vacía el mapa. 
	 */
	/*
	 * Complejidad temporal: O(tabla.length - 1).
	 */
	@Override
	public void haceVacio() {
		numeroEntradas = 0;
		for(int i = 0; i < tabla.length - 1; i++) {
			tabla[i].clear();
		}
	}

	/**
	 * Método que devuelve el tamaño del mapa. 
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public int tamanho() {
		return numeroEntradas;
	}
	
	/**
	 * Método que convierte a String el mapa.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
    public String toString() {
      return Arrays.toString(tabla);
    }

}
