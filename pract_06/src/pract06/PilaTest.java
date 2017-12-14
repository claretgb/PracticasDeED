package pract06;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test del TDA Pila.
 *
 * @author Estructuras de Datos (UC)
 * @version oct-2017
 */
public class PilaTest {
	
	@Test
	public void testConstructor() {
		System.out.println("testConstructor");
		
		// comprueba que la pila recien creada tiene tamanho 0
		IPila<Character> pila = new PilaSimpleEnlace<Character>();
		compruebaPila(pila, 'X', 0);		
	}
	
	@Test
	public void testApila() {
		System.out.println("testApila");
		IPila<Character> pila = new PilaSimpleEnlace<Character>();
		
		// comprueba que los elementos se apilan correctamente
		char c = 'A';
		for(int i=1; i<=4; i++) {
			pila.apila(c);
			// comprueba que la pila tiene en la cima el ultimo caracter anhadido y que
			// su tamanho es correcto
			compruebaPila(pila, c, i);
			
			c++; // pasa al siguiente caracter
		}	
	}
		
	@Test
	public void testApilaDesapila() {
		System.out.println("testApilaDesapila");
		IPila<Character> pila = new PilaSimpleEnlace<Character>();
		
		// apila varios elementos
		char c = 'A';
		for(int i=1; i<=5; i++) {
			pila.apila(c);
			
			c++; // pasa al siguiente caracter
		}
		// comprueba que la pila tiene en la cima el ultimo caracter anhadido y que
		// su tamanho es correcto
		compruebaPila(pila, (char)(c-1), 5);
		
		// comprueba que los elementos se desapilan en el orden correcto
		c--; // hacemos c igual al ultimo elemento apilado
		for(int i=5; i>=1; i--) {
			char desapilado = pila.desapila();
			// comprueba que el elemento desapilado es el correcto
			assertTrue("Desapilado deberia ser " + c + " pero es " + desapilado,
					desapilado==c);
			
			c--; // pasa al siguiente caracter
			
			// comprueba que la pila tiene en la cima el elemento esperado y que
			// su tamanho es correcto
			compruebaPila(pila, c, i-1);
		}
	}
		
	@Test
	public void testApilaDesapilaIntercalados() {
		System.out.println("testApilaDesapilaIntercalados");
		char desapilado;
		IPila<Character> pila = new PilaSimpleEnlace<Character>();
		
		pila.apila('a'); // [a]
		compruebaPila(pila, 'a', 1);
		
		pila.apila('b'); // [b, a]
		compruebaPila(pila, 'b', 2);
		
		desapilado = pila.desapila(); // b <- [a]
		assertTrue("Desapilado deberia ser b pero es " + desapilado, desapilado=='b');
		compruebaPila(pila, 'a', 1);
		
		pila.apila('c'); // [c, a]
		compruebaPila(pila, 'c', 2);
		
		desapilado = pila.desapila(); // c <- [a]
		assertTrue("Desapilado deberia ser c pero es " + desapilado, desapilado=='c');
		compruebaPila(pila, 'a', 1);
		
		desapilado = pila.desapila(); // a <- []
		assertTrue("Desapilado deberia ser a pero es " + desapilado, desapilado=='a');
		compruebaPila(pila, 'X', 0);
		
		pila.apila('y'); // [y]
		compruebaPila(pila, 'y', 1);
		
		desapilado = pila.desapila(); // y <- []
		assertTrue("Desapilado deberia ser y pero es " + desapilado, desapilado=='y');
		compruebaPila(pila, 'X', 0);
	}
	
	@Test
	public void testHaceVacia() {
		System.out.println("testHaceVacia");
		IPila<Character> pila = new PilaSimpleEnlace<Character>();
		
		// apila varios elementos
		pila.apila('a'); // [a]
		pila.apila('b'); // [b, a]
		compruebaPila(pila, 'b', 2);
		
		// hace vacia la pila
		pila.haceVacia();
		compruebaPila(pila, 'X', 0);
		
		// comprueba que el apilar y el desapilar sigue funcionando bien
		pila.apila('z'); // [z]
		compruebaPila(pila, 'z', 1);
		
		char desapilado = pila.desapila(); // z <- []
		assertTrue("Desapilado deberia ser z pero es " + desapilado, desapilado=='z');
		compruebaPila(pila, 'X', 0);		
	}
	
			
	/**
	 * Metodo auxiliar que comprueba que la pila esta en el estado esperado
	 * @param pila pila a comprobar
	 * @param tamanhoEsperado tamanho esperado de la pila
	 * @param cimaEsperada elemento esperado en la cabeza de la pila
	 */
	private void compruebaPila(IPila<Character> pila,
			char cimaEsperada, int tamanhoEsperado) {
		// muestra el estado de la pila para facilitar la depuracion
		if (pila.tamanho()==0) {
			System.out.println("Cima:<VACIA> Tamanho:" + pila.tamanho());
		} else {
			System.out.println("Cima:" + pila.cima() + " Tamanho:" + pila.tamanho());
		}
		
		// comprueba que el tamanho es el esperado
		assertTrue("Tamaño:"+pila.tamanho()+" debería ser " + tamanhoEsperado,
				pila.tamanho()==tamanhoEsperado);
		
		// comprueba que el elemento a la cabeza de la pila es el esperado
		if (!(pila.tamanho()==0))
			assertTrue("Error cima:"+pila.cima()+" deberia ser "+
					cimaEsperada, cimaEsperada==pila.cima());

		
		// comprueba que estaVacia() retorna el resultado esperado
		if (tamanhoEsperado==0) {
			assertTrue("Error: deberia estar vacia", pila.tamanho()==0);
		} else {
			assertTrue("Error: no deberia estar vacia", !(pila.tamanho()==0));
		}
	}

}
