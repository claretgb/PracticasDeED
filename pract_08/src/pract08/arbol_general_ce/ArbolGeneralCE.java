package pract08.arbol_general_ce;

/**
 * Clase que implementa la interfaz del TDA Arbol general.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol.
 * 
 * @author Clara Torre García-Barredo
 * @version nov-2017
 */
public class ArbolGeneralCE<E> implements IArbolGeneral<E> {

	private Nodo<E> raiz;
	
	/**
	 * Clase que implementa un nodo de un arbol general 
	 * (forma parte del TDA Arbol General).
	 * 
	 * @param <E> tipo de los elementos almacenados en el arbol.
	 * 
	 * @author Clara Torre García-Barredo
	 * @version nov-2017
	 */
	public static class Nodo<E> implements INodoArbolGeneral<E> {
		private E contenido;
		private Nodo<E> padre;
		private Nodo<E> primerHijo;
		private Nodo<E> hermanoDerecho;
		
		/*
		 * Complejidad temporal: O(1).
		 */
		/**
		 * Constructor de la clase Nodo.
		 */
		public Nodo(){
			this.contenido = null;
		}
		
		/*
		 * Complejidad temporal: O(1).
		 */
		/**
		 * Constructor de la clase Nodo.
		 * @param E contenido: el contenido del nodo.
		 */
		public Nodo(E contenido){
			this.contenido = contenido;
		}
		
		/**
		 * Retorna el contenido del nodo.
		 * @return el contenido del nodo.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public E contenido() {
			return this.contenido;
		}

		/**
		 * Cambia el contenido del nodo.
		 * @param nuevoContenido nuevo contenido del nodo.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void cambiaContenido(E nuevoContenido) {
			this.contenido = nuevoContenido;
			
		}

		/**
		 * Retorna el padre del nodo.
		 * @return el padre del nodo o null si el nodo actual es la raiz.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public INodoArbolGeneral<E> padre() {
			return padre;
		}

		/**
		 * Retorna el primer hijo del nodo.
		 * @return el primer hijo del nodo o null si el nodo actual
		 * no tiene hijos.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public INodoArbolGeneral<E> primerHijo() {
			return primerHijo;
		}

		/**
		 * Retorna el hermano derecho del nodo.
		 * @return el hermano derecho del nodo o null si el nodo actual
		 * es el ultimo hermano.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public INodoArbolGeneral<E> hermanoDcho() {
			return hermanoDerecho;
		}

		/**
		 * Anhade el primer hijo al nodo.
		 * Su antiguo primer hijo (si le hubiera) pasa a
		 * ser el hermano derecho del recien anhadido.
		 * @param contenido contenido del nodo a anhadir.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void insertaPrimerHijo(E contenido) {
			Nodo<E> hijo=new Nodo<E>(contenido);
			hijo.padre=this;
			hijo.hermanoDerecho=(Nodo<E>) this.primerHijo();
			this.primerHijo=hijo;			
		}

		/**
		 * Anhade el hermano derecho al nodo.
		 * Su antiguo hermano derecho (si le hubiera) pasa a
		 * ser el hermano derecho del recien anhadido.
		 * @param contenido contenido del nodo a anhadir.
		 * @throws UnsupportedOperationException si el nodo es la raiz.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void insertaHermanoDcho(E contenido)
				throws UnsupportedOperationException {
			if(padre == null){
				throw new UnsupportedOperationException();
			}
			
			Nodo<E> hermano=new Nodo<E>(contenido);
			hermano.padre=this.padre;
			hermano.hermanoDerecho=this.hermanoDerecho;
			hermanoDerecho=hermano;
		}

		/**
		 * Anhade la raiz de la rama como primer hijo del nodo actual.
		 * Su antiguo primer hijo (si le hubiera) pasa a ser el
		 * hermano derecho del nodo que era la raíz de la rama.
		 * Despues de esta operacion el arbol "rama" queda vacio.
		 * @param rama rama que anhadir como primer hijo.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void insertaRamaPrimerHijo(IArbolGeneral<E> rama) {
			Nodo<E> punteroAuxiliar = primerHijo;
			primerHijo = (Nodo<E>) rama.raiz();
			rama.cambiaPadre(this);
			rama.cambiaHermanoDerecho(punteroAuxiliar);
			rama.haceVacio();
		}

		/**
		 * Anhade la raiz de la rama como hermano derecho del nodo actual.
		 * Su antiguo hermano derecho (si le hubiera) pasa a ser el
		 * hermano derecho del nodo que era la raíz de la rama.
		 * Despues de esta operacion el arbol "rama" queda vacio.
		 * @param rama rama que anhadir como primer hijo.
		 * @throws UnsupportedOperationException si el nodo es la raiz.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public void insertaRamaHermanoDcho(IArbolGeneral<E> rama)
				throws UnsupportedOperationException {
			if(padre == null) {
				throw new UnsupportedOperationException();
			}
			ArbolGeneralCE<E> rama2 = (ArbolGeneralCE<E>) rama;
			rama2.raiz.hermanoDerecho = this.hermanoDerecho;
			rama2.raiz.padre = this.padre;
			this.hermanoDerecho = rama2.raiz;
			rama.haceVacio();
		}

		/**
		 * Elimina la rama cuya raiz es en nodo actual.
		 * @return arbol cuya raiz es el nodo actual.
		 */
		/*
		 * Complejidad temporal: O(1).
		 */
		@Override
		public IArbolGeneral<E> cortaRama() {
			if(padre.primerHijo == this) {
				padre.primerHijo = this.hermanoDerecho;
				this.padre = null;
				this.hermanoDerecho = null;
			}
			else {
				Nodo<E> aux = padre.primerHijo;
				while(aux.hermanoDerecho != this) {
					aux = aux.hermanoDerecho;
				}
				aux.hermanoDerecho = this.hermanoDerecho;
				this.hermanoDerecho = null;
				this.padre = null;
			}
			IArbolGeneral<E> solucion = new ArbolGeneralCE<E>();
			((ArbolGeneralCE<E>)solucion).raiz = this;
			return solucion;
		}
	}
	
