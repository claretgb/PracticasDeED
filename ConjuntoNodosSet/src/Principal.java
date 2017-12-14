
public class Principal {
	public static void main(String[] args) {
		ISet <String> lista = new SetSimpleEnlace <String> ();
		lista.anhadir("Pepe");
		lista.anhadir("Juan");
		lista.anhadir("Pepe");
		System.out.println(lista.contiene("Pepe"));
		System.out.println(lista.contiene("Ana"));
		lista.elimina("Juan");
		System.out.println(lista.contiene("Juan"));
		lista.elimina("Pepe");
		System.out.println(lista.contiene("Pepe"));
		lista.anhadir("Pepe");
		lista.anhadir("Juan");
		ISet <String> lista2 = new SetSimpleEnlace <String> ();
		lista2.anhadir("Pepe");
		lista2.anhadir("Juan");
		lista2.anhadir("Lucia");
		lista2.anhadir("Marta");
		System.out.println(lista);
		System.out.println(lista2);
		ISet <String> conjuntoInterseccion = lista.conjuntoInterseccion(lista2);
		System.out.println(conjuntoInterseccion.contiene("Juan"));
		System.out.println(conjuntoInterseccion.contiene("Lucia"));
		System.out.println(conjuntoInterseccion);
		ISet <String> conjuntoUnion = lista.conjuntoUnion(lista2);
		System.out.println(conjuntoUnion);	
		ISet <String> conjuntoDiferencia = lista.diferencia(lista2);
		System.out.println(conjuntoDiferencia);
	}
}