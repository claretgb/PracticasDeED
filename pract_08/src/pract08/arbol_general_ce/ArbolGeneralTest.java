package pract08.arbol_general_ce;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test de un arbol general.
 * 
 * @author Estructuras de Datos (UC)
 * @version nov-2017
 */
public class ArbolGeneralTest {

	@Test
	public void testConstructorContenidoRaiz() {
		System.out.println("testConstructorContenidoRaiz");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(5);

		assertTrue(a.tamanho() == 1);

		assertTrue(a.raiz().contenido() == 5);		
		assertTrue(a.raiz().padre() == null);		
		assertTrue(a.raiz().primerHijo() == null);		
		assertTrue(a.raiz().hermanoDcho() == null);

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	public void testConstructorArbolVacio() {
		System.out.println("testConstructorArbolVacio");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>();

		assertTrue(a.tamanho() == 0);

		assertTrue(a.raiz() == null);
	}

	@Test
	public void testInsertaPrimerHijoSimple() {
		System.out.println("testInsertaPrimerHijoSimple");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(1);

		INodoArbolGeneral<Integer> nodoRaiz = a.raiz();

		// inserta el primer hijo de la raiz
		nodoRaiz.insertaPrimerHijo(2);
		INodoArbolGeneral<Integer> nodoHijo = nodoRaiz.primerHijo();

		// comprueba que se ha insertado bien
		assertTrue(nodoRaiz.primerHijo() == nodoHijo);
		assertTrue(nodoHijo.contenido() == 2);
		assertTrue(nodoHijo.padre() == a.raiz());		
		assertTrue(nodoHijo.primerHijo() == null);		
		assertTrue(nodoHijo.hermanoDcho() == null);

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	public void testInsertaHermanoDchoSimple() {
		System.out.println("testInsertaHermanoDchoSimple");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(0);

		INodoArbolGeneral<Integer> nodo = a.raiz();

		// inserta el primer hijo de la raiz
		nodo.insertaPrimerHijo(1);

		// inserta hermano derecho del primer hijo de la raiz
		nodo = nodo.primerHijo();
		nodo.insertaHermanoDcho(2);
		INodoArbolGeneral<Integer> nodoHermanoDcho = nodo.hermanoDcho();

		// comprueba que el primer hijo esta bien
		assertTrue(nodo.contenido() == 1);
		assertTrue(nodo.padre() == a.raiz());		
		assertTrue(nodo.primerHijo() == null);		
		assertTrue(nodo.hermanoDcho() == nodoHermanoDcho);

		// comprueba que el hermano derecho esta bien
		assertTrue(nodoHermanoDcho.contenido() == 2);
		assertTrue(nodoHermanoDcho.padre() == a.raiz());		
		assertTrue(nodoHermanoDcho.primerHijo() == null);		
		assertTrue(nodoHermanoDcho.hermanoDcho() == null);

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	public void testInsertaHermanoDcho() {
		System.out.println("testInsertaHermanoDcho");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(0);

		INodoArbolGeneral<Integer> nodoRaiz = a.raiz();

		// inserta el primer hijo de la raiz
		nodoRaiz.insertaPrimerHijo(1);
		INodoArbolGeneral<Integer> nodoHijo = nodoRaiz.primerHijo();

		// inserta dos hermanos derechos del primer hijo de la raiz
		nodoHijo.insertaHermanoDcho(3);
		nodoHijo.insertaHermanoDcho(2);
		INodoArbolGeneral<Integer> nodoDcho2 = nodoHijo.hermanoDcho();
		INodoArbolGeneral<Integer> nodoDcho3 = nodoDcho2.hermanoDcho();

		// comprueba que el primer hijo esta bien
		assertTrue(nodoHijo.contenido() == 1);
		assertTrue(nodoHijo.padre() == nodoRaiz);		
		assertTrue(nodoHijo.primerHijo() == null);		
		assertTrue(nodoHijo.hermanoDcho() == nodoDcho2);

		// comprueba que el primer hermano derecho esta bien
		assertTrue(nodoDcho2.contenido() == 2);
		assertTrue(nodoDcho2.padre() == nodoRaiz);		
		assertTrue(nodoDcho2.primerHijo() == null);		
		assertTrue(nodoDcho2.hermanoDcho() == nodoDcho3);

		// comprueba que el segundo hermano derecho esta bien
		assertTrue(nodoDcho3.contenido() == 3);
		assertTrue(nodoDcho3.padre() == nodoRaiz);		
		assertTrue(nodoDcho3.primerHijo() == null);		
		assertTrue(nodoDcho3.hermanoDcho() == null);

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	public void testPrimerHijo() {
		System.out.println("testPrimerHijo");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(0);

		INodoArbolGeneral<Integer> nodo = a.raiz();

		// comprueba que primerHijo retorna null
		assertTrue(nodo.primerHijo() == null);

		// inserta dos nodos
		nodo.insertaPrimerHijo(1);
		assertTrue(nodo.primerHijo().contenido() == 1);
		nodo = nodo.primerHijo();
		assertTrue(nodo != null);
		nodo.insertaPrimerHijo(2);
		assertTrue(nodo.primerHijo().contenido() == 2);

		// navega desde la raiz hasta la hoja
		nodo = a.raiz();
		assertTrue(nodo.contenido() == 0);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 1);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 2);
		nodo = nodo.primerHijo();
		assertTrue(nodo == null);	

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	public void testPadre() {
		System.out.println("testPadre");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(0);

		INodoArbolGeneral<Integer> nodo = a.raiz();

		// comprueba que padre retorna null
		assertTrue(nodo.padre() == null);

		// inserta dos nodos
		nodo.insertaPrimerHijo(1);
		assertTrue(nodo.primerHijo().contenido() == 1);
		nodo = nodo.primerHijo();
		assertTrue(nodo != null);
		nodo.insertaPrimerHijo(2);

		// navega desde la hoja hasta la raiz
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 2);
		nodo = nodo.padre();
		assertTrue(nodo.contenido() == 1);
		nodo = nodo.padre();
		assertTrue(nodo.contenido() == 0);
		nodo = nodo.padre();
		assertTrue(nodo == null);	

		muestraEnPreordenLN(a.raiz());
	}

	@Test
	public void testHermanoDcho() {
		System.out.println("testHermanoDcho");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(0);

		INodoArbolGeneral<Integer> nodo = a.raiz();

		// comprueba que hermanoDcho retorna null
		assertTrue(nodo.hermanoDcho() == null);

		// inserta tres hijos
		nodo.insertaPrimerHijo(3);
		assertTrue(nodo.primerHijo().contenido() == 3);
		nodo.insertaPrimerHijo(2);
		assertTrue(nodo.primerHijo().contenido() == 2);
		nodo.insertaPrimerHijo(1);
		assertTrue(nodo.primerHijo().contenido() == 1);

		// navega por los hijos
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 1);
		nodo = nodo.hermanoDcho();
		assertTrue(nodo.contenido() == 2);
		nodo = nodo.hermanoDcho();
		assertTrue(nodo.contenido() == 3);
		nodo = nodo.hermanoDcho();
		assertTrue(nodo == null);	

		muestraEnPreordenLN(a.raiz());
	}


	@Test
	public void testErrorInsertaHermanoRaiz() {
		System.out.println("testErrorInsertaHermanoRaiz");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(1);

		INodoArbolGeneral<Integer> nodo = a.raiz();

		nodo.insertaPrimerHijo(2);

		try {
			nodo.insertaHermanoDcho(3);
			fail("No error al tratar de insertar el hermano de la raiz");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

	}


	@Test
	public void testInsertaTamanhoRecorre() {
		System.out.println("testInsertaTamanhoRecorre");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(3);

		assertTrue(a.tamanho() == 1);

		INodoArbolGeneral<Integer> nodo = a.raiz();

		nodo.insertaPrimerHijo(31);        //          3
		assertTrue(a.tamanho() == 2);      //    31           32
		nodo = nodo.primerHijo();          // 311         321 322 323
		nodo.insertaPrimerHijo(311);
		assertTrue(a.tamanho() == 3);
		nodo.insertaHermanoDcho(32);        
		assertTrue(a.tamanho() == 4);
		nodo = nodo.hermanoDcho();
		nodo.insertaPrimerHijo(321);
		assertTrue(a.tamanho() == 5);
		nodo = nodo.primerHijo();
		nodo.insertaHermanoDcho(322); 
		assertTrue(a.tamanho() == 6);
		nodo = nodo.hermanoDcho();
		nodo.insertaHermanoDcho(323);
		assertTrue(a.tamanho() == 7);

		muestraEnPreordenLN(a.raiz());

		// recorre el arbol creado
		nodo = a.raiz();
		assertTrue(nodo.contenido() == 3);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 31);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 311);
		assertTrue(nodo.primerHijo() == null);
		nodo = nodo.padre().hermanoDcho();
		assertTrue(nodo.contenido() == 32);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 321);
		nodo = nodo.hermanoDcho();
		assertTrue(nodo.contenido() == 322);
		nodo = nodo.hermanoDcho();
		assertTrue(nodo.contenido() == 323);
		assertTrue(nodo.hermanoDcho() == null);

	}	

	@Test
	public void testModifica() {
		System.out.println("testModifica");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(4);
		INodoArbolGeneral<Integer> nodo = a.raiz();

		nodo.insertaPrimerHijo(41);
		nodo = nodo.primerHijo();       //              4
		nodo.insertaHermanoDcho(42);    //     41              42
		nodo = nodo.hermanoDcho();      //  412              422
		nodo.insertaPrimerHijo(422);
		nodo = nodo.padre().primerHijo();
		nodo.insertaPrimerHijo(412);
		assertTrue(a.tamanho() == 5);

		muestraEnPreordenLN(a.raiz());

		// modifica el arbol
		nodo = a.raiz();               //             6
		nodo.cambiaContenido(6);       //    61              42
		nodo = nodo.primerHijo();      // 412              622
		nodo.cambiaContenido(61);
		nodo = nodo.hermanoDcho().primerHijo();
		nodo.cambiaContenido(622);

		muestraEnPreordenLN(a.raiz());

		// comprueba que esta bien
		nodo = a.raiz();
		assertTrue(nodo.contenido() == 6);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 61);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 412);
		nodo = nodo.padre().hermanoDcho();
		assertTrue(nodo.contenido() == 42);
		nodo = nodo.primerHijo();
		assertTrue(nodo.contenido() == 622);
	}

	@Test
	public void testCortaRama() {
		System.out.println("testCortaRama");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(4);
		INodoArbolGeneral<Integer> nodo = a.raiz();

		nodo.insertaPrimerHijo(41);
		nodo = nodo.primerHijo();       //              4
		nodo.insertaHermanoDcho(42);    //     41              42
		nodo = nodo.hermanoDcho();      //  412              422
		nodo.insertaPrimerHijo(422);
		nodo = nodo.padre().primerHijo();
		nodo.insertaPrimerHijo(412);
		assertTrue(a.tamanho() == 5);

		muestraEnPreordenLN(a.raiz());

		// corta rama
		nodo = a.raiz().primerHijo();
		IArbolGeneral<Integer> rama = nodo.cortaRama();

		// comprueba que los tamanhos son correctos
		assertTrue(a.tamanho() == 3);
		assertTrue(rama.tamanho() == 2);

		// comprueba que los arboles estan bien
		assertTrue(a.raiz().contenido() == 4);
		assertTrue(a.raiz().primerHijo().contenido() == 42);
		assertTrue(a.raiz().primerHijo().primerHijo().contenido() == 422);
		assertTrue(rama.raiz().contenido() == 41);
		assertTrue(rama.raiz().primerHijo().contenido() == 412);

		// muestra arbol
		System.out.print("Arbol:");
		muestraEnPreordenLN(a.raiz());
		
		// comprueba que la raiz de la rama no tiene padre
		assertTrue(rama.raiz().padre() == null);

		// muestra la rama
		System.out.print("Rama:");
		muestraEnPreordenLN(rama.raiz());
	}

	@Test
	public void testVaciaYCambiaRaiz() {
		System.out.println("testVaciaYCambiaRaiz");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(4);
		INodoArbolGeneral<Integer> nodo = a.raiz();
		nodo.insertaPrimerHijo(41);
		assertTrue(a.tamanho() == 2);

		// anhade raiz debe fallar en un arbol que no esta vacio
		try {
			a.anhadeRaiz(123);
			fail("No excepción UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}

		// vacia
		a.haceVacio();
		assertTrue(a.tamanho() == 0);
		assertTrue(a.raiz() == null);

		// al anhadir una nueva raiz es posible seguir trabajando con
		// el arbol
		a.anhadeRaiz(1);
		nodo = a.raiz();
		nodo.insertaPrimerHijo(3);
		nodo.insertaPrimerHijo(2);
		assertTrue(a.tamanho() == 3);

		// se puede recorrer el arbol
		nodo = a.raiz();
		nodo = nodo.primerHijo().hermanoDcho();
		assertTrue(nodo.contenido() == 3);	
	}

	@Test
	public void testAnhadeRama() {
		System.out.println("testAnhadeRama");
		IArbolGeneral<Integer> a = new ArbolGeneralCE<Integer>(4);

		// crea el arbol
		INodoArbolGeneral<Integer> nodo = a.raiz();

		nodo.insertaPrimerHijo(41);
		nodo = nodo.primerHijo();       //              4
		nodo.insertaHermanoDcho(42);    //     41              42
		nodo = nodo.hermanoDcho();      //  412              422
		nodo.insertaPrimerHijo(422);
		nodo = nodo.padre().primerHijo();
		nodo.insertaPrimerHijo(412);
		assertTrue(a.tamanho() == 5);

		final int tamanhoOrigArbol = a.tamanho();

		muestraEnPreordenLN(a.raiz());

		// crea una rama
		IArbolGeneral<Integer> r1 = new ArbolGeneralCE<Integer>(5);

		nodo = r1.raiz();
		nodo.insertaPrimerHijo(52);   //          5
		nodo.insertaPrimerHijo(51);   //    51          52
		nodo = nodo.primerHijo();     //             521
		nodo = nodo.hermanoDcho();
		nodo.insertaPrimerHijo(521);
		final int tamanhoRama1 = r1.tamanho();

		// anhade la rama como primer hijo del nodo 41
		nodo = a.raiz().primerHijo();
		nodo.insertaRamaPrimerHijo(r1);

		muestraEnPreordenLN(a.raiz());
		assertTrue(nodo.primerHijo().contenido().equals(5));
		assertTrue(a.tamanho() == tamanhoOrigArbol + tamanhoRama1);
		// el padre de la raiz de la rama debe ser el nodo
		assertTrue(nodo.primerHijo().padre() == nodo);
		// comprueba que la rama queda vacia
		assertTrue(r1.raiz() == null);

		// crea otra rama
		IArbolGeneral<Integer> r2 = new ArbolGeneralCE<Integer>(6);
		INodoArbolGeneral<Integer> nodo2 = r2.raiz();   //     6
		nodo2.insertaPrimerHijo(61);                    //  61
		final int tamanhoRama2 = r2.tamanho();

		// anhade la rama como hermano derecho del nodo 41
		nodo = a.raiz().primerHijo();
		nodo.insertaRamaHermanoDcho(r2);

		muestraEnPreordenLN(a.raiz());
		
		assertTrue(nodo.hermanoDcho().contenido().equals(6));
		assertTrue(a.tamanho() == tamanhoOrigArbol + tamanhoRama1 +
					tamanhoRama2);
		// el padre de la raiz de la rama debe ser la raiz
		assertTrue(nodo.hermanoDcho().padre() == a.raiz());
		// comprueba que la rama queda vacia
		assertTrue(r2.raiz() == null);
	}

	/**
	 * Metodo auxiliar que muestra el arbol en preorden.
	 * Finaliza con un salto de linea.
	 * @param nodo raiz del arbol a mostrar.
	 */
	private static void muestraEnPreordenLN(INodoArbolGeneral<Integer> nodo) {
		muestraEnPreorden(nodo);
		System.out.println();
	}
	

	/**
	 * Metodo auxiliar que muestra el arbol en preorden.
	 * @param nodo raiz del arbol a mostrar.
	 */
	private static void muestraEnPreorden(INodoArbolGeneral<Integer> nodo) {
		// muestra el contenido del nodo actual
		System.out.print(nodo.contenido() + "  ");

		// muestra en preorden los subarboles de los hijos
		nodo = nodo.primerHijo();
		while (nodo != null) {
			muestraEnPreorden(nodo);
			nodo = nodo.hermanoDcho();
		}
	}
}
