package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoDeDiferenca extends Condicao {

	public CondicaoDeDiferenca(Fase fase) {
		super(fase);
	}

	public CondicaoDeDiferenca(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
	}
	
	public CondicaoDeDiferenca(Fase fase, Elemento elementoDeReferencia) {
		super(fase, elementoDeReferencia);
	}

	@Override
	public boolean isCondicaoDeParada() {
		if (isCondicaoComElemento()) {
			if (isDistintoDoElementoDeReferencia()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getValorDeReferencia() == CONDICAO_SEM_VALOR_DE_REFERENCIA) {
				return isDistintosUnsDosOutros();
			} else {
				return isDistintosDoValorDeReferencia();
			}
		}
	}
	
	private boolean isDistintoDoElementoDeReferencia(){
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		Elemento elemento = new Elemento();
		for (Integer res : lista) {
			elemento.adicionar(res);
		}
		if (!elemento.isIgual(getElementoDeReferencia())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDistintosUnsDosOutros() {
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		if (todosDistintosUnsDosOutros(lista)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean todosDistintosUnsDosOutros(List<Integer> numeros) {
		for (int i = 0; i < numeros.size(); i++) {
			for (int j = 0; j < numeros.size(); j++) {
				if (j != i) {
					if (numeros.get(i) == numeros.get(j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isDistintosDoValorDeReferencia() {
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		for (Integer res : lista) {
			if (res == getValorDeReferencia()) {
				return false;
			}
		}
		return true;
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
		return "!" + valor;
	}

}
