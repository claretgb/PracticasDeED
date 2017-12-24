package pract12;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Prueba de una cola de prioridad.
 * 
 * @author Estructuras de Datos (UC)
 * @version dic-17
 *
 */
public class ColaPrioMonticuloTest {
	
	/**
	 * Comprobaciones habituales sobre la cola. Prueba los métodos masPrioritario(), tamanho() y
	 * estaVacia(). Comprueba que el elemento menor es el esperado, que el tamaño es también
	 * el esperado y que la cola está vacía si, y sólo si, tamaño es 0
	 * @param cola cola a comprobar
	 * @param menorEsperado elemento que debe ser el menor
	 * @param tamanhoEsperado tamaño que debe tener la cola
	 */
	private static void compruebaCola(IColaPrioridad<Integer> cola,
			int menorEsperado, int tamanhoEsperado) {
		// muestra la cola, es necesario definir el metodo toString
		// de la cola.
		System.out.println(cola);
		
		assertTrue("Error tamaño " + cola.tamanho(), tamanhoEsperado == cola.tamanho());
		
		Integer i = cola.masPrioritario();
		
		// Salida más explicativa para el menor esperado
		String menorEsperadoTexto = null;
		if (menorEsperado >= 0) {
			menorEsperadoTexto = String.valueOf(menorEsperado);
		}
		else menorEsperadoTexto = "ninguno, cola vacía";
		
		assertTrue("Error, menor encontrado: " + i + ", menor esperado: " + menorEsperadoTexto, 
				(tamanhoEsperado==0 && i==null) || (tamanhoEsperado>0 && i.equals(menorEsperado)));
		
		assertTrue("tamaño:" + tamanhoEsperado + " vacía:" + (cola.tamanho()==0),
				(tamanhoEsperado==0 && (cola.tamanho()==0)) || 
				(tamanhoEsperado>0 && (cola.tamanho()>0)));
	}

	/**
	 * Prueba que el constructor crea una cola vacía
	 */
	@Test
	public void testColaPrioMonticulo() {
		System.out.println("testColaPrioMonticulo");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, -1, 0);
	}

	/**
	 * Prueba general de la cola, prueba los métodos encolaConPrioridad(),
	 * desencolaMasPrioritario(), masPrioritario(), tamanho() y estaVacia()
	 */
	@Test
	public void testCola() {
		System.out.println("testCola");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, -1, 0);
		
		cola.encolaConPrioridad(3);
		compruebaCola(cola, 3, 1);
		
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		
		compruebaCola(cola, 1, 4);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==1);		
		compruebaCola(cola, 2, 3);

		
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(1);		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==1);		
		compruebaCola(cola, 2, 4);		
		i = cola.desencolaMasPrioritario();		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==2);		
		compruebaCola(cola, 3, 2);		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==3);		
		compruebaCola(cola, 5, 1);		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==5);		
		compruebaCola(cola, -3, 0);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==null);		
		compruebaCola(cola, -2, 0);
	}
	
	/**
	 * Prueba que la cola se puede llenar completamente y que sigue funcionando
	 * bien.
	 */
	@Test
	public void testColaLlena() {
		System.out.println("testColaLlena");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		
		// llena cola
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(4);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);		
		compruebaCola(cola, 1, 8);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==1);		
		compruebaCola(cola, 2, 7);
		
		cola.encolaConPrioridad(3);		
		compruebaCola(cola, 2, 8);
	}
	
	/**
	 * Prueba para superar la capacidad de la cola.
	 */
	@Test
	public void testColaSuperaCapacidad() {
		System.out.println("testColaSuperaCapacidad");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(3);
		
		// cola llena tras encolar
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(4);
		compruebaCola(cola, 1, 3);
				
		// trata de anhadir un elemento a una cola llena
		try {
			cola.encolaConPrioridad(3);	
			fail("No excepción cola llena");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		
		// cola llena tras haceVacia
		cola.haceVacia();
		compruebaCola(cola, -1, 0);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(4);
		compruebaCola(cola, 1, 3);
		// trata de anhadir un elemento a una cola llena
		try {
			cola.encolaConPrioridad(3);	
			fail("No excepción cola llena");
		} catch (IndexOutOfBoundsException e) {
			// El comportamiento correcto es que se lance la excepción
			// Simplemente la cojo para que no salga fuera del método y
			// JUnit lo interprete como un error
		}
		
	}

	/**
	 * Prueba la situación en que el hueco creado al desencolar no hay que hundirle
	 * hasta el último nivel
	 */
	@Test
	public void testDesencolaMasPrioritario() {
		System.out.println("testDesencolaMasPrioritario");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(6);
		cola.encolaConPrioridad(4);		
		compruebaCola(cola, 1, 6);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==1);		
		compruebaCola(cola, 2, 5);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==2);		
		compruebaCola(cola, 3, 4);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==3);		
		compruebaCola(cola, 4, 3);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==4);		
		compruebaCola(cola, 5, 2);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==5);		
		compruebaCola(cola, 6, 1);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==6);		
		compruebaCola(cola, -7, 0);
	}
	
	/**
	 * Prueba la situación en que tenemos varios elementos iguales
	 */
	@Test
	public void testDesencolaMenorIguales() {
		System.out.println("testDesencolaMenorIguales");
		Integer i;
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		cola.encolaConPrioridad(1);
		cola.encolaConPrioridad(2);
		cola.encolaConPrioridad(3);
		cola.encolaConPrioridad(5);
		cola.encolaConPrioridad(6);
		cola.encolaConPrioridad(3);		
		compruebaCola(cola, 1, 6);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==1);		
		compruebaCola(cola, 2, 5);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==2);		
		compruebaCola(cola, 3, 4);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==3);		
		compruebaCola(cola, 3, 3);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==3);		
		compruebaCola(cola, 5, 2);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==5);		
		compruebaCola(cola, 6, 1);
		
		i = cola.desencolaMasPrioritario();
		assertTrue(i==6);		
		compruebaCola(cola, -7, 0);
	}
	
	/**
	 * Prueba que haceVacia funciona correctamente
	 */
	@Test
	public void testHaceVacia() {
		System.out.println("testHaceVacia");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, -1, 0);
		
		cola.encolaConPrioridad(3);		
		cola.encolaConPrioridad(2);		
		cola.desencolaMasPrioritario();		
		cola.encolaConPrioridad(2);
		
		cola.haceVacia();
		compruebaCola(cola, -3, 0);	
		
		cola.encolaConPrioridad(5);
		compruebaCola(cola, 5, 1);				
	}
	
	/**
	 * Prueba que haceVacia funciona correctamente sin desencolar
	 */
	@Test
	public void testHaceVaciaSimple() {
		System.out.println("testHaceVaciaSimple");
		IColaPrioridad<Integer> cola = new ColaPrioMonticulo<Integer>(8);
		compruebaCola(cola, -1, 0);
		
		cola.encolaConPrioridad(3);		
		cola.encolaConPrioridad(2);		
		Integer i = cola.masPrioritario();
		assertTrue(i==2);
		
		cola.haceVacia();
		compruebaCola(cola, -1, 0);
		i = cola.masPrioritario();
		assertTrue(i==null);
		
		cola.encolaConPrioridad(5);
		compruebaCola(cola, 5, 1);				
	}
	
	

}
