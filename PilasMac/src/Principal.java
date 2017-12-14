import java.util.LinkedList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		//Crear array de pilas
		@SuppressWarnings("unchecked")
		List<String> []v = new List[5];
		for(int i = 0; i < 5; i++) {
			v[i] = new LinkedList<String>();
		}
		//añadimos
		v[0].add(0, "A");
		v[0].add(0, "B");
		v[0].add(0, "C");
		v[1].add(0, "D");
		//desapilar
		for(int i = 0; i < 5; i++) {
			while(v[i].size() > 0) { //o (!v[i].isEmpty());
				System.out.println(v[i].remove(0));
			}
		}
	}
}
