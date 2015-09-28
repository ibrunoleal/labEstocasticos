package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Etapa;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.Condicao;

public class InformacaoDePadrao extends Informacao {
	
	private List<Integer> padrao;

	public InformacaoDePadrao(Fase fase, int valor) {
		super(fase, valor);
		// TODO Auto-generated constructor stub
	}
	

	public InformacaoDePadrao(Fase fase, List<Integer> padrao) {
		super(fase, Condicao.CONDICAO_SEM_VALOR_DE_REFERENCIA);
		this.padrao = padrao;
	}



//	@Override
//	public String getInformacao() {
//		// TODO Auto-generated method stub
//		return "nao tem informacao";
//	}


	
	/**
	 * Recupera a quantidade de lancamentos(etapas) da fase dada
	 * cujos resultados estao de acordo com a informação requerida.
	 * @return o numero de etapas da fase que satisfazem a informação.
	 */
	public int getQuantidadeDeEtapasValidas() {
		return getQuantidadeDePadroesValidos();
	}
	
	private int getQuantidadeDePadroesValidos() {
		int tamanhoDoPadrao = padrao.size();
		int cont = 0;
		if (getFase().getNumeroDeExecucoes() < tamanhoDoPadrao) {
			return 0;
		} else {
			if (getFase().getNumeroDeExecucoes() == tamanhoDoPadrao) {
				if (verificarPadraoNaPosicao(0)){
					return 1;
				}
			} else {
				for (int i = 0; i <= (getFase().getNumeroDeExecucoes() - tamanhoDoPadrao); i++) {
					if (verificarPadraoNaPosicao(i)) {
						cont++;
					}
				}
			}
		}
		return cont;
	}

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

	

	public int getTamanhoDoPadrao() {
		return padrao.size();
	}


	/**
	 * Nao foi implementado.
	 * Caso especial de informacao que eh necessario mais do que uma etapa
	 * para verificar o resultado;
	 * @return sempre retorna true;
	 */
	public boolean isEtapaValida(Etapa etapa) {
		return true;
	}


	public List<Integer> getPadrao() {
		return padrao;
	}
	
	
}
