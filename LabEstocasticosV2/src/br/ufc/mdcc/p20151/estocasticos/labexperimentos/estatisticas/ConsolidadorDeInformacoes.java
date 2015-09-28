package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.grafico.Grafico;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.grafico.PontoDoGrafico;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Combinacao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.Informacao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoAll;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDeDiferenca;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDeIgualdade;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDePadrao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDeSoma;

public class ConsolidadorDeInformacoes {
	
	public static int QUANTIDADE_DE_REPETICOES_DO_EXPERIMENTO = 2000;

	private Experimento experimento;
	
	private Informacao informacao;
	
	private List<ResultadoDeExperimento> resultadosDeExperimentos;
	
	private List<Elemento> elementosDeInteresse;
	
	public ConsolidadorDeInformacoes(Experimento experimento, Informacao informacao) {
		this.experimento = experimento;
		this.informacao = informacao;
		this.resultadosDeExperimentos = new ArrayList<ResultadoDeExperimento>();
		this.elementosDeInteresse = filtrarElementosDeInteresse();
		
		executar();
	}
	
	/**
	 * Percorre o conjunto de todas as combinacoes possiveis de resultados da fase de informacao
	 * e verifica se cada elemento da combinacao é um resultado desejado pela informacao.
	 * 
	 * @return Lista com os elementos (possiveis resultados) que sao de interesse da informacao.
	 */
	public List<Elemento> filtrarElementosDeInteresse() {
		List<Elemento> listaDeElementos = new ArrayList<Elemento>();
		Combinacao combinacao = new Combinacao(informacao.getFase());
		for (Elemento elemento : combinacao.getListaCombinada()) {
			if (isElementoDeInteresse(elemento)) {
				listaDeElementos.add(elemento);
			}
		}
		return listaDeElementos;
	}
	
