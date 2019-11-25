package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.comparators.JogadorHabilidadeComparator;
import br.com.codenation.desafio.comparators.JogadorIdadeComparator;
import br.com.codenation.desafio.comparators.JogadorSalarioComparator;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.model.Jogador;
import br.com.codenation.desafio.model.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times = new ArrayList<Time>();
	private List<Jogador> jogadores = new ArrayList<Jogador>();

	public List<Long> TimesId() {
		List<Long> timesId = new ArrayList<Long>();
		for (Time time : times) {
			timesId.add(time.getId());
		}
		return timesId;
	}

	public List<Long> JogadoresId() {
		List<Long> jogadoresId = new ArrayList<Long>();
		for (Jogador jogador : jogadores) {
			jogadoresId.add(jogador.getId());
		}
		Collections.sort(jogadoresId);
		return jogadoresId;
	}

	public Jogador buscaJogadorPeloId(Long idJogador) {
		for (Jogador jogador : jogadores) {
			if (jogador.getId().equals(idJogador)) {
				return jogador;
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public Time buscaTimePeloId(Long idTime) {
		for (Time time : times) {
			if (time.getId().equals(idTime)) {
				return time;
			}
		}
		throw new TimeNaoEncontradoException();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		if (TimesId().contains(id)) {
			throw new IdentificadorUtilizadoException();
		}
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		if (JogadoresId().contains(id)) {
			throw new IdentificadorUtilizadoException();
		}
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		jogadores.add(new Jogador(id, nome, dataNascimento, nivelHabilidade, salario, idTime));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		if (!JogadoresId().contains(idJogador)) {
			throw new JogadorNaoEncontradoException();
		}

		for (Jogador jogador : jogadores) {
			if (jogador.getId().equals(idJogador)) {
				for (Time time : times) {
					if (time.getId().equals(jogador.getIdTime())) {
						time.setIdCapitao(idJogador);
					}
				}
			}
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		for (Time time : times) {
			if (time.getId().equals(idTime) && time.getIdCapitao() != null) {
				return time.getIdCapitao();
			}
		}
		throw new CapitaoNaoInformadoException();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		String result = "";
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		for (Time time : times) {
			if (time.getId().equals(idTime)) {
				result = time.getNome();
			}
		}
		return result;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		String result = "";
		if (!JogadoresId().contains(idJogador)) {
			throw new JogadorNaoEncontradoException();
		}
		for (Jogador jogador : jogadores) {
			if (jogador.getId().equals(idJogador)) {
				result = jogador.getNome();
			}
		}
		return result;
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}

		List<Long> jogadoresDoTime = new ArrayList<Long>();
		for (Jogador jogador : jogadores) {
			if (jogador.getIdTime().equals(idTime)) {
				jogadoresDoTime.add(jogador.getId());
			}
		}
		Collections.sort(jogadoresDoTime);
		return jogadoresDoTime;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return TimesId();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		Collections.sort(jogadores, new JogadorHabilidadeComparator());
		List<Long> jogadoresDoTime = new ArrayList<Long>();
		for (Jogador jogador : jogadores) {
			if (jogador.getIdTime().equals(idTime)) {
				jogadoresDoTime.add(jogador.getId());
			}
		}
		return jogadoresDoTime.get(0);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		Collections.sort(jogadores, new JogadorIdadeComparator());
		List<Long> jogadoresDoTime = new ArrayList<Long>();
		for (Jogador jogador : jogadores) {
			if (jogador.getIdTime().equals(idTime)) {
				jogadoresDoTime.add(jogador.getId());
			}
		}
		return jogadoresDoTime.get(0);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if (!JogadoresId().contains(idJogador)) {
			throw new JogadorNaoEncontradoException();
		}
		return buscaJogadorPeloId(idJogador).getSalario();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if (!TimesId().contains(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		Collections.sort(jogadores, new JogadorSalarioComparator());
		List<Long> jogadoresDoTime = new ArrayList<Long>();
		for (Jogador jogador : jogadores) {
			if (jogador.getIdTime().equals(idTime)) {
				jogadoresDoTime.add(jogador.getId());
			}
		}
		return jogadoresDoTime.get(0);
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		Collections.sort(jogadores, new JogadorHabilidadeComparator());
		List<Long> topJogadores = new ArrayList<Long>();
		for (int i = 0; i < top; i++) {
			topJogadores.add(jogadores.get(i).getId());
		}
		return topJogadores;
	}
	
	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (!TimesId().contains(timeDaCasa) || !TimesId().contains(timeDeFora)) {
			throw new TimeNaoEncontradoException();
		}
		Time mandante = buscaTimePeloId(timeDaCasa);
		Time visitante = buscaTimePeloId(timeDeFora);
		if (mandante.getCorUniformePrincipal().equals(visitante.getCorUniformePrincipal())) {
			return visitante.getCorUniformeSecundario();
		} else {
			return visitante.getCorUniformePrincipal();
		}
	}

}
