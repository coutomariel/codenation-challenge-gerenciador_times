package br.com.codenation.desafio.comparators;

import java.util.Comparator;

import br.com.codenation.desafio.model.Jogador;

public class JogadorSalarioComparator implements Comparator<Jogador> {

	@Override
	public int compare(Jogador jogador, Jogador outroJogador) {
		if (jogador.getSalario().compareTo(outroJogador.getSalario())>0) {
			return -1;
		}
		if (outroJogador.getSalario().compareTo(jogador.getSalario())>0) {
			return 1;
		}
		if (outroJogador.getSalario().equals(jogador.getSalario())) {
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
