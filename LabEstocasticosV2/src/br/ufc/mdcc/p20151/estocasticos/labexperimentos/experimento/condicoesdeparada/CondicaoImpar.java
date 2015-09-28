package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoImpar extends Condicao {

	public CondicaoImpar(Fase fase) {
		super(fase);
		// TODO Auto-generated constructor stub
	}

	public CondicaoImpar(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
		// TODO Auto-generated constructor stub
	}

	public CondicaoImpar(Fase fase, Elemento elementoDeReferencia) {
		super(fase, elementoDeReferencia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCondicaoDeParada() {
		// TODO Auto-generated method stub
		return false;
	}

}
