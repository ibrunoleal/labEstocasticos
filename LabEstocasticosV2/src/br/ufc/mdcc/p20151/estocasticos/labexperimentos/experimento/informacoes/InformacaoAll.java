package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Etapa;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class InformacaoAll extends Informacao {

	public InformacaoAll(Fase fase, int valor) {
		super(fase, valor);
		// TODO Auto-generated constructor stub
	}

	public InformacaoAll(Fase fase, Elemento elemento) {
		super(fase, elemento);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isEtapaValida(Etapa etapa) {
		return true;
	}
	
	public boolean isElementoDeInteresse(Elemento elemento) {
		return true;
	}

}
