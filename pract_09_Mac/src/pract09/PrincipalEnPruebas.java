package pract09;

import java.util.*;

public class PrincipalEnPruebas {

	public static void main1(String []args) {
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("17867654K", "Juan"); //Donde el DNI es la llave y el nombre el valor.
		System.out.println(mapa.get("17867654K"));
		String s = "Hola";
		System.out.println(s.hashCode());
	}
	
	public static void main2(String[] args) {
		IMapa<String,String> mapa = new MapaDispersionAbierta(5);
		mapa.anhade("Juan", "Cálculo");
		mapa.anhade("Pedro", "Álgebra");
		mapa.anhade("Alejandro", "EDa");
		mapa.anhade("Antonio", "Geografía");
		mapa.anhade("Paco", "Matemáticas");
		mapa.anhade("Quini", "Je");
		mapa.anhade("Rosa", "Arte");
		mapa.anhade("Ana", "Dibujo");
		mapa.anhade("Lucía", "TIC");
		mapa.anhade("Laura", "Filosofía");
		System.out.println(mapa.espia());
		mapa.doblar();
		System.out.println(mapa.espia());
	}
	
	public static void main(String[] args) {
		List<String> lista = new LinkedList<String>();
		lista.add("Martínez");
		lista.add("González");
		lista.add("Martínez");
		lista.add("Martínez");
		lista.add("González");
		lista.add("López");
		List<String> lista2 = sinRepetidos(lista);
		System.out.println(lista2);
	}
		
	public static List<String> sinRepetidos(List<String> lista) {
		SortedSet<String> conjunto = new TreeSet<String>();
		List<String> lista2 = new LinkedList<String>();
		for(int i = 0; i < lista.size(); i++) {
			if(!conjunto.contains(lista.get(i))) {
				conjunto.add(lista.get(i));
				lista2.add(lista.get(i));
			}
		}
		return lista2;
	}
}
