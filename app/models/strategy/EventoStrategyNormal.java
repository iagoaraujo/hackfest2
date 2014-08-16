package models.strategy;

import java.util.List;

import models.Evento;
import models.Pessoa;

public class EventoStrategyNormal implements EventoStrategy{

	@Override
	public boolean isParticipacaoConfirmada(Evento evento, Pessoa participante) {
		List<Pessoa> participantes = evento.getPessoasQueConfirmaram();
		if (!participantes.contains(participante)) {
			return false;
		}
		return true;
	}
}