	/**
	 * Verifica se um elemento (uma das possiveis combinacoes de resultados) atende aos criterios
	 * estabelecidos na pergunta do problema (informacao)
	 * @param elemento elemento a ser verificado se atende os criterios da informacao
	 * @return true se o elemento atende aos criterios da informacao e false c.c.
	 */
	public boolean isElementoDeInteresse(Elemento elemento) {
		if (informacao instanceof InformacaoDeIgualdade) {
			if ( ((InformacaoDeIgualdade)informacao).isElementoDeInteresse(elemento) ) {
				return true;
			}
		} else {
			if (informacao instanceof InformacaoDeDiferenca) {
				if ( ((InformacaoDeDiferenca)informacao).isElementoDeInteresse(elemento) ) {
					return true;
				}
			} else {
				if (informacao instanceof InformacaoDeSoma) {
					if ( ((InformacaoDeSoma)informacao).isElementoDeInteresse(elemento) ) {
						return true;
					}
				} else {
					if (informacao instanceof InformacaoAll) {
						if ( ((InformacaoAll)informacao).isElementoDeInteresse(elemento) ) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void executar() {
		for (int i = 0; i < QUANTIDADE_DE_REPETICOES_DO_EXPERIMENTO; i++) {
			experimento.limpar();
			experimento.executar();
			
			/*
			 * Para cada execucao do experimento os resultados de sua execucao sao
			 * representados e adicionados na lista de resultadosDeExperimentos
			 */
			ResultadoDeExperimento resultadoDeExperimento = new ResultadoDeExperimento(
					informacao.getQuantidadeDeEtapasValidas(),
					informacao.getFase().getNumeroDeExecucoes(),
					experimento.getNumeroDePassos(),
					informacao.getFase().getElementosDeResultado(),
					informacao.getFase().faseToResultadoDeExperimento()
				);
			resultadosDeExperimentos.add(resultadoDeExperimento);
		}
		
	}
	
	private void exibirEstatisticasDosElementosDeInteresse() {
		if (elementosDeInteresse.size() > 1) {
			System.out.println("\n\nResultado resumido:");
			exibirEstatisticasDeElemento(elementosDeInteresse);
			
			if (informacao.isInformacaoComResultadoExpandido()) {
				System.out.println("\n\nResultado detalhado:");
				for (Elemento elemento : elementosDeInteresse) {
					exibirEstatisticasDeElemento(elemento);
				}
			}
		} else {
			exibirEstatisticasDeElemento(elementosDeInteresse.get(0));
		}
	}
	
	private void exibirEstatisticasDeElemento(Elemento elemento) {
		System.out.println("O resultado " + elemento + " ocorre em media "
				+ getMediaDeOcorrenciaDeElemento(elemento) + " a cada "
				+ getMediaDePassosDaFaseDeInformacao() + " execucoes da fase "
				+ informacao.getFase().getNome() + ".");
//		System.out.println("O resultado " + elemento
//				+ " ocorre numa proporcao de " + getProporcao(elemento)
//				+ " nos lancamentos da fase " + informacao.getFase().getNome()
//				+ ".");
//		System.out.println("Proporcao: " + getProporcao(elemento));
	}
	
	private void exibirEstatisticasDeElemento(List<Elemento> elementos) {
		double mediaDeOcorrencia = 0;
		for (Elemento elemento : elementos) {
			mediaDeOcorrencia += getMediaDeOcorrenciaDeElemento(elemento);
		}
		System.out.println("Os resultados que satisfazem ocorrem em media "
				+ mediaDeOcorrencia + " a cada "
				+ getMediaDePassosDaFaseDeInformacao() + " execucoes da fase "
				+ informacao.getFase().getNome() + ".");
	}
	
	public void exibirEstatisticas() {
		if (!(informacao instanceof InformacaoDePadrao)) {
			exibirEstatisticasDosElementosDeInteresse();
		} else {
			exibirEstatisticasDePadrao();
		}
	}
	
	public void exibirEstatisticasDePadrao() {
		System.out.println("O padrao " + ((InformacaoDePadrao)informacao).getPadrao() + " ocorre em media "
				+ getMediaDeOcorrenciaDoPadrao( ((InformacaoDePadrao)informacao).getPadrao() ) + " a cada "
				+ getMediaDePassosDaFaseDeInformacao() + " execucoes da fase "
				+ informacao.getFase().getNome() + ".");
	}
	
	public int getNumeroDeOcorrenciaDoPadrao(List<Integer> padrao) {
		int contador = 0;
		for (ResultadoDeExperimento resultado : resultadosDeExperimentos) {
			
			int tamanhoDoPadrao = padrao.size();
			int cont = 0;
			if (resultado.getNumeroDeExecucoes() < tamanhoDoPadrao) {
				//nao soma nada
			} else {
				if (resultado.getNumeroDeExecucoes() == tamanhoDoPadrao) {
					if (verificarPadraoNaPosicao(0, padrao, resultado.getResultadosDoExperimento())){
						return 1;
					}
				} else {
					for (int i = 0; i <= (resultado.getNumeroDeExecucoes() - tamanhoDoPadrao); i++) {
						if (verificarPadraoNaPosicao(i, padrao, resultado.getResultadosDoExperimento())) {
							cont++;
						}
					}
				}
			}
			contador +=cont;
			
			
		}
		return contador;
	}
	
	private boolean verificarPadraoNaPosicao(int posicao, List<Integer> padrao, List<Elemento> resultados) {
		int cont = 0;
		while (cont < padrao.size()) {

			if (resultados.get(posicao).getElemento().get(0) == padrao.get(cont)) {
				posicao++;
				cont++;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Apos a execucao de um numero muito grande de repeticoes do experimento
	 * calcula-se o numero de passos do experimento.
	 * @return media de passos por experimento.
	 */
	public double getMediaDePassosDoExperimento() {
		int soma = 0;
		for (ResultadoDeExperimento resultado : resultadosDeExperimentos) {
			soma += resultado.getTotalDePassosNoExperimento();
		}
		return (double)soma / QUANTIDADE_DE_REPETICOES_DO_EXPERIMENTO;
	}
	
	public double getMediaDePassosDaFaseDeInformacao() {
		int soma = 0;
		for (ResultadoDeExperimento resultado : resultadosDeExperimentos) {
			soma += resultado.getNumeroDeExecucoes();
		}
		return (double)soma / QUANTIDADE_DE_REPETICOES_DO_EXPERIMENTO;
	}
	
	public int getNumeroDeOcorrenciaDeElemento(Elemento elemento) {
		int contador = 0;
		for (ResultadoDeExperimento resultado : resultadosDeExperimentos) {
			contador += resultado.getQuantidadeDeOcorrenciasDoElemento(elemento);
		}
		return contador;
	}
	
	public int getNumeroDeExecucoesDaFase() {
		int soma = 0;
		for (ResultadoDeExperimento resultadoDeExperimento : resultadosDeExperimentos) {
			soma += resultadoDeExperimento.getNumeroDeExecucoes();
		}
		return soma;
	}
	
	public double getMediaDeOcorrenciaDeElemento(Elemento elemento) {
		int numeroDeOcorrencias = getNumeroDeOcorrenciaDeElemento(elemento);
		return (double)numeroDeOcorrencias / QUANTIDADE_DE_REPETICOES_DO_EXPERIMENTO;
	}
	
	public double getMediaDeOcorrenciaDoPadrao(List<Integer> padrao) {
		int numeroDeOcorrencias = getNumeroDeOcorrenciaDoPadrao(padrao);
		return (double)numeroDeOcorrencias / QUANTIDADE_DE_REPETICOES_DO_EXPERIMENTO;
	}
	
	public double getProporcao(Elemento elemento) {
		return getMediaDeOcorrenciaDeElemento(elemento) / getMediaDePassosDaFaseDeInformacao();
	}

	/**
	 * Gera um grafico onde o eixo X é dado por um possivel resultado e o eixo
	 * Y é a quantidade de vezes que esse é obtido, em media,no experimento
	 * @param pontosDoGrafico os possiveis resultados e o numero medio de vezes
	 * que ocorrem no experimetno definido
	 */
	public void gerarGrafico(List<PontoDoGrafico> pontosDoGrafico) {
		
		Grafico grafico = new Grafico("Laboratorio de Estocasticos: Simulador de Experimentos",
				"Grafico de Resultados");
		
		for (PontoDoGrafico p : pontosDoGrafico) {
			grafico.adicionarPonto(p.getElemento().toString(), p.getOcorrenciaMedia());
		}
		
		grafico.exportarGraficoComoFigura();
	}


	
	public String getNomeDaFaseDeInformacao() {
		return informacao.getFase().getNome();
	}

	public List<Elemento> getElementosDeInteresse() {
		return elementosDeInteresse;
	}
	

}
