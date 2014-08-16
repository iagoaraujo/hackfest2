package models.strategy;

import java.util.Collections;
import java.util.List;

import models.Evento;
import models.Pessoa;

public class EventoStrategyPrioritario implements EventoStrategy{

	@Override
	public boolean isParticipacaoConfirmada(Evento evento, 
			Pessoa participante) {
		List<Pessoa> participantes = evento.getPessoasQueConfirmaram();
		Collections.sort(participantes);
		if (!participantes.contains(participante)) {
			return false;
		}
		int contador = 1;
		for (Pessoa inscrito: participantes) {
			if (inscrito.equals(participante)) {
				return contador<=evento.getLocal().getCapacidade();
			}
			contador++;
		}
		return false;
	}
}
