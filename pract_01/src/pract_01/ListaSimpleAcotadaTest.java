package pract_01;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de una lista de tamanho acotado con acceso
 * unicamente posicional (no tiene iterador)
 *
 * @author Estructuras de Datos (UC)
 * @version sep-2017
 */
public class ListaSimpleAcotadaTest {

	@Test
	public void testConstructor() {
		System.out.println("testConstructor");

		IListaSimple<String> lst =
				new ListaArraySimple<String>(4);

		// comprueba que la lista comienza vacia
		muestraLista(lst);
		int tam = lst.tamanho();
		assertTrue("Error: tamanho:" + tam + " deberia ser 0", tam == 0);

		// comprueba que se produce un error al acceder al primer elemento
		try {
			lst.obtenElemento(0);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
	}

	@Test
	public void testAnhadeSimple() {
		System.out.println("testAnhadeSimple");

		IListaSimple<String> lst =
				new ListaArraySimple<String>(5);
		int tam;
		String str;

		// anhade primer elemento en lista vacia
		lst.anhade(0, "A");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
		assertTrue("Error: str deberia ser A, pero es:"+ str, str.equals("A"));

		// anhade elemento final en lista con un elemento
		lst.anhade(1, "C");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 2, pero es:" + tam, tam == 2);
		assertTrue("Error: el ultimo elemento deberia ser C, pero es:"+ str,
				str.equals("C"));

		// anhade elemento intermedio
		lst.anhade(1, "B");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);

		// comprueba que los elementos estan donde deben
		str = lst.obtenElemento(0);
		assertTrue("Error: el elemento 0 deberia ser A, pero es:"+ str,
				str.equals("A"));
		str = lst.obtenElemento(1);
		assertTrue("Error: el elemento 1 deberia ser B, pero es:"+ str,
				str.equals("B"));
		str = lst.obtenElemento(2);		
		assertTrue("Error: el elemento 2 deberia ser C, pero es:"+ str,
				str.equals("C"));
	}

	@Test
	public void testAnhadePosIntermedia() {
		System.out.println("testAnhadePosIntermedia");

		IListaSimple<String> lst =
				new ListaArraySimple<String>(5);
		int tam;
		String str;

		// anhade en lista vacia
		lst.anhade(0, "C");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
		assertTrue("Error: str deberia ser C, pero es:"+ str, str.equals("C"));

		// anhade primero en lista con un elemento
		lst.anhade(0, "A");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 2, pero es:" + tam, tam == 2);
		assertTrue("Error: el primer elemento deberia ser A, pero es:"+ str,
				str.equals("A"));

		// anhade elemento intermedio
		lst.anhade(1, "B");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);
		assertTrue("Error: el elemento 1 deberia ser B, pero es:"+ str,
				str.equals("B"));