	/**
	 * Constructor de la clase ArbolGeneralCE.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public ArbolGeneralCE() {
		raiz = null;
	}
	
	/**
	 * Constructor de la clase ArbolGeneralCE.
	 * @param E contenidoRaiz: el contenido del nodo raíz.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	public ArbolGeneralCE(E contenidoRaiz){
		raiz = new Nodo<E>(contenidoRaiz);		
	}
	
	/**
	 * Retorna el nodo en la raiz del arbol.
	 * @return el nodo en la raiz del arbol.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public Nodo<E> raiz() {
		return raiz;
	}

	/**
	 * Retorna el numero de nodos del arbol.
	 * @return numero de nodos del arbol.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	@Override
	public int tamanho() {
		if(raiz == null) {
			return 0;
		}
		return numDescendientesRec(raiz);
	}
	
	/**
	 * Método que calcula el número de descendientes de un nodo.
	 * @param INodoArbolGeneral<E> nodo: el nodo del que se quiere 
	 * calcular el número de descendientes.
	 * @return numDescendientes: el número de descendientes.
	 */
	/*
	 * Complejidad temporal: O(n).
	 */
	/*
	 * El tipo de recorrido que hace es preorden.
	 */
	public int numDescendientesRec(INodoArbolGeneral<E> nodo) {
		int numDescendientes = 1;
		nodo = nodo.primerHijo();
		while(nodo != null){
			numDescendientes += numDescendientesRec(nodo);
			nodo = nodo.hermanoDcho();
		}
		return numDescendientes;
	}

	/**
	 * Vacia el arbol.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public void haceVacio() {
		raiz = null;
	}

	/**
	 * Anhade el nodo raiz con el contenido indicado a un arbol vacio.
	 * @param contenidoRaiz contenido del nodo raiz.
	 * @throws UnsupportedOperationException si el arbol no está vacio.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public void anhadeRaiz(E contenidoRaiz)
			throws UnsupportedOperationException {
		if(raiz() != null){
			throw new UnsupportedOperationException();
		}
		raiz = new Nodo<E>(contenidoRaiz);
	}

	/**
	 * Método auxiliar que cambia el padre de un nodo.
	 * @param Nodo<E> padre: el padre que le quieres asignar.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public void cambiaPadre(Nodo<E> padre) {
		Nodo<E> aux = (Nodo<E>) raiz;
		aux.padre = padre;
	}

	/**
	 * Método auxiliar que cambia el hermano derecho de un nodo.
	 * @param Nodo<E> hermanoDerecho: el hermano derecho que le quieres asignar.
	 */
	/*
	 * Complejidad temporal: O(1).
	 */
	@Override
	public void cambiaHermanoDerecho(Nodo<E> hermanoDerecho) {
			Nodo<E> aux = (Nodo<E>) raiz;
			aux.hermanoDerecho = hermanoDerecho;
		}
}
