package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoDePadrao extends Condicao {
	
	private List<Integer> padrao;

	public CondicaoDePadrao(Fase fase) {
		super(fase);
		// TODO Auto-generated constructor stub
	}

	public CondicaoDePadrao(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
		// TODO Auto-generated constructor stub
	}
	
	public CondicaoDePadrao(Fase fase, List<Integer> padrao) {
		super(fase);
		this.padrao = padrao;
	}

	@Override
	public boolean isCondicaoDeParada() {
		if (containsPadrao()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Verifica se o padrao da condicao de parada se encontra nos resultados
	 * da etapa.
	 * obs.: funcionando somente para quando a fase é definida
	 * apenas com um poliedro
	 * @return true se o padrao ja foi obtido e false c.c.
	 */
	private boolean containsPadrao() {
		int tamanhoDoPadrao = padrao.size();
		if (getFase().getNumeroDeExecucoes() < tamanhoDoPadrao) {
			return false;
		} else {
			if (getFase().getNumeroDeExecucoes() == tamanhoDoPadrao) {
				if (verificarPadraoNaPosicao(0)){
					return true;
				}
			} else {
				for (int i = 0; i <= (getFase().getNumeroDeExecucoes() - tamanhoDoPadrao); i++) {
					if (verificarPadraoNaPosicao(i)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica se o padrao da condicao de parada existe iniciando na posicao
	 * dada.
	 * @param posicao inicio para verificacao do padrao.
	 * @return true se o padrao existe iniciando na posicao dada e false c.c.
	 */
	private boolean verificarPadraoNaPosicao(int posicao) {
		int cont = 0;
		while (cont < padrao.size()) {
			if (getFase().getEtapas().get(posicao).getResultadoDaEtapa().getResultados().get(0) == padrao.get(cont)) {
				posicao++;
				cont++;
			} else {
				return false;
			}
		}
		return true;
	}

}
