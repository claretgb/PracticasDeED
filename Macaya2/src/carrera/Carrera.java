package carrera;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Carrera {

	private Map<Integer,Participante> mapaParticipantes;
	private Map<String,Equipo> mapaEquipos;
	private SortedSet<Equipo> clasificacion;
	
	@SuppressWarnings("serial")
	public static class DorsalIncorrecto extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class ParticipanteYaEnMeta extends RuntimeException {}
	
	@SuppressWarnings("serial")
	public static class EquipoIncorrecto extends RuntimeException {}
	
	public Carrera() {
		mapaParticipantes = new HashMap<Integer,Participante>();
		mapaEquipos = new HashMap<String,Equipo>();
		clasificacion = new TreeSet<Equipo>();
	}
	
	public void anhadeParticipante(Participante participante) {
		mapaParticipantes.put(participante.getNumeroDorsal(), participante);
	}
	
	public void anhadeEquipo(Equipo equipo) {
		mapaEquipos.put(equipo.getNombre(), equipo);
	}
	
	public void anhadeParticipanteAEquipo(Participante participante, Equipo equipo) {
		Participante[] miembros = equipo.getMiembros();
		int i = equipo.CONTADOR;
		miembros[i] = participante;
		equipo.CONTADOR++;
		participante.setEquipo(equipo);
	}
	
	public void participanteLlegaAMeta(int dorsal, double tiempo) 
			throws ParticipanteYaEnMeta, DorsalIncorrecto {
		if(!mapaParticipantes.containsKey(dorsal)) {
			throw new DorsalIncorrecto();
		}
		Participante participante = buscaParticipante(dorsal);
		if(participante.getTiempo() != 0) {
			throw new ParticipanteYaEnMeta();
		}
		participante.setTiempo(tiempo);
		if(participante.getEquipo() != null) {
			participante.getEquipo().setTiempo(participante.getEquipo().getTiempo()+tiempo);
		}
		Participante[] miembros = participante.getEquipo().getMiembros();
		boolean equipoNoCompleto = false;
		for(int i = 0; i < participante.getEquipo().getMiembros().length; i++) {
			if(miembros[i].getTiempo() == 0) {
				equipoNoCompleto = true;
			}
		}
		if(!equipoNoCompleto) {
			anhadeAClasificacion(participante.getEquipo());
		}
	}
	
	private void anhadeAClasificacion(Equipo equipo) {
		clasificacion.add(equipo);
	}
	
	public String muestraDatosEquipo(String nombreEquipo) throws EquipoIncorrecto {
		Equipo equipo = buscaEquipo(nombreEquipo);
		if(equipo == null) {
			throw new EquipoIncorrecto();
		}
		Participante[] miembros = equipo.getMiembros();
		String str = "Datos del equipo " + nombreEquipo + ": Tiempo: " + equipo.getTiempo() + ", Participantes: ";
		for(int i = 0; i < miembros.length; i++) {
			str = str + "Dorsal: " + miembros[i].getNumeroDorsal();
			if(miembros[i].getTiempo() != 0) {
				str = str + "Ha llegado";
			} else {
				str = str + "Aún no ha llegado";
			}
		}
		return str;
	}
	
	public String muestraLosQueFaltan(Equipo equipo) {
		String str = "Equipo: " + equipo.getNombre();
		Participante[] miembros = equipo.getMiembros();
		for(int i = 0; i < miembros.length; i++) {
			if(miembros[i].getTiempo() == 0) {
				str = str + "No ha llegado el participante: " + miembros[i].getNumeroDorsal();
			}
		}
		return str;
	}
	
	public String muestraTodosLosEquipos() {
		Set<String> nombresEquipos = mapaEquipos.keySet();
		String str = "";
		for(String nombre : nombresEquipos) {
			str = str + muestraDatosEquipo(nombre);
		}
		return str;
	}
	
	public String muestraClasificacion() {
		
		String t="";
		for(Equipo e: clasificacion)
		{
			t=t+e+"\n";
			
		}
		return t;
}

	private Participante buscaParticipante(int dorsal) {
		if(mapaParticipantes.containsKey(dorsal)) {
			return mapaParticipantes.get(dorsal);
		}
		return null;
	}
	
	private Equipo buscaEquipo(String nombre) {
		if(mapaEquipos.containsKey(nombre)) {
			return mapaEquipos.get(nombre);
		}
		return null;
	} 
}
