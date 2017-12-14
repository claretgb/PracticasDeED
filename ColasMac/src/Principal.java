import java.util.*;

public class Principal {

	public static void main1(String[] args) {
		ICola<String> cola = new ColaListaDoble<String>();
		cola.encola("Pepe");
		cola.encola("Juan");
		cola.encola("Ana");
		while(!cola.estaVacia()) {
			System.out.println(cola.desencola());
		}
	}
	
	public static void main2(String[] args) {
		Queue<String> cola = new LinkedList<String>();
		cola.add("Pepe");
		cola.add("Juan");
		cola.add("Ana");
		while(!cola.isEmpty()) {
			System.out.println(cola.remove());
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		Queue<String> []v = (Queue<String> []) new Queue[5];
		for(int i = 0; i < v.length; i++) {
			v[i] = new LinkedList<String>();
		}
		v[0].offer("Juan");
		v[0].offer("Quini");
		v[2].offer("Je");
		v[2].offer("Popcorn");
		for(int i = 0; i < v.length; i++) {
			System.out.println("Cola:" +i);
			while(!v[i].isEmpty()) {
				System.out.println(v[i].poll());
			}
		}
	}
}