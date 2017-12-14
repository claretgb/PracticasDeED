
public class ConjeturaMoises{

	public static void main(String[] args) {
		IPila<Integer> pila = new PilaListaDoble<Integer>();
		for(int i = 1; i <= 10; i++) {
			pila.apilar(i);
		}
		while(pila.tamanho() > 1) {
			int a = pila.desapilar();
			int b = pila.desapilar();
			int c = a + b;
			System.out.println("Sumo" + a + "y" + b + "y meto" + c);
			pila.apilar(c);
			if(pila.tamanho() >= 2) {
				a = pila.desapilar();
				b = pila.desapilar();
				c = a - b;
				System.out.println("Resto" + a + "y" + b + "y meto" + c);
				pila.apilar(c);
			}
		}
		System.out.println("Resultado: " + pila.desapilar());
	}

}
