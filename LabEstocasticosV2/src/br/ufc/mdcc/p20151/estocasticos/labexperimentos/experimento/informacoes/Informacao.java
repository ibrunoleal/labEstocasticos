package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Etapa;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto.TextoToInformacao;

public abstract class Informacao {

	


	/**
	 * Fase cuja cada etapa de execucao sera verificada o valor
	 * da soma dos resultados obtidos.
	 */
	private Fase fase;
	
	/**
	 * O valor a ser verificado para cada etapa da fase.
	 * obs.: quando for o caso, pode ser verificacao por elemento, ou todos os casos.
	 */
	private int valor;
	
	/**
	 * O elemento de resultado a ser verificado.
	 * obs.: quando for o caso, pode ser verificacao por valor, ou todos os casos.
	 */
	private Elemento elemento;
	
	private boolean informacaoComResultadoExpandido;
	
	
	public Informacao(Fase fase, int valor) {
		this.fase = fase;
		this.valor = valor;
		this.elemento = null;
		this.informacaoComResultadoExpandido = false;
	}
	
	public Informacao(Fase fase, Elemento elemento) {
		this.fase = fase;
		this.elemento = elemento;
		this.valor = TextoToInformacao.VALOR_DA_INFORMACAO_NAO_DEFINIDO;
		this.informacaoComResultadoExpandido = false;
	}

	/**
	 * Recupera a quantidade de lancamentos(etapas) da fase dada
	 * cujos resultados estao de acordo com a informação requerida.
	 * @return o numero de etapas da fase que satisfazem a informação.
	 */
	public int getQuantidadeDeEtapasValidas() {
		int quantidadeDeEtapasValidas = 0;
		for (Etapa etapa : getFase().getEtapas()) {
			if (isEtapaValida(etapa)) {
				quantidadeDeEtapasValidas++;
			}
		}
		return quantidadeDeEtapasValidas;
	}
	
	/**
	 * Verifica se os resultados do lancamento de uma etapa correspondem
	 * às informacoes solicitas.
	 * @param etapa etapa a ser verificada
	 * @return true se a os valores da etapa satisfazem a informacao requerida
	 */
	public abstract boolean isEtapaValida(Etapa etapa);
	
	public Fase getFase() {
		return fase;
	}

	public boolean isInformacaoComElemento() {
		if (elemento != null) {
			return true;
		}
		return false;
	}

	public int getValor() {
		return valor;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public boolean isInformacaoComResultadoExpandido() {
		return informacaoComResultadoExpandido;
	}

	public void setInformacaoComResultadoExpandido(
			boolean informacaoComResultadoExpandido) {
		this.informacaoComResultadoExpandido = informacaoComResultadoExpandido;
	}
	
	@Override
	public String toString() {
		return "Informacao [fase=" + fase.getNome() + ", valor=" + valor + ", elemento="
				+ elemento + ", informacaoComResultadoExpandido="
				+ informacaoComResultadoExpandido + "]";
	}

}
