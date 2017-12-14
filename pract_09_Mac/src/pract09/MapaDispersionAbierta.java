package pract09;

import java.util.*;

public class MapaDispersionAbierta<K,V> implements IMapa<K,V> {

	@SuppressWarnings("hiding")
	public class Entrada<K, V> {
		private K k;
		private V v;
		
		public Entrada(K k, V v) {
			this.k = k;
			this.v = v;
		}
		
		public K k() {
			return k;
		}
		
		public V v() {
			return v;
		}
		
		public void setV(V valor) {
			v = valor;
		}
	}
	
	//Atributos de la clase mapa.
	private List<Entrada<K,V>>[] tabla;
	private int numeroEntradas;
	
	@SuppressWarnings("unchecked")
	public MapaDispersionAbierta() {
		tabla = new List[10];
		for(int i = 0; i < tabla.length; i++) {
			tabla[i] = new LinkedList<Entrada<K,V>>();
			numeroEntradas = 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public MapaDispersionAbierta(int longitud) {
		 tabla = new LinkedList[longitud];
		 for(int i=0; i<longitud; i++) {
			 tabla[i] = new LinkedList<Entrada<K, V>>();
		 }
		 numeroEntradas = 0;
	}
	
	public int hash(K llave) {
		int cod = llave.hashCode() % tabla.length;
		if(cod < 0) {
			cod += tabla.length;
		}
		return cod;
	}
	
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
	
	/*
	 * int hc = k.hashCode();
	 * int pos = Math.Abs(hc) % tabla.length;
	 * Entrada<K,V> eaux = buscaEnLista(tabla[pos], k);
	 * }
	 * Busca:
	 * if(eaux == null) {
	 * 	tabla[pos].add(new Entrada(llave, valor));
	 * 	numDatos++;
	 * } else {
	 * 	eaux.v = v;
	 * }
	 */
	
	/*
	 * vgetK
	 * {
	 * int hc = ...
	 * int pos = ...
	 * Entrada<K,V> eau = busca...
	 * if(eaux == null) {
	 * return null;
	 * }
	 * else {
	 * return eaux.v;
	 */
	
	/*
	 * public eliminaLista (List<Entrada<K,V>> l, K k) {
	 * 	Iterator<Entrada<K,V>> ite = l.iterator();
	 * 	while(ite.hasNext()) {
	 * 		Entrada<K,V> aux = ite.next();
	 * 		if(aux.k.equals(k)) {
	 * 			ite.remove();
	 * 			return true;
	 * 		}
	 * 	}
	 * 	return false;
	 * }
	 */

	private Entrada<K, V> buscaEnLista(List<Entrada<K, V>> list, K llave) {
		for(Entrada<K,V> auxiliar : list) {
			if(auxiliar.k.equals(llave)) {
				return auxiliar;
			}
		}
		return null;
	}

	@Override
	public void elimina(K llave) {
		int cod = hash(llave);
		boolean eliminado = eliminaDeLaLista(tabla[cod],llave);
		if(eliminado) {
			numeroEntradas--;
		}
	}
	

	private boolean eliminaDeLaLista(List<MapaDispersionAbierta<K, V>.Entrada<K, V>> list, K llave) {
		// TODO Auto-generated method stub
		return false;
	}

	public void anhadePrimero(Entrada<K, V> entrada) {
		
	}

	@Override
	public V busca(K llave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void haceVacio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int tamanho() {
		return tabla.length;
	}
	
	public boolean regenerar() {
		int contador = colision();
		if((contador/tabla.length)*100 >= 50) {
			return true;
		}
		return false;
	}
	
	public int colision() {
		int contador = 0;
		for(int i = 0; i < tabla.length; i++) {
			if(tabla[i].size() > 1) {
				contador++;
			}
		}
		return contador;
	}

	@SuppressWarnings("unchecked")
	public void doblar() {
		List<Entrada<K,V>>[] tabla2 = new List[tabla.length*100];
		for(int i = 0; i < tabla2.length; i++) {
			tabla2[i]= new LinkedList<Entrada<K,V>>();
		}
		for(int i = 0; i < tabla.length; i++) {
			for(Entrada<K,V> entrada : tabla[i]) {
				int hashCode = entrada.k.hashCode();
				int posicion = Math.abs(hashCode)%tabla2.length;
				tabla2[posicion].add(entrada);
			}
		}
		tabla = tabla2;
	}
	
	public String espia() {
		String s = "";
		for(int i = 0; i < tabla.length; i++) {
			s = s + i + ":" + tabla[i].size() + "\n";
		}
		return s;
	}
}
