package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Etapa;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDeIgualdade;

public class InformacaoDeIgualdade extends Informacao {

	public InformacaoDeIgualdade(Fase fase, int valor) {
		super(fase, valor);
		// TODO Auto-generated constructor stub
	}

	public InformacaoDeIgualdade(Fase fase, Elemento elemento) {
		super(fase, elemento);
	}

	
	public boolean isEtapaValida(Etapa etapa) {
		if (isInformacaoComElemento()) {
			if (isIgualAoElemento(etapa)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getValor() == CondicaoDeIgualdade.CONDICAO_SEM_VALOR_DE_REFERENCIA) {
				int referenciaLocal = etapa.getResultadoDaEtapa().getResultados().get(0);
				for (Integer res : etapa.getResultadoDaEtapa().getResultados()) {
					if (!(res == referenciaLocal)) {
						return false;
					}
				}
			} else {
				for (Integer res : etapa.getResultadoDaEtapa().getResultados()) {
					if (!(res == getValor())) {
						return false;
					}
				}

			}
			return true;
		}
	}
	
	private boolean isIgualAoElemento(Etapa etapa){
		List<Integer> lista = etapa.getResultadoDaEtapa().getResultados();
		Elemento elemento = new Elemento();
		for (Integer res : lista) {
			elemento.adicionar(res);
		}
		if (elemento.isIgual(getElemento())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementoDeInteresse(Elemento elemento) {
		if (isInformacaoComElemento()) {
			if (getElemento().isIgual(elemento)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getValor() == CondicaoDeIgualdade.CONDICAO_SEM_VALOR_DE_REFERENCIA) {
				int referenciaLocal = elemento.getElemento().get(0);
				for (Integer res : elemento.getElemento()) {
					if (!(res == referenciaLocal)) {
						return false;
					}
				}
			} else {
				for (Integer res : elemento.getElemento()) {
					if (!(res == getValor())) {
						return false;
					}
				}

			}
			return true;
		}
	}
}