		// anhade elemento final
		lst.anhade(3, "D");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(3);
		assertTrue("Error: el tamanho deberia ser 4, pero es:" + tam, tam == 4);
		assertTrue("Error: el elemento deberia ser D, pero es:"+ str,
				str.equals("D"));		
	}

	@Test
	public void testAnhadeLimiteCapacidad() {
		System.out.println("testAnhadeLimiteCapacidad");

		IListaSimple<String> lst =
				new ListaArraySimple<String>(5);
		int tam;
		String str;

		// anhade elementos hasta casi llenar la lista
		lst.anhade(0, "C");
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(3, "D");
		muestraLista(lst);
		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 4, pero es:" + tam, tam == 4);

		// anhade elemento que llena la lista
		lst.anhade(4, "E");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(4);
		assertTrue("Error: el tamanho deberia ser 5, pero es:" + tam, tam == 5);
		assertTrue("Error: el elemento deberia ser E, pero es:"+ str,
				str.equals("E"));

		// intenta anhadir un elemento que no cabe
		try {
			lst.anhade(1, "X");
			fail("No excepción posicion incorrecta");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		assertTrue("Error: el tamanho deberia ser 5, pero es:" + tam, tam == 5);

		// intenta obtener el primer elemento fuera de la capacidad
		try {
			str	= lst.obtenElemento(5);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
	}

	@Test
	public void testElimina() {
		System.out.println("testElimina");

		IListaSimple<String> lst = new ListaArraySimple<String>(4);
		int tam;
		String str;

		// elimina primero dejando la lista vacia
		lst.anhade(0, "Z");
		str = lst.elimina(0);
		
		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 0, pero es:" + tam, tam == 0);	
		
		assertTrue("Error: el elemento eliminado deberia ser Z, pero es:"+ str,
				str.equals("Z"));
		muestraLista(lst);

		// trata te obtener un elemento que ya ha sido eliminado
		try {
			str = lst.obtenElemento(0);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	

		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 0, pero es:" + tam, tam == 0);	

		// elimina primero dejando lista con elementos
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(2, "C");
		lst.anhade(3, "D");
		str = lst.elimina(0);
		assertTrue("Error: el elemento eliminado deberia ser A, pero es:"+ str,
				str.equals("A"));
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(0);
		assertTrue("Error: el tamanho deberia ser 3, pero es:" + tam, tam == 3);
		assertTrue("Error: el primer elemento deberia ser B, pero es:"+ str, str.equals("B"));

		// elimina intermedio
		str = lst.elimina(1);
		assertTrue("Error: el elemento eliminado deberia ser C, pero es:"+ str,
				str.equals("C"));
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(1);
		assertTrue("Error: el tamanho deberia ser 2, pero es:" + tam, tam == 2);
		assertTrue("Error: el elemento 1 deberia ser D, pero es:"+ str, str.equals("D"));

		// elimina ultimo
		str = lst.elimina(1);
		assertTrue("Error: el elemento eliminado deberia ser D, pero es:"+ str,
				str.equals("D"));
		muestraLista(lst);
		try {
			str = lst.obtenElemento(1);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 1, pero es:" + tam, tam == 1);
	}

	@Test
	public void testEliminaLimiteCapacidad() {
		System.out.println("testEliminaLimiteCapacidad");

		IListaSimple<String> lst =
				new ListaArraySimple<String>(5);
		int tam;
		String str;

		// anhade elementos hasta llenar la lista
		lst.anhade(0, "C");
		lst.anhade(0, "A");
		lst.anhade(1, "B");
		lst.anhade(3, "D");
		lst.anhade(4, "E");
		muestraLista(lst);
		tam = lst.tamanho();
		str = lst.obtenElemento(4);
		assertTrue("Error: el tamanho deberia ser 5, pero es:" + tam, tam == 5);
		assertTrue("Error: el elemento deberia ser E, pero es:"+ str,
				str.equals("E"));

		// intenta eliminar un elemento fuera de la capacidad de la lista
		try {
			str = lst.elimina(5);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
		tam = lst.tamanho();
		assertTrue("Error: el tamanho deberia ser 5, pero es:" + tam, tam == 5);

		// elimina el ultimo elemento
		str = lst.elimina(4);
		muestraLista(lst);
		tam = lst.tamanho();
		assertTrue("Error: el elemento eliminado deberia ser E, pero es:"+ str,
				str.equals("E"));
		assertTrue("Error: el tamanho deberia ser 4, pero es:" + tam, tam == 4);	
	}

	@Test
	public void testPosIncorrecta() {
		System.out.println("testAnhadePosIncorrecta");

		IListaSimple<String> lst = new ListaArraySimple<String>(5);

		// posicion negativa
		try {
			lst.anhade(-1, "Z");
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
		muestraLista(lst);

		try {
			lst.elimina(-1);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		try {
			lst.obtenElemento(-1);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// posicion correcta
		lst.anhade(0, "A");
		muestraLista(lst);

		// posicion fuera de la lista
		try {
			lst.anhade(2, "B");
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		muestraLista(lst);

		try {
			lst.elimina(2);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		try {
			lst.obtenElemento(2);
			fail("No excepción posicion incorrecta");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}	
	}


	/**
	 * Metodo auxiliar que muestra la lista por pantalla
	 * @param lst lsita a mostrar
	 */
	public void muestraLista(IListaSimple<String> lst) {
		String str = "[";
		for(int i=0; i<lst.tamanho(); i++) {
			try {
				str += lst.obtenElemento(i);
			} catch (Exception e) {
				// obtenElemento ha lanzado una excepcion
				str += "error";
			}
			if (i < lst.tamanho()-1) {
				str = str + ", ";
			}
		}
		str += "]";
		System.out.println(str);
	}
}
