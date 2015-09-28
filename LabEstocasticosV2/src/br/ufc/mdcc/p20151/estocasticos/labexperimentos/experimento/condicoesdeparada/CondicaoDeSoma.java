package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoDeSoma extends Condicao {

	public CondicaoDeSoma(Fase fase) {
		super(fase);
	}

	public CondicaoDeSoma(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
		// TODO Auto-generated constructor stub
	}

	public CondicaoDeSoma(Fase fase, Elemento elementoDeReferencia) {
		super(fase, elementoDeReferencia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCondicaoDeParada() {
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		int soma = 0;
		for (Integer res : lista) {
			soma += res;
		}
		
		if (isCondicaoComElemento()) {
			if (getSomaDoElemento() == getValorDeReferencia()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (soma == getValorDeReferencia()) {
				return true;
			} else {
				return false;
			}
		}
	}

	private int getSomaDoElemento() {
		int soma = 0;
		for (Integer valor : getElementoDeReferencia().getElemento()) {
			soma += valor;
		}
		return soma;
	}

	public String toString() {
		String valor = "";
		if (isCondicaoComElemento()) {
			valor = getElementoDeReferencia().toString();
		} else {
			if (!(getValorDeReferencia() == CONDICAO_SEM_VALOR_DE_REFERENCIA)) {
				valor = getValorDeReferencia() + "";
			}
		}
		return "+" + valor;
	}
	
}
