package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;


public class ResultadoDeExperimento {

	private int quantidadeDeEtapasValidas;
	
	private int numeroDeExecucoes;
	
	private int totalDePassosNoExperimento;
	
	private List<ElementoDeResultado> resultadosPossiveis;
	
	private List<Elemento> resultadosDoExperimento;

	public ResultadoDeExperimento(int quantidadeDeEtapasValidas,
			int numeroDeExecucoes, int totalDePassosNoExperimento,
			List<ElementoDeResultado> resultados,
			List<Elemento> resultadosDoExperimento) {
		
		
		this.quantidadeDeEtapasValidas = quantidadeDeEtapasValidas;
		this.numeroDeExecucoes = numeroDeExecucoes;
		this.totalDePassosNoExperimento = totalDePassosNoExperimento;
		this.resultadosPossiveis = resultados;
		this.resultadosDoExperimento = resultadosDoExperimento;
	}

	public int getQuantidadeDeEtapasValidas() {
		return quantidadeDeEtapasValidas;
	}

	public int getNumeroDeExecucoes() {
		return numeroDeExecucoes;
	}

	public int getTotalDePassosNoExperimento() {
		return totalDePassosNoExperimento;
	}

	public List<ElementoDeResultado> getResultados() {
		return resultadosPossiveis;
	}
	
	
	
	public List<Elemento> getResultadosDoExperimento() {
		return resultadosDoExperimento;
	}

	public int getQuantidadeDeOcorrenciasDoElemento(Elemento elemento) {
		for (ElementoDeResultado elementoDeResultado : resultadosPossiveis) {
			if (elementoDeResultado.getElemento().isIgual(elemento)) {
				return elementoDeResultado.getContador();
			}
		}
		return 0;
	}

}
