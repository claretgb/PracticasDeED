package OficinaPatentesMac;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OficinaPatentes {

	private Queue<Peticion>[] listaColas;
	
	@SuppressWarnings("unchecked")
	public OficinaPatentes() {
		listaColas = new Queue[7];
		for(int i = 0; i < 7; i++){
			listaColas[i] = new LinkedList<Peticion>();
		}
	}
	
	public void nuevaPeticion(Peticion peticion, int nivel) {
		if(nivel < 0 || nivel > 6) {
			System.out.println("Error. Nivel incorrecto");
		}
		else {
			listaColas[nivel].add(peticion);
		}
	}
	
	public String atenderPeticion() {
		int i = listaColas.length-1;
		Iterator<Peticion> iterador;
		Peticion aux = null;
			do {
				iterador = listaColas[i].iterator();
				if(iterador.hasNext()) {
					i--;
				}
				else {
					aux = iterador.next();
					i = 0;
				}
			}while(i >= 0);
		return aux.descripcion();
	}
	
	public List<Peticion> atenderTodas() {
		List<Peticion> auxiliar = new LinkedList<Peticion>();
		int i = listaColas.length-1;
		Iterator<Peticion> iterador;
		Peticion aux = null;
		do {
			iterador = listaColas[i].iterator();
			if(!iterador.hasNext()) {
				i--;
			}
			else {
				aux = iterador.next();
				iterador.remove();
				auxiliar.add(aux);
			}
		}while(i >= 0);
		return auxiliar;
	}
	
	public void transvase(int nivelOrigen, int nivelDestino, int nPeticiones) {
		Iterator<Peticion> iterador;
		iterador = listaColas[nivelOrigen].iterator();
		int i = 0;
		while(iterador.hasNext() && i <= nPeticiones) {
			listaColas[nivelDestino].add(iterador.next());
			iterador.remove();
			i++;
		}
	}
}
