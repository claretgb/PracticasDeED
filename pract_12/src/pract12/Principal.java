package pract12;

import pract12.Imserso.Cliente;

public class Principal {

	public static void main(String[] args) {
		IColaPrioridad<Cliente> cola = new ColaPrioMonticulo<Cliente>(10);
		Cliente marisa = new Cliente("Marisa",12);
		Cliente juan = new Cliente("Juan",3);
		Cliente roberto = new Cliente("Roberto",2);
		Cliente mCarmen = new Cliente("María del Carmen",11);
		Cliente luisa = new Cliente("Luisa", 16);
		cola.encolaConPrioridad(marisa);
		cola.encolaConPrioridad(juan);
		cola.encolaConPrioridad(roberto);
		cola.encolaConPrioridad(mCarmen);
		cola.encolaConPrioridad(luisa);
		//System.out.println(cola.masPrioritario());
		//System.out.println(cola);
		System.out.println(cola.desencolaMasPrioritario());
		System.out.println(cola.desencolaMasPrioritario());
		System.out.println(cola.desencolaMasPrioritario());
		System.out.println(cola.desencolaMasPrioritario());
		System.out.println(cola.desencolaMasPrioritario());
	}

}
