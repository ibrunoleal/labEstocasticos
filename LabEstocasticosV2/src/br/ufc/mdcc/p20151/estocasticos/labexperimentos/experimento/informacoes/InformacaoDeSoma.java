package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Etapa;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class InformacaoDeSoma extends Informacao {
	
	public InformacaoDeSoma(Fase fase, int valor) {
		super(fase, valor);
	}


	public boolean isEtapaValida(Etapa etapa) {
		int soma = 0;
		for (Integer res : etapa.getResultadoDaEtapa().getResultados()) {
			soma += res;
		}
		if (soma == getValor()) {
			return true;
		}
		return false;
	}
	
	public boolean isElementoDeInteresse(Elemento elemento) {
		int soma = elemento.getSomaDoElemento();
		if (soma == getValor()) {
			return true;
		} else {
			return false;
		}
	}
	
}
