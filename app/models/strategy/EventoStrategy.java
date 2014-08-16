package models.strategy;

import models.Evento;
import models.Pessoa;

public interface EventoStrategy {

	/**
	 * Método a ser implementado pelas classes que representam estratégias de
	 * análise de inscrição de um usuário em um evento. A estratégia varia de
	 * acordo com o tipo do evento.
	 * 
	 * @param evento
	 * 		Evento que o usuário deseja participar ou já está participando
	 * @param participante
	 * 		Usuário participante do evento
	 * @return
	 * 		True se o participante está com a inscrição confirmada, false caso contrário
	 */
	public boolean isParticipacaoConfirmada(Evento evento, 
			Pessoa participante);
}
