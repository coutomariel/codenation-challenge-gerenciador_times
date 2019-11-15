package br.com.codenation.desafio.comparators;

import java.util.Comparator;

import br.com.codenation.desafio.model.Jogador;

public class JogadorIdadeComparator implements Comparator<Jogador>{

	@Override
	public int compare(Jogador jogador, Jogador outroJogador) {
		if (jogador.getIdade() > outroJogador.getIdade()) {
			return -1;
		}
		if (outroJogador.getIdade() > jogador.getIdade()) {
			return 1;
		}
		if (outroJogador.getNivelHabilidade() == jogador.getNivelHabilidade()) {
			if (jogador.getId() < outroJogador.getId()) {
				return -1;
			}
			if (outroJogador.getId() > jogador.getId()) {
				return 1;
			}
		}
		return 0;
	}

}
