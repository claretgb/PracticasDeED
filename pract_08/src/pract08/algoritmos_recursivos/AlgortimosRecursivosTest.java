package pract08.algoritmos_recursivos;

import static org.junit.Assert.*;

import org.junit.Test;

import pract08.arbol_general_ce.ArbolGeneralCE;
import pract08.arbol_general_ce.IArbolGeneral;
import pract08.arbol_general_ce.INodoArbolGeneral;

/**
 * Test de algoritmos recursivos en arboles generales.
 *
 * @author Estructuras de Datos (UC)
 * @version nov-2017
 */
public class AlgortimosRecursivosTest {

	@Test
	public void testCuentaOcurrenciasElemento() {
		System.out.println("testCuentaOcurrenciasElemento");
		// crea un arbol binario
		IArbolGeneral<Integer> arbol = new ArbolGeneralCE<Integer>();

		// arbol vacio
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(
				arbol, 1) == 0);

		// arbol con solo la raiz
		arbol.anhadeRaiz(1);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(
				arbol, 1) == 1);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(
				arbol, 2) == 0);

		// anhade mas nodos al arbol
		INodoArbolGeneral<Integer> nodo = arbol.raiz();

		nodo.insertaPrimerHijo(3);
		nodo.insertaPrimerHijo(2);

		nodo = nodo.primerHijo(); // <- 2
		nodo.insertaPrimerHijo(1);
		nodo.insertaPrimerHijo(5);
		nodo.insertaPrimerHijo(4);
		nodo = nodo.primerHijo().hermanoDcho(); // <-  5
		nodo.insertaPrimerHijo(1);

		nodo = nodo.padre().hermanoDcho();  // <- 3
		nodo.insertaPrimerHijo(2);
		// Estado final del arbol
		//               1
		//        2              3
		//    4   5   1       2
		//       1 
		assertTrue(arbol.tamanho() == 8);

		// comprueba que las cocurrencias medidas son las correctas
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 1) == 3);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 2) == 2);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 3) == 1);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 4) == 1);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 5) == 1);
		assertTrue(AlgoritmosRecursivos.cuentaOcurrenciasElemento(arbol, 6) == 0);		
	}



	@Test
	public void testbuscaElemIgualAPrimerHijo() {
		System.out.println("testbuscaElemIgualAPrimerHijo");
		// crea un arbol binario
		IArbolGeneral<Integer> arbol = new ArbolGeneralCE<Integer>();

		// arbol vacio
		assertTrue(AlgoritmosRecursivos.buscaElemIgualAPrimerHijo(arbol) == null);

		// arbol con solo la raiz
		arbol.anhadeRaiz(1);
		assertTrue(AlgoritmosRecursivos.buscaElemIgualAPrimerHijo(arbol) == null);

		// anhade mas nodos al arbol
		INodoArbolGeneral<Integer> nodo = arbol.raiz();

		nodo.insertaPrimerHijo(3);
		nodo.insertaPrimerHijo(2);

		nodo = nodo.primerHijo(); // <- 2
		nodo.insertaPrimerHijo(1);
		nodo.insertaPrimerHijo(5);
		nodo.insertaPrimerHijo(2);
		nodo = nodo.primerHijo().hermanoDcho(); // <-  5
		nodo.insertaPrimerHijo(1);

		nodo = nodo.padre().hermanoDcho();  // <- 3
		nodo.insertaPrimerHijo(2);
		// Estado final del arbol
		//               1
		//        2              3
		//    2   5   1       2
		//       1 
		assertTrue(arbol.tamanho() == 8);   

		// comprueba se retorna el elemento con dos hijos iguales
		assertTrue(AlgoritmosRecursivos.buscaElemIgualAPrimerHijo(
				arbol) == 2);
	}

}
