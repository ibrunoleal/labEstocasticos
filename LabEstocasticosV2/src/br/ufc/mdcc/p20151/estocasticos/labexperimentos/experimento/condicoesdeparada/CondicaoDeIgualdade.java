package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoDeIgualdade extends Condicao {
	

	public CondicaoDeIgualdade(Fase fase) {
		super(fase);
	}

	public CondicaoDeIgualdade(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
	}
	
	public CondicaoDeIgualdade(Fase fase, Elemento elementoDeReferencia) {
		super(fase, elementoDeReferencia);
	}

	@Override
	public boolean isCondicaoDeParada() {
		if (isCondicaoComElemento()) {
			if (isIgualAoElementoDeReferencia()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getValorDeReferencia() == CONDICAO_SEM_VALOR_DE_REFERENCIA) {
				return isIguaisUnsAosOutros();
			} else {
				return isIguaisAoValorDeReferencia();
			}
		}
	}
	
	private boolean isIgualAoElementoDeReferencia(){
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		Elemento elemento = new Elemento();
		for (Integer res : lista) {
			elemento.adicionar(res);
		}
		if (elemento.isIgual(getElementoDeReferencia())) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isIguaisUnsAosOutros() {
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		int referencia = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados().get(0);
		for (Integer res : lista) {
			if (!(res == referencia)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isIguaisAoValorDeReferencia() {
		List<Integer> lista = getFase().getUltimaEtapaExecutada().getResultadoDaEtapa().getResultados();
		for (Integer res : lista) {
			if (res != getValorDeReferencia()) {
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
		return "=" + valor;
	}
}
